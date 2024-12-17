package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.DocRevEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRevDao extends JpaRepository<DocRevEntity, Long> {
}
