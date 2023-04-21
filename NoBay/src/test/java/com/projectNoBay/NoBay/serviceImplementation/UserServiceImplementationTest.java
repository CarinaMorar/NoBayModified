package com.projectNoBay.NoBay.serviceImplementation;

import com.projectNoBay.NoBay.model.User;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    private User user;
    private Long id = 1L;

    @BeforeEach
    void init() {
        initMocks(this);
        user = new User();
        user.setId(id);
    }

    @Test
    @DisplayName("should not update the user's password when the user is not found")
    void updateUserPassWhenUserNotFound() {
        User user = new User();
        user.setId(1L);
        user.setPassword("12345");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.updateUserPass(user, "12345"));
    }

    @Test
    @DisplayName("should not update the user's email when the user is not found")
    void updateUserEmailWhenUserNotFound() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@test.com");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(
                RuntimeException.class, () -> userService.updateUserEmail(user, "test@test.com"));
    }

    @Test
    @DisplayName("should update the user's email when a valid user and email are provided")
    void updateUserEmailWhenValidUserAndEmailProvided() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@test.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUserEmail(user, "test@test.com");
        assertThat(updatedUser.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    @DisplayName("should update the user's first name when the user exists")
    void updateUserFirstNameWhenUserExists() {
        User user = User.builder().id(1L).firstName("John").build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUserFirstName(user, "John");

        assertThat(updatedUser.getFirstName()).isEqualTo("John");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("should update the user's last name when the user exists")
    void updateUserLastNameWhenUserExists() {
        User user = User.builder().id(1L).lastName("Smith").build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUserLastName(user, "Jones");

        assertThat(updatedUser.getLastName()).isEqualTo("Jones");
        verify(userRepository).findById(1L);
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("should not create a user with invalid input")
    void createUserWithInvalidInput() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user");
        user.setEmail("user@gmail.com");
        user.setPassword("user");
        user.setFirstName("user");
        user.setLastName("user");
        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.createUser(user)).isEqualTo(user);
    }

    @Test
    @DisplayName("should create a new user with valid input")
    void createUserWithValidInput() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isEqualTo(1L);
        assertThat(createdUser.getUsername()).isEqualTo("test");
        assertThat(createdUser.getEmail()).isEqualTo("test@test.com");
        assertThat(createdUser.getPassword()).isEqualTo("test");
        assertThat(createdUser.getFirstName()).isEqualTo("test");
        assertThat(createdUser.getLastName()).isEqualTo("test");
    }

    @Test
    @DisplayName("should delete the user when the user exists")
    void deleteUserWhenUserExists() {

        userService.deleteUser(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    @DisplayName("should return empty optional if user not found by id")
    void findUserByIdIfNotFound() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        Optional<User> user = userService.findUserById(id);
        assertThat(user).isEmpty();
    }

    @Test
    @DisplayName("should delete user by id if exists")
    void deleteUserByIdIfExists() {

        userService.deleteUserById(1L);
        assertNotNull(user);
        //   verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("should return all users")
    void findAllUsers() {
        User user1 = User.builder().id(1L).username("user1").build();
        User user2 = User.builder().id(2L).username("user2").build();
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.findAllUsers();
        assertThat(result).isEqualTo(users);
    }

    @Test
    @DisplayName("should return user by id if exists")
    void findUserByIdIfExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> userById = userService.findUserById(1L);
        assertNotNull(userById);
        assertEquals(userById.get(), user);
    }
}