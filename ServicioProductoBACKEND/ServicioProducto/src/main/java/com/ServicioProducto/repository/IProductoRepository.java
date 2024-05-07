package com.ServicioProducto.repository;

import com.ServicioProducto.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Modifying
    @Transactional
    @Query(value = "EXEC dbo.sp_crear_producto :nombre, :descripcion, :existencia, :precio", nativeQuery = true)
    void crearProducto(@Param("nombre") String nombre,
                        @Param("descripcion") String descripcion,
                        @Param("existencia") int existencia,
                        @Param("precio") double precio);

    @Query(value = "EXEC dbo.sp_leer_productos", nativeQuery = true)
    List<Producto> LeerProductos();

    @Query(value = "EXEC dbo.sp_leer_producto_por_id :id", nativeQuery = true)
    Optional<Producto> leerProductoPorId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "EXEC dbo.sp_actualiza_producto_por_id :id, :nombre, :descripcion, :existencia, :precio", nativeQuery = true)
    void actualizaProductoPorId(@Param("id") Long id,
                                @Param("nombre") String nombre,
                                @Param("descripcion") String descripcion,
                                @Param("existencia") int existencia,
                                @Param("precio") double precio);

    @Modifying
    @Transactional
    @Query(value = "EXEC dbo.sp_elimina_producto_por_id :id", nativeQuery = true)
    void eliminaProductoPorId(Long id);

}
