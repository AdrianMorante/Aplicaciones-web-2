package T2_DAWII.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import T2_DAWII.model.Usuario;
import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findOneByEmail(String email);
	
	@Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.fecha = :fecha WHERE u.email = :email")
    void actualizarFechaLogin(@Param("email") String email, @Param("fecha") Date fecha);
}
