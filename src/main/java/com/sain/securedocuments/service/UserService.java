package com.sain.securedocuments.service;


import com.sain.securedocuments.entity.RolesEntity;

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);
    RolesEntity getRoleName(String name);
}
