package com.ServicioProducto.service;

import com.ServicioProducto.dto.AuthLoginRqDto;
import com.ServicioProducto.entities.UserJwt;
import com.ServicioProducto.repository.IUserJwtRepository;
import com.ServicioProducto.util.JwtUtils;
import com.ServicioProducto.dto.AuthRsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IUserJwtRepository userJwtRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserJwt userJwt = userJwtRepository.findUserJwtByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + "No existe"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userJwt.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        return new User(userJwt.getUsername(), userJwt.getPassword(), userJwt.isEnable(), userJwt.isAccountNoExpired(), userJwt.isCredentialNoExpired(), userJwt.isAccountNoLocked(), authorityList);

    }

    public AuthRsDto loginUser(AuthLoginRqDto authLoginRequest){
        String username = authLoginRequest.username;
        String password = authLoginRequest.password;

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthRsDto authResponse = new AuthRsDto(username,"Usuario logeado con exito.", accessToken, true);

        return authResponse;
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null){
            throw new BadCredentialsException("Usuario o Password invalidos.");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Usuario o Password invalidos.");
        }

        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(), userDetails.getAuthorities());
    }
}