package com.sain.securedocuments.service.impl;

import com.sain.securedocuments.entity.ConfirmationEntity;
import com.sain.securedocuments.entity.CredentialEntity;
import com.sain.securedocuments.entity.RolesEntity;
import com.sain.securedocuments.entity.UserEntity;
import com.sain.securedocuments.enumeration.Authority;
import com.sain.securedocuments.enumeration.EventType;
import com.sain.securedocuments.event.UserEvent;
import com.sain.securedocuments.repository.ConfirmationRepository;
import com.sain.securedocuments.repository.CredentialsRepository;
import com.sain.securedocuments.repository.RoleRepository;
import com.sain.securedocuments.repository.UserRepository;
import com.sain.securedocuments.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.sain.securedocuments.utils.UserUtils.createUserEntity;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialsRepository credintialsRepository;
    private final ConfirmationRepository confirmationRepository;
    //private final BCryptEncoder encoder;
    private final ApplicationEventPublisher publisher;


    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepository.save(createNewUser(firstName,lastName,email));
        var credentialEntity = new CredentialEntity(password,userEntity);
        credintialsRepository.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key",confirmationEntity.getKey())));
    }

    @Override
    public RolesEntity getRoleName(String name) {
        var role = roleRepository.findByNameIgnoreCase(name);
        return role.orElseThrow(() -> new RuntimeException("Role not found"));
    }

    private UserEntity createNewUser(String firstName, String lastName, String email) {
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }
}
