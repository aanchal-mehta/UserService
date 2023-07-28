package com.immersion.useraudit.user.service;

import com.immersion.useraudit.user.domain.model.User;
import com.immersion.useraudit.user.exception.UserNotFoundException;
import com.immersion.useraudit.user.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        log.info("Adding user: {}", user.getId());
        var correlationId = UUID.randomUUID();
        user.setCorrelationId(correlationId.toString());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        log.info("Updating user: {}", user.getId());
        var correlationId = UUID.randomUUID();
        user.setCorrelationId(correlationId.toString());
        var opExistingUser = userRepository.findById(id);
        if (opExistingUser.isPresent()) {
            var existingUser = opExistingUser.get();
            existingUser.setName(StringUtils.isNotBlank(user.getName()) ? user.getName().trim() : existingUser.getName());

            existingUser.setEmail(StringUtils.isNotBlank(user.getEmail()) ? user.getEmail().trim() : existingUser.getEmail());

            existingUser.setGender(StringUtils.isNotBlank(user.getGender()) ? user.getGender().trim() : existingUser.getGender());

            existingUser.setIsActive(user.getIsActive() != null ? user.getIsActive() : existingUser.getIsActive());

            existingUser.setContactNumber(StringUtils.isNotBlank(user.getContactNumber()) ? user.getContactNumber().trim() : existingUser.getContactNumber());

            existingUser.setDateOfBirth(StringUtils.isNotBlank(user.getDateOfBirth()) ? user.getDateOfBirth().trim() : existingUser.getDateOfBirth());

            existingUser.setRole(StringUtils.isNotBlank(user.getRole()) ? user.getRole().trim() : existingUser.getRole());
           return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById((id));
        if(user !=null)
        userRepository.deleteById(id);
        else {
          throw new UserNotFoundException("User not found with ID: " + id);
        }
    }

    @Override
    public User viewUserDetails(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public  List<User> viewAllUsers() {
        return userRepository.findAll();
    }

}
