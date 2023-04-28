package com.projectNoBay.NoBay.repository;

import com.projectNoBay.NoBay.DTO.AdminDTO;
import com.projectNoBay.NoBay.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {

    List<Admin> findAdminByAdminName(String name);

}
