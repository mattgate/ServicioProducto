package com.ServicioProducto.controller;

import com.ServicioProducto.dto.AuthLoginRqDto;
import com.ServicioProducto.dto.AuthRsDto;
import com.ServicioProducto.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @PostMapping("/login")
    public ResponseEntity<AuthRsDto> login(@RequestBody AuthLoginRqDto userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
}
