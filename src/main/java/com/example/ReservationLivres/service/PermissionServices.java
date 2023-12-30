package com.example.ReservationLivres.service;



import com.example.ReservationLivres.entity.Permission;

import java.util.List;


public interface PermissionServices {
    
    public List<Permission> getAllPermissions();

    public Permission createPermission(Permission permission);

    public Permission getPermissionById(long id);

    public Permission updatePermission(long id, Permission updatedPermission);

    public Boolean deletePermission(long id);
}