package com.example.ReservationLivres;

import com.example.ReservationLivres.Repository.AppUserRepository;
import com.example.ReservationLivres.Repository.RoleRepository;
import com.example.ReservationLivres.entity.AppUser;
import com.example.ReservationLivres.entity.Role;
import com.example.ReservationLivres.service.UserServices;
import com.example.ReservationLivres.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testAddUser() {
        AppUser user = new AppUser();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        Role role = new Role();
        role.setId(2);

        when(roleRepository.findById(2)).thenReturn(Optional.of(role));
        when(passwordEncoder.encode("testPassword")).thenReturn("encodedPassword");

        ResponseEntity<?> response = userService.addUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(appUserRepository, times(1)).save(user);
    }

    @Test
    public void testFindUserByUsername() {
        String username = "testUser";
        AppUser user = new AppUser();
        user.setUsername(username);

        when(appUserRepository.findByUsername(username)).thenReturn(Optional.of(user));

        AppUser foundUser = userService.fidUserByUsername(username);

        assertEquals(username, foundUser.getUsername());
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 1;

        userService.DeleteUser(userId);

        verify(appUserRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testFindUserById() {
        Integer userId = 1;

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(new AppUser()));

        assertTrue(userService.findUserById(userId));
    }

    @Test
    public void testGetUsers() {
        List<AppUser> userList = Arrays.asList(new AppUser(), new AppUser());

        when(appUserRepository.findAll()).thenReturn(userList);

        List<AppUser> users = userService.getUsers();

        assertEquals(userList.size(), users.size());
    }

    @Test
    public void testGetUserById() {
        Integer userId = 1;
        AppUser user = new AppUser();
        user.setId(userId);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(user));

        AppUser foundUser = userService.getUserById(userId);

        assertEquals(userId, foundUser.getId());
    }
}
