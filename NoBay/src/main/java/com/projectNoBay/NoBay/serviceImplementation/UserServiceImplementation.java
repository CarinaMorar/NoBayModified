package com.projectNoBay.NoBay.serviceImplementation;

import com.projectNoBay.NoBay.model.Orders;
import com.projectNoBay.NoBay.model.User;
import com.projectNoBay.NoBay.repository.UserRepository;
import com.projectNoBay.NoBay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }
    @Override
    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }
    @Override
    public List<User> findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
    @Override
    public Long deleteUserById(Long id){
        userRepository.deleteById(id);
        return id;
    }
    @Override
    public User deleteUser(User user){
        userRepository.delete(user);
        return user;
    }
    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }
    @Override
    public User updateUserName(User user,String name) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setUsername(name);
        return userRepository.save(existingUser);
    }
    @Override
    public User updateUserLastName(User user,String name) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setLastName(name);
        return userRepository.save(existingUser);
    }
    @Override
    public User updateUserFirstName(User user,String name) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setFirstName(name);
        return userRepository.save(existingUser);
    }
    @Override
    public User updateUserEmail(User user,String email) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setEmail(email);
        return userRepository.save(existingUser);
    }
    @Override
    public User updateUserPass(User user,String pass) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setPassword(pass);
        return userRepository.save(existingUser);
    }

    public User updateOrdersList (User user, List<Orders> orders){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setOrders(orders);
        return userRepository.save(existingUser);
    }

    @Override
    public String logIn(User user1, String parola, String email)
    {
        User user=userRepository.findById(user1.getId()).orElse(null);
        if(user.getPassword().equals(parola) && user.getEmail()==email)
            return  "Succes login Client";
        else
            return "Try Again or become a client";
    }
/*@Override
    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setUsername(user.getUsername());
        existingUser.setLastName(user.getLastName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }*/
}
