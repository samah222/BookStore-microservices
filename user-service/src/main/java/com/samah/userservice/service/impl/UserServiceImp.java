package com.samah.userservice.service.impl;

import com.samah.userservice.dto.*;
import com.samah.userservice.entity.ChangePasswordVerificationToken;
import com.samah.userservice.entity.ResetVerificationToken;
import com.samah.userservice.entity.VerificationToken;
import com.samah.userservice.exception.*;
import com.samah.userservice.entity.User;
import com.samah.userservice.mapper.UserMapper;
import com.samah.userservice.payload.Mail;
import com.samah.userservice.producer.RabbitmqMailProducer;
import com.samah.userservice.repository.ChangePasswordVerificationTokenRepository;
import com.samah.userservice.repository.ResetVerificationTokenRepository;
import com.samah.userservice.repository.VerificationTokenRepository;
import com.samah.userservice.service.UserService;
import com.samah.userservice.utils.EmailValidation;
import com.samah.userservice.utils.PasswordValidator;
import com.samah.userservice.utils.TokenExpirationTime;
import com.samah.userservice.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.samah.userservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImp implements UserService {
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    ChangePasswordVerificationTokenRepository changePasswordVerificationTokenRepository;
    @Autowired
    ResetVerificationTokenRepository resetVerificationTokenRepository;
    @Autowired
    private InfoServiceImpl infoServiceImpl;
    UserMapper userMapper;
    @Autowired
    private RabbitmqMailProducer producer;

    @Override
    public UserDto getUserByName(String name) {
        User user = userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.UserToUserDto(user);
    }

    @Override
    @Cacheable(value = "userById", key = "#id")
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.UserToUserDto(user);
    }

    @Override
    public UserDto addUser(UserRegistrationDto registrationDto) {
        if (registrationDto == null
                || registrationDto.getName() == null
                || registrationDto.getEmail() == null
                || registrationDto.getPassword() == null
                || registrationDto.getMatchingPassword() == null)
            throw new NullPointerException("User data should not be null");
        if(userRepository.findByEmail(registrationDto.getEmail())!= null)
            throw new UsernameAlreadyExistsException("Email already registered ");
        if (!registrationDto.getPassword().equals(registrationDto.getMatchingPassword()))
            throw new PasswordNotMatchingException("Passwords not matching");
        if(!PasswordValidator.isPasswordStrong(registrationDto.getPassword()))
            throw new WeakPasswordException("Password should be at least 8 characters, one Capital, number and" +
                    "a special character");
        if(!EmailValidation.isValidEmail(registrationDto.getEmail()))
            throw new InvalidUserDataException("Invalid email syntax");
        User user = userMapper.UserRegistrationDtoToUser(registrationDto);
        user.setTokens(user.getTokens() + 1);
        User savedUser = userRepository.save(user);
        String token = UUID.randomUUID().toString();
        saveVerificationToken(savedUser, token);
        // send email
        String url = "http://" + infoServiceImpl.getMyappServer() + ":" + infoServiceImpl.getServerPort()
                + "/v1/users/verifyRegistration?token=" + token;
        producer.sendMessage(new Mail(savedUser.getEmail(),"BookStore Registration Token",
                " Please click on this url to confirm your email : " + url));
        return userMapper.UserToUserDto(savedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> userMapper.UserToUserDto(u)).toList();
    }

    @Override
    public UserDto updateUser(Long id, UserDto dto) {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Email not found"));
        if(!dbUser.isEnabled())
            throw new UserNotActivatedException("User not activated");
        Optional.ofNullable(dto.getEmail()).ifPresent(data -> {
            if(EmailValidation.isValidEmail(data))
                dbUser.setEmail(dto.getEmail());
            else throw new InvalidUserDataException("Email syntax is invalid");
        });
        Optional.ofNullable(dto.getRole()).ifPresent(data -> {
                dbUser.setRole(dto.getRole());
        });
        userRepository.save(dbUser);
        return userMapper.UserToUserDto(dbUser);
    }

    public void saveVerificationTokenForUSer(UserDto dto, String token) {
        VerificationToken verificationToken = new VerificationToken(userMapper.UserDtoToUser(dto), token);
        verificationTokenRepository.save(verificationToken);
    }

    public void saveVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    public void saveChangePasswordVerificationToken(User user, String token) {
        ChangePasswordVerificationToken changeToken = new ChangePasswordVerificationToken(user, token);
        changePasswordVerificationTokenRepository.save(changeToken);
    }

    public void saveResetVerificationToken(User user, String token) {
        ResetVerificationToken resetToken = new ResetVerificationToken(user, token);
        resetVerificationTokenRepository.save(resetToken);
    }

    @Override
    public CustomResponse validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null)
            return new CustomResponse(UserUtils.RESET_TOKEN_NOT_FOUND, "RESET_TOKEN_NOT_FOUND");
        else {
            User user = verificationToken.getUser();
            if (TokenExpirationTime.calculateDifferenceTime(verificationToken.getExpirationTime(), LocalDateTime.now()) == 1) {
                user.setEnabled(true);
                userRepository.save(user);
                return new CustomResponse(UserUtils.SUCCESSFUL, "SUCCESSFUL");
            } else {
                verificationTokenRepository.deleteById(verificationToken.getId());
                return new CustomResponse(UserUtils.REGISTRATION_TOKEN_EXPIRED, "REGISTRATION_TOKEN_EXPIRED");
            }
        }
    }

    @Override
    public CustomResponse resendVerificationToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->new UserNotFoundException("User not found"));
        if (user.getTokens() == UserUtils.MAX_NO_OF_TOKENS)
            return new CustomResponse(UserUtils.Max_NUMBER_OF_TOKENS, "Max_NUMBER_OF_TOKENS");
        VerificationToken verificationToken = verificationTokenRepository.findByUserId(user.getId());
        if(verificationToken != null)
            verificationTokenRepository.deleteById(verificationToken.getId());
        String token = UUID.randomUUID().toString();
        saveVerificationToken(user, token);
        String url = "http://" + infoServiceImpl.getMyappServer() + ":" + infoServiceImpl.getServerPort()
                + "/v1/users/newToken?token=" + token;
        producer.sendMessage(new Mail(email,"BookStore Registration Resend Token",
                " Please click on this url to confirm your email : " + url));
        return new CustomResponse(0, "Successful");
    }

    @Override
    public CustomResponse changePassword(ChangePasswordDto changePasswordDto) {
        if (changePasswordDto.getOldPassword() == null
                || changePasswordDto.getNewPassword() == null
                || changePasswordDto.getMatchingPassword() == null
                || changePasswordDto.getEmail() == null)
            throw new NullPointerException("Change password info can not be null");
        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getMatchingPassword())) {
            return new CustomResponse(UserUtils.NEW_PASSWORDS_NOT_MATCHING, "PASSWORDS_NOT_MATCHING");
        }
        if(!PasswordValidator.isPasswordStrong(changePasswordDto.getNewPassword()))
            throw new WeakPasswordException("Password should be at least 8 characters, one Capital, number and" +
                    "                    a special character");
        User user = userRepository.findByEmail(changePasswordDto.getEmail()).orElseThrow(
                () ->new UserNotFoundException("User not exist"));
        if (!user.getPassword().equals(changePasswordDto.getOldPassword())) {
            return new CustomResponse(UserUtils.OLD_PASSWORD_NOT_MATCHING_WITH_DB, "OLD_PASSWORD_NOT_MATCHING");
        } else {
            user.setPassword(changePasswordDto.getNewPassword());
            userRepository.save(user);
        }
        return new CustomResponse(0, "SUCCESSFUL");
    }

    @Override
    public CustomResponse requestResetPassword(RequestResetPasswordDto requestResetPasswordDto) {
        String token = null;
        if (requestResetPasswordDto == null
                || requestResetPasswordDto.getEmail() == null)
            throw new NullPointerException("Reset password data can not be null");
        User user = userRepository.findByEmail(requestResetPasswordDto.getEmail()).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        ResetVerificationToken resetToken = resetVerificationTokenRepository.findByUserId(user.getId());
        if (resetToken != null)
            token = resetToken.getToken();
        else {
            token = UUID.randomUUID().toString();
        }
        saveResetVerificationToken(user, token);
        // send email
        String url = "http://" + infoServiceImpl.getMyappServer() + ":" + infoServiceImpl.getServerPort()
                + "/v1/users/resetPassword?token=" + token;
        producer.sendMessage(new Mail(user.getEmail(), "BookStore- Token for Request Reset Password"
                , " Please click on this url to reset your password : " + url));
        return new CustomResponse(UserUtils.SUCCESSFUL, "Email Sent");
    }

    @Override
    public CustomResponse resetPassword(ResetPasswordDto resetPasswordDto) {
        if (resetPasswordDto == null
                || resetPasswordDto.getEmail() == null
                || resetPasswordDto.getPassword() == null
                || resetPasswordDto.getMatchingPassword() == null
                || resetPasswordDto.getToken() == null)
            throw new NullPointerException("Reset password data can not be null");
        if (!resetPasswordDto.getPassword().equals(resetPasswordDto.getMatchingPassword()))
            throw new PasswordNotMatchingException("Passwords not matched");
        User user = userRepository.findByEmail(resetPasswordDto.getEmail()).orElseThrow(
                ()-> new UserNotFoundException("User email not found"));
        ResetVerificationToken resetVerificationToken = resetVerificationTokenRepository.findByUserId(user.getId());
        if (resetVerificationToken == null)
            return new CustomResponse(UserUtils.RESET_TOKEN_NOT_FOUND, "RESET_TOKEN_NOT_FOUND");
        if (!resetVerificationToken.getToken().equals(resetPasswordDto.getToken()))
            return new CustomResponse(UserUtils.RESET_TOKEN_NOT_MATCHED, "RESET_TOKEN_NOT_MATCHED");
        if (TokenExpirationTime.calculateDifferenceTime(resetVerificationToken.getExpirationTime(), LocalDateTime.now()) != 1)
            return new CustomResponse(UserUtils.RESET_TOKEN_EXPIRED, "RESET_TOKEN_EXPIRED");
        user.setPassword(resetPasswordDto.getPassword());
        userRepository.save(user);
        return new CustomResponse(0, "SUCCESSFUL");
    }
}
