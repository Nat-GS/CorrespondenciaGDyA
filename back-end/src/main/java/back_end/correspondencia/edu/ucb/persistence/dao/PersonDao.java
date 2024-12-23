package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonDao extends JpaRepository<PersonEntity, Long> {
    boolean existsByCi(String ci); // Para verificar si un CI ya existe.
    Optional<PersonEntity> findByCi(String ci); // Para buscar por CI.
}
