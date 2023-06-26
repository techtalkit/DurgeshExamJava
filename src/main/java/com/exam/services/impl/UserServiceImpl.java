package com.exam.services.impl;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    //Creating the user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User localuser=this.userRepository.findByUsername(user.getUsername());
        if(localuser !=null){
            System.out.println("User is already there");
            throw new Exception("User already present");
        }else{
            //user created
            for(UserRole ur: userRoles){
                roleRepository.save((ur.getRole()));
            }
            user.getUserRoles().addAll(userRoles);
            localuser=this.userRepository.save(user);
        }
        return localuser;
    }
    //Getting user by username
    @Override
    public User getuser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
