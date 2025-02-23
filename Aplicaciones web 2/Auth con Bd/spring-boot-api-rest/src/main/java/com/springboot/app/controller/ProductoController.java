package com.springboot.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.Producto;
import com.springboot.app.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listar(){
		return service.listarProductos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listaPorID(@PathVariable Long id){
		return service.listarProductosPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@Validated @RequestBody Producto producto){
		return service.agregarProductos(producto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody Producto producto,@PathVariable Long id){
		return service.editarProductos(producto,id);
	}
	
	@DeleteMapping
	("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
		return service.eliminarProductos(id);
	}
	
	@GetMapping("/enable")
	public ResponseEntity<Map<String, Object>> listarPorEnable(){
		return service.listarProductosEnable();
	}
	
	@PutMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> eliminarPorEnable(@PathVariable Long id){
		return service.eliminarProductosEnable(id);
	}
}
