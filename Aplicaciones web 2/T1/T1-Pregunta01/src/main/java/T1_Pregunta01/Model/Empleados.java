package T1_Pregunta01.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "empleados")
@EntityListeners(AuditingEntityListener.class)
public class Empleados {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombres;
	private String apellidos;
	private String contrato;
	private double sueldo;
	private Integer dni;
	private Integer antiguedad;
	private Integer telefono;
	private String genero;
	private String visible;
	
}
