package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.DocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocDao extends JpaRepository<DocEntity, Long> {

    // Consulta nativa para buscar documentos por idRoleUser
    @Query(value = "SELECT * FROM doc WHERE rol_has_user_id_rol_user = :idRoleUser", nativeQuery = true)
    List<DocEntity> findDocumentsByRoleUser(@Param("idRoleUser") Long idRoleUser);
}
