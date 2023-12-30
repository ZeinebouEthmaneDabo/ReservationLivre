package com.example.ReservationLivres;

import com.example.ReservationLivres.Repository.RoleRepository;
import com.example.ReservationLivres.entity.Role;
import com.example.ReservationLivres.service.RoleServices;
import com.example.ReservationLivres.service.serviceImpl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServices roleService = new RoleServiceImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRoles() {
        // Arrange
        List<Role> roles = Arrays.asList(new Role(), new Role());
        when(roleRepository.findAll()).thenReturn(roles);

        // Act
        List<Role> result = roleService.getAllRoles();

        // Assert
        assertEquals(roles, result);
    }

    @Test
    void testGetRoleById_ExistingId() {
        // Arrange
        long id = 1L;
        Role role = new Role();
        when(roleRepository.findById(id)).thenReturn(Optional.of(role));

        // Act
        Role result = roleService.getRoleById(id);

        // Assert
        assertEquals(role, result);
    }

    @Test
    void testGetRoleById_NonExistingId() {
        // Arrange
        long id = 1L;
        when(roleRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> roleService.getRoleById(id));
    }

    @Test
    void testCreateRole() {
        // Arrange
        Role role = new Role();
        when(roleRepository.save(role)).thenReturn(role);

        // Act
        Role result = roleService.createRole(role);

        // Assert
        assertEquals(role, result);
    }

    @Test
    void testUpdateRole() {
        // Arrange
        Role updatedRole = new Role();
        when(roleRepository.save(updatedRole)).thenReturn(updatedRole);

        // Act
        Role result = roleService.updateRole(updatedRole);

        // Assert
        assertEquals(updatedRole, result);
    }

    @Test
    void testDeleteRole_ExistingId() {
        // Arrange
        long id = 1L;
        Role role = new Role();
        when(roleRepository.findById(id)).thenReturn(Optional.of(role));

        // Act
        roleService.deleteRole(id);

        // Assert
        verify(roleRepository, times(1)).delete(role);
    }

    @Test
    void testDeleteRole_NonExistingId() {
        // Arrange
        long id = 1L;
        when(roleRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> roleService.deleteRole(id));
    }
}
