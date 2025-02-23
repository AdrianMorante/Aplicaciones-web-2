package T1_Pregunta01.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import T1_Pregunta01.Model.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Long>{
	List<Empleados> findAllByVisible(String visible);

}
