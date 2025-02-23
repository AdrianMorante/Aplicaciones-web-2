package T1_Pregunta02y03.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import T1_Pregunta02y03.Model.Empleados;
import T1_Pregunta02y03.Service.EmpleadosService;

@RestController
@RequestMapping("/api/feignclient")
public class EmpleadosController {

    @Autowired
    private EmpleadosService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarEmpleados() {
        try {
            ResponseEntity<Map<String, Object>> response = service.listarEmpleados();

            if (response != null && response.getBody() != null) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> empleadosData = (List<Map<String, Object>>) response.getBody().get("empleados");

                List<Map<String, Object>> empleadosModificados = empleadosData.stream().map(empleado -> {
                    String genero = (String) empleado.get("genero");
                    empleado.put("genero", "M".equals(genero) ? "Masculino" : "F".equals(genero) ? "Femenino" : genero);

                    String contrato = (String) empleado.get("contrato");
                    empleado.put("contrato", "C".equals(contrato) ? "Completo" : "P".equals(contrato) ? "Parcial" : contrato);

                    return empleado;
                }).collect(Collectors.toList());

                Map<String, Object> responseBody = Map.of(
                        "status", "Ok",
                        "fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                        "empleados", empleadosModificados
                );

                return ResponseEntity.ok(responseBody);
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "No se pudieron obtener los empleados"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "Ocurrió un error al procesar la solicitud",
                    "error", e.getMessage()
            ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarEmpleadoPorId(@PathVariable Long id) {
        try {
            ResponseEntity<Map<String, Object>> response = service.listarEmpleadosPorId(id);

            if (response != null && response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> empleado = (Map<String, Object>) response.getBody().get("empleado");

                if (empleado != null) {
                    String genero = (String) empleado.get("genero");
                    empleado.put("genero", "M".equals(genero) ? "Masculino" : "F".equals(genero) ? "Femenino" : genero);

                    String contrato = (String) empleado.get("contrato");
                    empleado.put("contrato", "C".equals(contrato) ? "Completo" : "P".equals(contrato) ? "Parcial" : contrato);
                }

                return ResponseEntity.ok(Map.of(
                        "empleado", empleado
                ));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "Empleado no encontrado"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message", "Ocurrió un error al buscar el empleado",
                    "error", e.getMessage()
            ));
        }
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarEmpleado(@Validated @RequestBody Empleados empleado) {
        try {
            ResponseEntity<Map<String, Object>> response = service.agregarEmpleados(empleado);

            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(Map.of(
                        "mensaje", "Se pudo consumir el servicio",
                        "servicio", "agregar Empleado"
                ));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "mensaje", "No se pudo consumir el servicio",
                    "servicio", "agregar Empleado"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "mensaje", "No se pudo consumir el servicio",
                    "servicio", "agregar Empleado",
                    "error", e.getMessage()
            ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editarEmpleado(@PathVariable Long id, @RequestBody Empleados empleado) {
        try {
            ResponseEntity<Map<String, Object>> response = service.editarEmpleados(empleado, id);

            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(Map.of(
                        "mensaje", "Se pudo consumir el servicio",
                        "servicio", "editar Empleado"
                ));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "mensaje", "No se pudo consumir el servicio",
                    "servicio", "editar Empleado"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "mensaje", "No se pudo consumir el servicio",
                    "servicio", "editar Empleado",
                    "error", e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarEmpleado(@PathVariable Long id) {
        try {
            ResponseEntity<Map<String, Object>> response = service.eliminarEmpleados(id);

            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(Map.of(
                        "mensaje", "Se pudo consumir el servicio",
                        "servicio", "eliminar Empleado"
                ));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "mensaje", "No se pudo consumir el servicio",
                    "servicio", "eliminar Empleado"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "mensaje", "No se pudo consumir el servicio",
                    "servicio", "eliminar Empleado",
                    "error", e.getMessage()
            ));
        }
    }

}
