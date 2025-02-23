package T1_Pregunta01.Controller;

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

import T1_Pregunta01.Model.Empleados;
import T1_Pregunta01.ServiceImpl.EmpleadosServiceImpl;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadosController {
	@Autowired

    private EmpleadosServiceImpl service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        return service.listarEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarPorId(@PathVariable Long id) {
        return service.listarEmpleadosPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@Validated @RequestBody Empleados empleados) {
        return service.agregarEmpleados(empleados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody Empleados empleados, @PathVariable Long id) {
        return service.editarEmpleados(empleados, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
        return service.eliminarEmpleados(id);
    }
    
    @GetMapping("/visible")
    public ResponseEntity<Map<String, Object>> listarPorVisible() {
        return service.listarEmpleadosVisible();
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminarPorVisible(@PathVariable Long id) {
        return service.eliminarEmpleadosVisible(id);
    }

}
