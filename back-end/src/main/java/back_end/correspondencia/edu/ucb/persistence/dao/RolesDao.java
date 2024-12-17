package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesDao extends JpaRepository<RolesEntity, Long> {

}
