package com.ServicioProducto.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
@AllArgsConstructor
@NoArgsConstructor
public class AuthRsDto {

    public String username;
    public String message;
    public String jwt;
    public boolean status;
}
