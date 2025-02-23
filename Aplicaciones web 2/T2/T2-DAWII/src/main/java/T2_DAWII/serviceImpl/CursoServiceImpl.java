package T2_DAWII.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import T2_DAWII.model.Cursos;
import T2_DAWII.repository.CursosRepository;
import T2_DAWII.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService{
	
	@Autowired
	private CursosRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listarCursos() {
	    Map<String, Object> respuesta = new HashMap<>();    
	    List<Cursos> cursos = dao.findAll();
	    
	    if (!cursos.isEmpty()) {
	        respuesta.put("mensaje", "Lista de cursos");
	        respuesta.put("cursos", cursos);
	        respuesta.put("fecha", new Date());    
	        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No existen registros");
	        respuesta.put("fecha", new Date());    
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}


	
}
