package com.example.ReservationLivres.Repository;


import com.example.ReservationLivres.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(long id);
    Role findByName(String name);
}