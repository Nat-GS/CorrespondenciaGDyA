package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesDao extends JpaRepository<RolesEntity, Long> {

    Optional<RolesEntity> findByUserRoleAndStatus(String userRole, int status);


}
