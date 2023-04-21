package com.projectNoBay.NoBay.mapper;

import com.projectNoBay.NoBay.DTO.AdminDTO;
import com.projectNoBay.NoBay.model.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public AdminDTO mapModelToDto(Admin admin){
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setAdminName(admin.getAdminName());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPassword(admin.getPassword());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setFirstName(admin.getFirstName());

        return adminDTO;
    }

    public Admin mapDtoToModel(AdminDTO adminDTO){
        Admin admin =  new Admin();
        admin.setId(adminDTO.getId());
        admin.setAdminName(adminDTO.getAdminName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        admin.setLastName(adminDTO.getLastName());
        admin.setFirstName(adminDTO.getFirstName());

        return admin;
    }
}
