package com.projectNoBay.NoBay.controller;

import com.projectNoBay.NoBay.DTO.UserDTO;
import com.projectNoBay.NoBay.model.User;
import com.projectNoBay.NoBay.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public List<UserDTO> findAllUsers(){
        List<User> user1 = userService.findAllUsers();
        List<UserDTO> userDTO = user1.stream().map(user ->
                modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
        return userDTO;
    }
    @GetMapping("/findById{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        Optional<User> user = userService.findUserById(id);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/findByUsername{username}")
    public ResponseEntity findUserByName(@PathVariable String username){
        List<User> users = userService.findUserByUsername(username);
        List<UserDTO> userDTO = users.stream().map(user ->
                modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping("/delete")
    public UserDTO deleteUser(@RequestBody User user){
        User user1 = userService.deleteUser(user);
        UserDTO userDTO = modelMapper.map(user1, UserDTO.class);

        return userDTO;
    }

    @DeleteMapping("/deleteById{id}")
    public UserDTO deleteUserById(@PathVariable Long id){
        Optional<User> user = userService.findUserById(userService.deleteUserById(id));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @PutMapping("/updateUsername{username}")
    public UserDTO updateUsername(@RequestBody User user, @PathVariable String username){
        User user1 = userService.updateUserName(user, username);
        UserDTO userDTO = modelMapper.map(user1, UserDTO.class);
        return userDTO;
    }

    @PutMapping("/create")
    public UserDTO createUser(@RequestBody User user) {
        User user1 = userService.createUser(user);
        UserDTO userDTO = modelMapper.map(user1, UserDTO.class);
        return userDTO;
    }
}
