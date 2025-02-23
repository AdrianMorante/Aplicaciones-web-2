package T1_Pregunta02y03.Service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import T1_Pregunta02y03.Model.Empleados;

@FeignClient(name = "T1-Pregunta01", url = "http://localhost:8090/api/empleados")
public interface EmpleadosService {
		@GetMapping
	    public ResponseEntity<Map<String, Object>> listarEmpleados();

	    @GetMapping("/{id}")
	    public ResponseEntity<Map<String, Object>> listarEmpleadosPorId(@PathVariable("id") Long id);

	    @PostMapping
	    public ResponseEntity<Map<String, Object>> agregarEmpleados(@RequestBody Empleados empleados);

	    @PutMapping("/{id}")
	    public ResponseEntity<Map<String, Object>> editarEmpleados(@RequestBody Empleados empleados, @PathVariable("id") Long id);

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Map<String, Object>> eliminarEmpleados(@PathVariable("id") Long id);

}
