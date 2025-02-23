package T1_Pregunta01.Service;

import java.util.Map;

import org.springframework.http.ResponseEntity;


import T1_Pregunta01.Model.Empleados;

public interface EmpleadoService {
	public ResponseEntity<Map<String, Object>> listarEmpleados();
	
	public ResponseEntity<Map<String, Object>> listarEmpleadosPorId(Long id);
	
	public ResponseEntity<Map<String, Object>> agregarEmpleados(Empleados empleados);
	
	public ResponseEntity<Map<String, Object>> editarEmpleados(Empleados empleados, Long id);
	
	public ResponseEntity<Map<String, Object>> eliminarEmpleados(Long id);	
	
	ResponseEntity<Map<String, Object>> eliminarEmpleadosVisible(Long id);
	
    ResponseEntity<Map<String, Object>> listarEmpleadosVisible();
	}
