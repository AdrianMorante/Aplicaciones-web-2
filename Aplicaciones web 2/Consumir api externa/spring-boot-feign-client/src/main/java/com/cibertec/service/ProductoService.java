package com.cibertec.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cibertec.model.Producto;

@FeignClient(name = "spring-boot-feign-client", url = "http://localhost:8090/api/productos")
public interface ProductoService {

    @GetMapping
    public ResponseEntity<Map<String, Object>> getProductos();

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarProductosPorId(@PathVariable("id") Long id);

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarProductos(@RequestBody Producto producto);

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editarProductos(@RequestBody Producto producto, @PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarProductos(@PathVariable("id") Long id);
}
