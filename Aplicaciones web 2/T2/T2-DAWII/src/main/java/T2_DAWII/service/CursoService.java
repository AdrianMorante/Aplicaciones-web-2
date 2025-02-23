package T2_DAWII.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface CursoService {
	public ResponseEntity<Map<String, Object>> listarCursos();
	

}
