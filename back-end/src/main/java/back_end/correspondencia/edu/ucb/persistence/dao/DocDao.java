package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.DocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocDao extends JpaRepository<DocEntity, Long> {
}
