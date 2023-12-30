package com.example.ReservationLivres.web.ControllerApi;


import com.example.ReservationLivres.dto.AuthRequest;
import com.example.ReservationLivres.dto.RegisterModel;
import com.example.ReservationLivres.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(Constants.AUTH)
public interface PublicControllerApi {

        @PostMapping(Constants.REGISTER)
        public ResponseEntity<?> register(@RequestBody RegisterModel registerModel);

        @PostMapping(Constants.LOGIN)
        public ResponseEntity<?> login(@RequestBody AuthRequest authRequest);
}