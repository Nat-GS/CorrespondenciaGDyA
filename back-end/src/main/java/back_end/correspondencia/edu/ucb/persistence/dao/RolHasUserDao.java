package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.RolHasUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolHasUserDao extends JpaRepository<RolHasUserEntity, Long> {
}
