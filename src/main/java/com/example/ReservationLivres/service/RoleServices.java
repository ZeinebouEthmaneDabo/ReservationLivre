package com.example.ReservationLivres.service;



import com.example.ReservationLivres.entity.Role;

import java.util.List;


public interface RoleServices {
    
    public List<Role> getAllRoles();

    public Role getRoleById(long id);

    public Role createRole(Role role);

    public Role updateRole(Role updatedRole);
    
    public void deleteRole(long id);
}