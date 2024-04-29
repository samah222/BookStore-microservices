package com.samah.userservice.service.impl;

import com.samah.userservice.dto.UserDto;
import com.samah.userservice.dto.UserRegistrationDto;
import com.samah.userservice.entity.VerificationToken;
import com.samah.userservice.exception.PasswordNotMatchingException;
import com.samah.userservice.exception.UserNotFoundException;
import com.samah.userservice.entity.User;
import com.samah.userservice.mail.MailSenderService;
import com.samah.userservice.mapper.UserMapper;
import com.samah.userservice.repository.VerificationTokenRepository;
import com.samah.userservice.service.InfoService;
import com.samah.userservice.service.UserService;
import com.samah.userservice.utils.TokenExpirationTime;
import com.samah.userservice.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.samah.userservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private MailSenderService mailSenderService;
    @Autowired
    private InfoService infoService;
    UserMapper userMapper;

    @Override
    public UserDto getUserByName(String name) {
        Optional<User> user = userRepository.findByName(name);
        if (user.isPresent())
            return userMapper.UserToUserDto(user.get());
        else throw new UserNotFoundException("User not found");
    }

    @Override
    @Cacheable(value = "userById", key = "#id")
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return userMapper.UserToUserDto(user.get());
        else throw new UserNotFoundException("User not found");
        //return user.orElse(null);
    }

    @Override
    public UserDto addUser(UserRegistrationDto registrationDto) {
        //check fields
        if (registrationDto == null
                || registrationDto.getName() == null
                || registrationDto.getEmail() == null
                || registrationDto.getPassword() == null
                || registrationDto.getMatchingPassword() == null)
            throw new NullPointerException("User data should not be null");
        if (!registrationDto.getPassword().equals(registrationDto.getMatchingPassword()))
            throw new PasswordNotMatchingException("Passwords not matching");
        User user = userMapper.UserRegistrationDtoToUser(registrationDto);
        user.setTokens(user.getTokens() + 1);
        User savedUser = userRepository.save(user);
        String token = UUID.randomUUID().toString();
        saveVerificationTokenForUSer(savedUser, token);
        // send email
        String url = "http://" + infoService.getMyappServer() + ":" + infoService.getServerPort()
                + "/v1/users/verifyRegistration?token=" + token;
        mailSenderService.sendNewMail(savedUser.getEmail(), "BookStore Registration Token"
                , " Please click on this url to confirm your email : " + url);
        return userMapper.UserToUserDto(savedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> userMapper.UserToUserDto(u))
                .toList();
    }

    @Override
    public UserDto updateUser(Long id, UserDto dto) {
        //User dbUser = userRepository.findById(id).get(); //
        User user = userMapper.UserDtoToUser(dto);
        user.setId(id);
        if (!userRepository.existsById(user.getId()))
            throw new IllegalArgumentException("User does not exist");
        return userMapper.UserToUserDto(user);
    }

    @Override
    public void saveVerificationTokenForUSer(UserDto dto, String token) {

        VerificationToken verificationToken = new VerificationToken(userMapper.UserDtoToUser(dto), token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void saveVerificationTokenForUSer(User user, String token) {

        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public int validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null)
            return -1;
        else {
            User user = verificationToken.getUser();
            if (TokenExpirationTime.calculateDifferenceTime(verificationToken.getExpirationTime(), LocalDateTime.now()) == 1) {
                user.setEnabled(true);
                userRepository.save(user);
                return 1;
            } else {
                verificationTokenRepository.deleteById(verificationToken.getId());
                return 0;
            }
        }
    }

    @Override
    public String resendVerificationToken(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent())
            return null;
        if (user.get().getTokens() == UserUtils.MAX_NO_OF_TOKENS)
            return "Max number of tokens";
        String token = UUID.randomUUID().toString();
        saveVerificationTokenForUSer(user.get(), token);
        String url = "http://" + infoService.getMyappServer() + ":" + infoService.getServerPort()
                + "/v1/users/newToken?token=" + token;
        mailSenderService.sendNewMail(email, "BookStore Registration Resend Token"
                , " Please click on this url to confirm your email : " + url);

        return "Successful";
    }
}
