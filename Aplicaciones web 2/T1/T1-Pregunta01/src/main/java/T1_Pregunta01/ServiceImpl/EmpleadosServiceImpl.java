package T1_Pregunta01.ServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import T1_Pregunta01.Model.Empleados;
import T1_Pregunta01.Repository.EmpleadosRepository;
import T1_Pregunta01.Service.EmpleadoService;

@Service
public class EmpleadosServiceImpl implements EmpleadoService{
	@Autowired
	private EmpleadosRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleados() {
	    Map<String, Object> respuesta = new HashMap<>();
	    List<Empleados> empleados = dao.findAll();

	    if (!empleados.isEmpty()) {
	        respuesta.put("mensaje", "Lista de empleados");
	        respuesta.put("empleados", empleados); 
	        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No existen registros de empleados");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}


	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleadosPorId(Long id) {
	    Map<String, Object> respuesta = new HashMap<>();
	    Optional<Empleados> empleado = dao.findById(id); 

	    if (empleado.isPresent()) {
	        respuesta.put("empleado", empleado.get());
	        respuesta.put("mensaje", "Búsqueda correcta");
	        return ResponseEntity.ok().body(respuesta);
	    } else {
	        respuesta.put("mensaje", "Sin registros con ID: " + id);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> agregarEmpleados(Empleados empleados) {
	    Map<String, Object> respuesta = new HashMap<>();
	    dao.save(empleados);
	    respuesta.put("empleado", empleados);
	    respuesta.put("mensaje", "Empleado añadido correctamente");
	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

	@Override
	public ResponseEntity<Map<String, Object>> editarEmpleados(Empleados empleados, Long id) {
	    Map<String, Object> respuesta = new HashMap<>();
	    Optional<Empleados> empleadoExiste = dao.findById(id);

	    if (empleadoExiste.isPresent()) {
	        Empleados empleado = empleadoExiste.get();
	        empleado.setNombres(empleados.getNombres());
	        empleado.setApellidos(empleados.getApellidos());
	        empleado.setContrato(empleados.getContrato());
	        empleado.setSueldo(empleados.getSueldo());
	        empleado.setDni(empleados.getDni());
	        empleado.setAntiguedad(empleados.getAntiguedad());
	        empleado.setTelefono(empleados.getTelefono());
	        empleado.setGenero(empleados.getGenero());
	        empleado.setVisible(empleados.getVisible());
	        dao.save(empleado);
	        respuesta.put("empleado", empleado);
	        respuesta.put("mensaje", "Datos del empleado modificados correctamente");
	        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "Sin registros con ID: " + id);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarEmpleados(Long id) {
	    Map<String, Object> respuesta = new HashMap<>();
	    Optional<Empleados> empleadoExiste = dao.findById(id);

	    if (empleadoExiste.isPresent()) {
	        dao.delete(empleadoExiste.get());
	        respuesta.put("mensaje", "Empleado eliminado correctamente");
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "Sin registros con ID: " + id);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}


	@Override
	public ResponseEntity<Map<String, Object>> eliminarEmpleadosVisible(Long id) {
	    Map<String, Object> respuesta = new HashMap<>();
	    Optional<Empleados> empleadoExiste = dao.findById(id);

	    if (empleadoExiste.isPresent()) {
	        Empleados empleado = empleadoExiste.get();
	        empleado.setVisible("N");
	        dao.save(empleado);
	        respuesta.put("mensaje", "Empleado marcado como no visible");
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "Sin registros con ID: " + id);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarEmpleadosVisible() {
	    Map<String, Object> respuesta = new HashMap<>();
	    List<Empleados> empleados = dao.findAllByVisible("S");

	    if (!empleados.isEmpty()) {
	        respuesta.put("mensaje", "Lista de empleados visibles");
	        respuesta.put("empleados", empleados);
	        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No existen registros de empleados visibles");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}
	

}
