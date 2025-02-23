package com.springboot.app.serviceImplement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.model.Producto;
import com.springboot.app.repository.ProductoRepository;
import com.springboot.app.service.ProductoService;

@Service
public class ProductoServiceImplement implements ProductoService{

	@Autowired
	private ProductoRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listarProductos() {
		Map<String,Object> respuesta = new HashMap<>();	
		List<Producto> productos = dao.findAll();
		
		if(!productos.isEmpty()) {
			respuesta.put("mensaje", "Lista de productos");
			respuesta.put("productos", productos);
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarProductosPorId(Long id) {
		Map<String,Object> respuesta = new HashMap<>();		
		Optional<Producto> productos = dao.findById(id);
		
		if(productos.isPresent()) {
			respuesta.put("productos", productos);
			respuesta.put("mensaje", "Busqueda correcta");
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.ok().body(respuesta);	
		}else {
			respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> agregarProductos(Producto producto) {
		Map<String,Object> respuesta = new HashMap<>();	
		dao.save(producto);
		respuesta.put("productos", producto);
		respuesta.put("mensaje", "Se añadio correctamente el producto");
		respuesta.put("status", HttpStatus.CREATED);
		respuesta.put("fecha", new Date());	
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);	
	}

	@Override
	public ResponseEntity<Map<String, Object>> editarProductos(Producto prd, Long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Producto> productoExiste = dao.findById(id);		
		if (productoExiste.isPresent()) {
			Producto producto = productoExiste.get();
            producto.setDescripcion(prd.getDescripcion());
            producto.setCantidad(prd.getCantidad());
            producto.setPrecio(prd.getPrecio());
            producto.setEnable(prd.getEnable());
            dao.save(producto);
            respuesta.put("productos", producto);
    		respuesta.put("mensaje", "Datos del producto modificado");
    		respuesta.put("status", HttpStatus.CREATED);
    		respuesta.put("fecha", new Date());	
    		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }else {
        	respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarProductos(Long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Producto> productoExiste = dao.findById(id);	
		if (productoExiste.isPresent()) {
			Producto producto = productoExiste.get();
			dao.delete(producto);
			respuesta.put("mensaje", "Eliminado correctamente");
			respuesta.put("status", HttpStatus.NO_CONTENT);
			respuesta.put("fecha", new Date());	

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}		

	}
	
	@Override
	public ResponseEntity<Map<String, Object>> eliminarProductosEnable(Long id) {
		Map<String,Object> respuesta = new HashMap<>();	
		Optional<Producto> productoExiste = dao.findById(id);	
		if (productoExiste.isPresent()) {
			Producto producto = productoExiste.get();
			producto.setEnable("N");
			dao.save(producto);
			respuesta.put("mensaje", "Eliminado correctamente");
			respuesta.put("status", HttpStatus.NO_CONTENT);
			respuesta.put("fecha", new Date());	

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros con ID: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}		

	}
	
	@Override
	public ResponseEntity<Map<String, Object>> listarProductosEnable() {
		Map<String,Object> respuesta = new HashMap<>();	
		List<Producto> productos = dao.findAllByEnable("S");
		
		if(!productos.isEmpty()) {
			respuesta.put("mensaje", "Lista de productos");
			respuesta.put("productos", productos);
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}


}
