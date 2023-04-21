package com.projectNoBay.NoBay.service;

import com.projectNoBay.NoBay.DTO.AdminDTO;
import com.projectNoBay.NoBay.model.Admin;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AdminService {

    List<Admin> findAllAdmins();

    Optional<Admin> findAdminById(Long id);

    List<Admin> findAdminByName(String name);

    Long deleteByIdAdmin(Long id);

    Admin deleteAdmin(Admin admin);

    Admin createAdmin(Admin admin);

    Admin updateAdminName(Admin admin, String name);

    Admin updateAdminFirstName(Admin admin, String name);

    Admin updateAdminLastName(Admin admin, String name);

    Admin updateAdminEmail(Admin admin, String email);

    Admin updateAdminPass(Admin admin, String pass);

    String logIn(Admin admin1, String password, String email);

    //Admin updateAdmin(Admin admin);
}
