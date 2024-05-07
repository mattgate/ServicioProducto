package com.ServicioProducto.service;

import com.ServicioProducto.entities.Producto;
import com.ServicioProducto.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    IProductoRepository productoRepository;

    public void CrearProducto(Producto producto){
        productoRepository.crearProducto(producto.getNombre(), producto.getDescripcion(), producto.getExistencia(), producto.getPrecio());
    }

    public List<Producto> leerProductos() {
        return productoRepository.LeerProductos();
    }

    public Optional<Producto> leerProductoPorId(Long id) {
        return productoRepository.leerProductoPorId(id);
    }

    public void actualizaProductoPorId(Producto producto) {
        productoRepository.actualizaProductoPorId(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getExistencia(), producto.getPrecio());
    }

    public void eliminaProductoPorId(Long id) {
        productoRepository.eliminaProductoPorId(id);
    }
}
