package T1_Pregunta02y03.Model;

import lombok.Data;

@Data
public class Empleados {
	
	private Integer id;
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
