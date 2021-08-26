package co.com.alianza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.alianza.entities.SucursalEntity;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalEntity, Long> {
	
	java.util.Optional<SucursalEntity> findByNombre(String nombre);

}
