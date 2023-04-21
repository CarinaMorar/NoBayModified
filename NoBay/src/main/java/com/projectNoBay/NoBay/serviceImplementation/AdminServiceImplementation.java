package com.projectNoBay.NoBay.serviceImplementation;

import com.projectNoBay.NoBay.DTO.AdminDTO;
import com.projectNoBay.NoBay.mapper.AdminMapper;
import com.projectNoBay.NoBay.model.Admin;
import com.projectNoBay.NoBay.repository.AdminRepository;
import com.projectNoBay.NoBay.repository.BeautyProductsRepository;
import com.projectNoBay.NoBay.repository.OrderRepository;
import com.projectNoBay.NoBay.repository.UserRepository;
import com.projectNoBay.NoBay.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImplementation implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    private BeautyProductsRepository beautyProductsRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public AdminServiceImplementation(AdminRepository adminRepository, BeautyProductsRepository beautyProductsRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.adminRepository=adminRepository;
        this.beautyProductsRepository=beautyProductsRepository;
        this.userRepository=userRepository;
        this.orderRepository=orderRepository;
    }

    /*
    public void AdminServiceImplementation(AdminRepository adminRepository, BeautyProductsRepository beautyProductsRepository, UserRepository userRepository, OrderRepository orderRepository)
     {

     }

     */

     @Override
    public List<Admin> findAllAdmins() {
         return adminRepository.findAll();
     }
     @Override
     public Optional<Admin> findAdminById(Long id) {
         return adminRepository.findById(id);
     }
     @Override
     public List<Admin> findAdminByName(String name){
         return adminRepository.findAdminByAdminName(name);
     }
     @Override
     public Long deleteByIdAdmin(Long id){
         adminRepository.deleteById(id);
         return id;
     }
     @Override
     public Admin deleteAdmin(Admin admin){
         adminRepository.delete(admin);
         return admin;
     }
     @Override
     public Admin createAdmin(Admin admin){
         return adminRepository.save(admin);
     }

     @Override
    public Admin updateAdminName(Admin admin, String name){
        Admin existingAdmin = adminRepository.findById(admin.getId()).orElse(null);
        existingAdmin.setAdminName(name);
        return adminRepository.save(existingAdmin);
    }
    @Override
    public Admin updateAdminFirstName(Admin admin, String name){
        Admin existingAdmin = adminRepository.findById(admin.getId()).orElse(null);
        existingAdmin.setFirstName(name);
        return adminRepository.save(existingAdmin);
    }
    @Override
    public Admin updateAdminLastName(Admin admin, String name){
        Admin existingAdmin = adminRepository.findById(admin.getId()).orElse(null);
        existingAdmin.setLastName(name);
        return adminRepository.save(existingAdmin);
    }
    @Override
    public Admin updateAdminEmail(Admin admin, String email){
        Admin existingAdmin = adminRepository.findById(admin.getId()).orElse(null);
        existingAdmin.setEmail(email);
        return adminRepository.save(existingAdmin);
    }
    @Override
    public Admin updateAdminPass(Admin admin, String pass){
        Admin existingAdmin = adminRepository.findById(admin.getId()).orElse(null);
        existingAdmin.setPassword(pass);
        return adminRepository.save(existingAdmin);
    }

    @Override
    public String logIn(Admin admin1, String password, String email)
    {
        Admin adminToLogin = adminRepository.findById(admin1.getId()).orElse(null);
        System.out.println("aaaaaaaaaaaaaaaaaaaaa");
        if(adminToLogin == null)
            System.out.println("NAIBAAA");
        if(adminToLogin.getPassword().equals(password) && adminToLogin.getEmail().equals(email)) {
            return "Succes";
        }
        else
            return "Try Again";
    }

     /*@Override
     public Admin updateAdmin(Admin admin){
         Admin existingAdmin = adminRepository.findById(admin.getId()).orElse(null);
         existingAdmin.setAdminName(admin.getAdminName());
         existingAdmin.setFirstName(admin.getFirstName());
         existingAdmin.setLastName(admin.getLastName());
         existingAdmin.setEmail(admin.getEmail());
         existingAdmin.setPassword(admin.getPassword());
         //existingAdmin.setId(admin.getId());
         return adminRepository.save(existingAdmin);
     }*/
}
