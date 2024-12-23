package back_end.correspondencia.edu.ucb.persistence.dao;

import back_end.correspondencia.edu.ucb.persistence.entity.DocRevEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import back_end.correspondencia.edu.ucb.persistence.entity.DocRevEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocRevDao extends JpaRepository<DocRevEntity, Long> {

    @Query("SELECT r FROM doc_rev r WHERE r.doc.idDoc = :docId")
    List<DocRevEntity> findByDocId(@Param("docId") Long docId);
}