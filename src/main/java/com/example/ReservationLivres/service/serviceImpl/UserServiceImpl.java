package com.example.ReservationLivres.service.serviceImpl;


import com.example.ReservationLivres.Repository.AppUserRepository;
import com.example.ReservationLivres.Repository.RoleRepository;
import com.example.ReservationLivres.entity.AppUser;
import com.example.ReservationLivres.entity.Role;
import com.example.ReservationLivres.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<?> addUser(AppUser userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        Optional<Role> roleOptional = roleRepository.findById(2);
       if (roleOptional.isPresent()) {
           Role notNullRole = roleOptional.get();
           userInfo.setRole(notNullRole);
       }
        appUserRepository.save(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public AppUser fidUserByUsername(String username) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(username);

        // Check if the result is present before calling .get()
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        } else {
            // Handle the case when the user is not found, e.g., return null or throw an exception
            // You can customize this based on your application's requirements
            return null;
        }
    }

    @Override
    public void DeleteUser(Integer id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public boolean findUserById(Integer id) {
        if (appUserRepository.findById(id).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    public AppUser getUserById(Integer id) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        // Use orElse to provide a default value if optional is empty
        return optionalAppUser.orElse(null); // You can provide a default AppUser or return null
    }

}