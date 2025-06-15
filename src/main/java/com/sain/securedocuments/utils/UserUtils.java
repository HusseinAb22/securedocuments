package com.sain.securedocuments.utils;

import com.sain.securedocuments.entity.RolesEntity;
import com.sain.securedocuments.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;
import static org.apache.commons.lang3.StringUtils.*;

public class UserUtils {
    public static UserEntity createUserEntity(String firstName, String lastName, String email, RolesEntity role){
        return UserEntity.builder().userId(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .lastLogin(LocalDateTime.now())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(false)
                .loginAttempts(0)
                .qrCodeSecret(EMPTY)
                .phone(EMPTY)
                .bio(EMPTY)
                .imageUrl("User-avatar.svg.png")
                .roles(role)
                .build();
    }
}
