package com.projectNoBay.NoBay.controller;

import com.projectNoBay.NoBay.DTO.AdminDTO;
import com.projectNoBay.NoBay.model.Admin;
import com.projectNoBay.NoBay.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public List<AdminDTO> findAllAdmins(){
        List<Admin> adminList = adminService.findAllAdmins();
        System.out.println(adminList);
        List<AdminDTO> adminDTO = adminList.stream().map(admin ->
                modelMapper.map(admin,AdminDTO.class)).collect(Collectors.toList());
        return adminDTO;
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity findAdminById(@PathVariable Long id){
        Optional<Admin> admin = adminService.findAdminById(id);
        AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(adminDTO);
    }

    @GetMapping("/findByName{name}")
    public ResponseEntity findAdminByName(@PathVariable String name){
        List<Admin> admins = adminService.findAdminByName(name);
        List<AdminDTO> adminDTO = admins.stream().map(admin ->
                modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(adminDTO);
    }

    @DeleteMapping("/delete")
    public AdminDTO deleteAdmin(@RequestBody Admin admin){
        Admin admin1 = adminService.deleteAdmin(admin);
        AdminDTO adminDTO = modelMapper.map(admin1, AdminDTO.class);

        return adminDTO;
    }

    @DeleteMapping("/deleteById{id}")
    public AdminDTO deleteAdminById(@PathVariable Long id){
        Optional<Admin> admin1 = adminService.findAdminById(adminService.deleteByIdAdmin(id));
        AdminDTO adminDTO = modelMapper.map(admin1, AdminDTO.class);

        return adminDTO;
    }

    @PutMapping("/updateName{name}")
    public AdminDTO updateAdminName(@RequestBody Admin admin, @PathVariable String name){
        Admin admin1 = adminService.updateAdminName(admin, name);
        AdminDTO adminDTO = modelMapper.map(admin1, AdminDTO.class);
        return adminDTO;
    }

    @PutMapping("/create")
    public AdminDTO createAdmin(@RequestBody Admin admin) {
        Admin admin1 = adminService.createAdmin(admin);
        AdminDTO adminDTO = modelMapper.map(admin1, AdminDTO.class);
        return adminDTO;
    }
}
