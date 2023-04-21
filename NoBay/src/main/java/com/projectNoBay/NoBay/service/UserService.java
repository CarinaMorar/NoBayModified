package com.projectNoBay.NoBay.service;

import com.projectNoBay.NoBay.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface UserService {
    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    List<User> findUserByUsername(String username);

    Long deleteUserById(Long id);

    User deleteUser(User user);

    User createUser(User user);

    User updateUserName(User user, String name);

    User updateUserLastName(User user, String name);

    User updateUserFirstName(User user, String name);

    User updateUserEmail(User user, String email);

    User updateUserPass(User user, String pass);

    String logIn(User user1, String parola, String email);

    //User updateUser(User user);
}
