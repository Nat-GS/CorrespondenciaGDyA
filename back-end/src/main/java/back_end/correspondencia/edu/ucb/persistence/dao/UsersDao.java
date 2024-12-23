package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersDao extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findUsersEntityByUsername(String username);
}
