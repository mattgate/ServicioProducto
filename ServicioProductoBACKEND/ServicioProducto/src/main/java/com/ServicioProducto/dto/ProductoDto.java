package com.ServicioProducto.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDto {
    private String nombre;

    private String descripcion;

    private int existencia;

    private double precio;
}
