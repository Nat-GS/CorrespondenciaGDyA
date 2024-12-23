package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.DocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DocDao extends JpaRepository<DocEntity, Long> {
    @Transactional
    @Query(value = "INSERT INTO doc (description, file_data, rol_has_user_id_rol_user, send_date, status, title) " +
            "VALUES (:description, :fileData, :rolHasUserId, :sendDate, :status, :title) RETURNING id_doc", nativeQuery = true)
    Long insertDocument(@Param("description") String description,
                        @Param("fileData") byte[] fileData,
                        @Param("rolHasUserId") Long rolHasUserId,
                        @Param("sendDate") LocalDateTime sendDate,
                        @Param("status") int status,
                        @Param("title") String title);

    @Query(value = "SELECT d.id_doc AS idDoc, d.title AS title, d.description AS description, d.status AS status, d.send_date AS sendDate, d.file_data AS fileData " +
            "FROM doc d " +
            "WHERE d.status = :status", nativeQuery = true)
    List<Object[]> findAllDocumentsByStatus(@Param("status") int status);

    @Query(value = "SELECT d.id_doc AS idDoc, d.title AS title, d.description AS description, " +
            "d.status AS status, d.send_date AS sendDate, d.file_data AS fileData " +
            "FROM doc d WHERE d.id_doc = :id", nativeQuery = true)
    Optional<Object[]> findDocumentRawById(@Param("id") Long id);

    @Query(value = "SELECT d.id_doc AS idDoc, d.title AS title, d.description AS description, " +
            "d.status AS status, d.send_date AS sendDate " +
            "FROM doc d " +
            "WHERE d.rol_has_user_id_rol_user = :rolHasUserId " +
            "ORDER BY d.send_date DESC", nativeQuery = true)
    List<Object[]> findDocumentsBySender(@Param("rolHasUserId") Long rolHasUserId);

    /*@Modifying
    @Transactional
    @Query(value = "INSERT INTO doc (description, file_data, rol_has_user_id_rol_user, send_date, status, title) " +
            "VALUES (:description, :fileData, :rolHasUserId, :sendDate, :status, :title)", nativeQuery = true)
    void insertDocument(@Param("description") String description,
                        @Param("fileData") byte[] fileData,
                        @Param("rolHasUserId") Long rolHasUserId,
                        @Param("sendDate") LocalDateTime sendDate,  // Cambiar a LocalDateTime
                        @Param("status") int status,
                        @Param("title") String title);*/

}
