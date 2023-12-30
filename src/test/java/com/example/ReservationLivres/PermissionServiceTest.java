package com.example.ReservationLivres;
import com.example.ReservationLivres.Repository.PermissionRepository;
import com.example.ReservationLivres.entity.Permission;
import com.example.ReservationLivres.service.PermissionServices;
import com.example.ReservationLivres.service.serviceImpl.PermissionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @Test
    public void testGetAllPermissions() {
        List<Permission> permissions = Arrays.asList(new Permission(), new Permission());

        when(permissionRepository.findAll()).thenReturn(permissions);

        List<Permission> result = permissionService.getAllPermissions();

        assertEquals(permissions.size(), result.size());
    }

    @Test
    public void testCreatePermission() {
        Permission permission = new Permission();
        permission.setName("TestPermission");

        when(permissionRepository.save(permission)).thenReturn(permission);

        Permission createdPermission = permissionService.createPermission(permission);

        assertNotNull(createdPermission);
        assertEquals("TestPermission", createdPermission.getName());
    }

    @Test
    public void testGetPermissionById() {
        long permissionId = 1;
        Permission permission = new Permission();
        permission.setId(permissionId);

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));

        Permission result = permissionService.getPermissionById(permissionId);

        assertNotNull(result);
        assertEquals(permissionId, result.getId());
    }

    @Test
    public void testGetPermissionByIdNotFound() {
        long nonExistentPermissionId = 999;

        when(permissionRepository.findById(nonExistentPermissionId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> permissionService.getPermissionById(nonExistentPermissionId));
    }

    @Test
    public void testUpdatePermission() {
        long permissionId = 1;
        Permission existingPermission = new Permission();
        existingPermission.setId(permissionId);
        existingPermission.setName("ExistingPermission");

        Permission updatedPermission = new Permission();
        updatedPermission.setName("UpdatedPermission");

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(existingPermission));
        when(permissionRepository.save(existingPermission)).thenReturn(existingPermission);

        Permission result = permissionService.updatePermission(permissionId, updatedPermission);

        assertNotNull(result);
        assertEquals("UpdatedPermission", result.getName());
    }

    @Test
    public void testUpdatePermissionNotFound() {
        long nonExistentPermissionId = 999;
        Permission updatedPermission = new Permission();

        when(permissionRepository.findById(nonExistentPermissionId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> permissionService.updatePermission(nonExistentPermissionId, updatedPermission));
    }

    @Test
    public void testDeletePermission() {
        long permissionId = 1;

        assertTrue(permissionService.deletePermission(permissionId));
        verify(permissionRepository, times(1)).deleteById(permissionId);
    }
}
