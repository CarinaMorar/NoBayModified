package com.projectNoBay.NoBay.serviceImplementation;

import com.projectNoBay.NoBay.model.Admin;
import com.projectNoBay.NoBay.repository.AdminRepository;
import com.projectNoBay.NoBay.repository.BeautyProductsRepository;
import com.projectNoBay.NoBay.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplementationTest {
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private BeautyProductsRepository beautyProductsRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminServiceImplementation adminService;

    private Admin admin;
    private Long id;
    @BeforeEach
    void init() {
        initMocks(this);
        admin=new Admin();
        admin.setId(id);
    }
    @Test
    @DisplayName("should update the admin password when the admin is found")
    void updateAdminPassWhenAdminIsFound() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setAdminName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin updatedAdmin = adminService.updateAdminPass(admin, "admin");

        assertThat(updatedAdmin.getPassword()).isEqualTo("admin");
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    @DisplayName("should update the admin email when the admin exists")
    void updateAdminEmailWhenAdminExists() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setEmail("admin@gmail.com");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin updatedAdmin = adminService.updateAdminEmail(admin, "admin@gmail.com");

        assertThat(updatedAdmin.getEmail()).isEqualTo("admin@gmail.com");
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    @DisplayName("should update the admin's last name when a valid admin and name are provided")
    void updateAdminLastNameWhenValidAdminAndNameProvided() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setLastName("Smith");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin updatedAdmin = adminService.updateAdminLastName(admin, "Jones");

        assertThat(updatedAdmin.getLastName()).isEqualTo("Jones");
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    @DisplayName("should update the admin's first name when the admin is found")
    void updateAdminFirstNameWhenAdminIsFound() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setAdminName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin updatedAdmin = adminService.updateAdminFirstName(admin, "Jane");

        assertThat(updatedAdmin.getFirstName()).isEqualTo("Jane");
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    @DisplayName("should update the admin's first name when the admin exists")
    void updateAdminFirstNameWhenAdminExists() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setAdminName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin updatedAdmin = adminService.updateAdminFirstName(admin, "Jane");

        assertThat(updatedAdmin.getFirstName()).isEqualTo("Jane");
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    @DisplayName("should update the admin name when the admin exists")
    void updateAdminNameWhenAdminExists() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setAdminName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin updatedAdmin = adminService.updateAdminName(admin, "admin1");

        assertThat(updatedAdmin.getAdminName()).isEqualTo("admin1");
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    @DisplayName("should delete the admin by id successfully")
    void deleteByIdAdminSuccessfully() {
        adminService.deleteByIdAdmin(1L);
        verify(adminRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("should delete admin")
    void deleteAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setAdminName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");

        adminService.deleteAdmin(admin);

        verify(adminRepository, times(1)).delete(admin);
    }

    @Test
    @DisplayName("should return all admins")
    void findAllAdmins() {
        Admin admin1 = new Admin();
        Admin admin2 = new Admin();
        List<Admin> admins = Arrays.asList(admin1, admin2);
        when(adminRepository.findAll()).thenReturn(admins);
        List<Admin> result = adminService.findAllAdmins();
        assertThat(result).hasSize(2);
        verify(adminRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("should create a new admin")
    void createAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setAdminName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");

        when(adminRepository.save(admin)).thenReturn(admin);

        Admin createdAdmin = adminService.createAdmin(admin);

        assertThat(createdAdmin).isNotNull();
        assertThat(createdAdmin.getId()).isEqualTo(1L);
        assertThat(createdAdmin.getAdminName()).isEqualTo("admin");
        assertThat(createdAdmin.getEmail()).isEqualTo("admin@gmail.com");
        assertThat(createdAdmin.getPassword()).isEqualTo("admin");
        assertThat(createdAdmin.getFirstName()).isEqualTo("admin");
        assertThat(createdAdmin.getLastName()).isEqualTo("admin");
    }

    @Test
    @DisplayName("should return admin by id")
    void findAdminById() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setAdminName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        Optional<Admin> foundAdmin = adminService.findAdminById(1L);

        assertThat(foundAdmin).isNotEmpty();
        assertThat(foundAdmin.get().getId()).isEqualTo(1L);
        assertThat(foundAdmin.get().getAdminName()).isEqualTo("admin");
        assertThat(foundAdmin.get().getEmail()).isEqualTo("admin@gmail.com");
        assertThat(foundAdmin.get().getPassword()).isEqualTo("admin");
        assertThat(foundAdmin.get().getFirstName()).isEqualTo("admin");
        assertThat(foundAdmin.get().getLastName()).isEqualTo("admin");
    }
}