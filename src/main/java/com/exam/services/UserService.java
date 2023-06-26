package com.exam.services;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;

import java.util.Set;

public interface UserService {
    //create user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    //get user by username
    public User getuser(String username);
    //delete user by id
    public void deleteUser(Long userId);
}
