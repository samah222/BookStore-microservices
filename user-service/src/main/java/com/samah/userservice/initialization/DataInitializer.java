package com.samah.userservice.initialization;

import com.samah.userservice.entity.Privilege;
import com.samah.userservice.entity.Role;
import com.samah.userservice.repository.PrivilegeRepository;
import com.samah.userservice.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer {
    Privilege privilegeFull = new Privilege(1, "FULL_PRIVILEGE");
    Privilege privilegeManageSystem = new Privilege(2, "MANAGE_SYSTEM_PRIVILEGE");
    Privilege privilegeManageUser = new Privilege(3, "MANAGE_USERS_PRIVILEGE");
    Privilege privilegeWrite = new Privilege(4, "WRITE_PRIVILEGE");
    Privilege privilegeRead = new Privilege(5, "READ_PRIVILEGE");
    Privilege privilegeLeast = new Privilege(6, "LEAST_PRIVILEGE");

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @PostConstruct
    public void initialize() {
        initializePrivileges();
        initializeRoles();
    }

    private void initializeRoles() {

        // Initialize roles here
        Role roleOwner = new Role(1, "OWNER", Set.of(privilegeFull));
        Role roleAdmin = new Role(2, "ADMIN", Set.of(privilegeWrite,privilegeRead,privilegeManageSystem));
        Role roleCashier = new Role(3, "CASHIER",Set.of(privilegeManageUser));
        Role roleUser = new Role(4, "USER",Set.of(privilegeLeast));
        roleRepository.save(roleOwner);
        roleRepository.save(roleCashier);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
    }

    private void initializePrivileges() {
        // Initialize privileges here
        privilegeRepository.save(privilegeFull);
        privilegeRepository.save(privilegeManageSystem);
        privilegeRepository.save(privilegeManageUser);
        privilegeRepository.save(privilegeWrite);
        privilegeRepository.save(privilegeRead);
        privilegeRepository.save(privilegeLeast);
    }
}

