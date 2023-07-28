package com.immersion.useraudit.user.service;
import com.immersion.useraudit.user.domain.model.User;

import java.util.List;
public interface UserService {

        User addUser(User user);
        User updateUser(String id, User user);
        void deleteUser(String id);
        User viewUserDetails(String id);
        List<User> viewAllUsers();
    }

