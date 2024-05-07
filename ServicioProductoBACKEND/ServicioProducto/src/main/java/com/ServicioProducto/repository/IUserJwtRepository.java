package com.ServicioProducto.repository;

import com.ServicioProducto.entities.UserJwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserJwtRepository  extends JpaRepository<UserJwt, Long> {
    Optional<UserJwt> findUserJwtByUsername(String username);

}
