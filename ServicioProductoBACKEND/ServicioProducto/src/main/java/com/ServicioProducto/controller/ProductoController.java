package com.ServicioProducto.controller;

import com.ServicioProducto.dto.ProductoDto;
import com.ServicioProducto.entities.Producto;
import com.ServicioProducto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/producto/")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crearProducto(@RequestBody ProductoDto productoDto){
        productoService.CrearProducto(
                Producto.builder()
                        .nombre(productoDto.getNombre())
                        .descripcion(productoDto.getDescripcion())
                        .existencia(productoDto.getExistencia())
                        .precio(productoDto.getPrecio())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<?> leerProductos(){

        List<ProductoDto> productoDtoList = productoService.leerProductos()
                .stream()
                .map(
                        producto -> ProductoDto.builder()
                                .nombre(producto.getNombre())
                                .descripcion(producto.getDescripcion())
                                .existencia(producto.getExistencia())
                                .precio(producto.getPrecio())
                                .build()
                ).toList();

        return ResponseEntity.ok(productoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> leerProductoPorId(@PathVariable("id") Long id){
        Optional<Producto> productoOptional = productoService.leerProductoPorId(id);

        if (productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            ProductoDto productoDto = ProductoDto.builder()
                    .nombre(producto.getNombre())
                    .descripcion(producto.getDescripcion())
                    .existencia(producto.getExistencia())
                    .precio(producto.getPrecio())
                    .build();

            return ResponseEntity.ok(productoDto);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizaProductoPorId(@PathVariable("id") Long id, @RequestBody ProductoDto productoDto){

        Optional<Producto> productoOptional = productoService.leerProductoPorId(id);

        if (productoOptional.isPresent()){
            Producto producto = productoOptional.get();

            /*Instant valDate = producto.getFechaDeModificacion().toInstant();

            Instant actualDate = Instant.now();

            Duration diferencia = Duration.between(valDate, actualDate).abs();

            if (diferencia.toHours() >= 12){*/
                producto.setNombre(productoDto.getNombre());
                producto.setDescripcion(productoDto.getDescripcion());
                producto.setExistencia(productoDto.getExistencia());
                producto.setPrecio(productoDto.getPrecio());

                productoService.actualizaProductoPorId(producto);

                return ResponseEntity.ok("Se actualizó el dato correctamente.");
/*            }else {
                return new ResponseEntity<>("No se puede actualizar el regitro porque No han pasado 12 hrs desde la ultima actualización.", HttpStatus.BAD_REQUEST);
            }*/

        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminaProductoPorId(@PathVariable("id") Long id){
        if (id != null){
            productoService.eliminaProductoPorId(id);
            return ResponseEntity.ok("Se eliminó el dato correctamente.");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


}
