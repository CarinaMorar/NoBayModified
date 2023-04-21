package com.projectNoBay.NoBay.repository;

import com.projectNoBay.NoBay.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findUserByUsername(String username);
}
