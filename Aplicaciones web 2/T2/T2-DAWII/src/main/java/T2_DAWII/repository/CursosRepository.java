package T2_DAWII.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import T2_DAWII.model.Cursos;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long>{
	
}
