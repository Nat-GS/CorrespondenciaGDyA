package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.dto.response.DocResponse;
import back_end.correspondencia.edu.ucb.dto.response.DocSummaryResponse;
import back_end.correspondencia.edu.ucb.persistence.dao.DocDao;
import back_end.correspondencia.edu.ucb.persistence.dao.RolHasUserDao;
import back_end.correspondencia.edu.ucb.persistence.entity.DocEntity;
import back_end.correspondencia.edu.ucb.persistence.entity.RolHasUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocBl {
    private final DocDao docDao;
    private final RolHasUserDao rolHasUserDao;

    @Autowired
    public DocBl(DocDao docDao, RolHasUserDao rolHasUserDao) {
        this.docDao = docDao;
        this.rolHasUserDao = rolHasUserDao;
    }

    @Transactional
    public DocResponse registerDocument(String fileData, String title, String description, Long rolHasUserId) {
        // Buscar la entidad RolHasUserEntity por ID
        RolHasUserEntity rolHasUser = rolHasUserDao.findById(rolHasUserId)
                .orElseThrow(() -> new RuntimeException("RolHasUserEntity no encontrado con ID: " + rolHasUserId));

        LocalDateTime sendDate = LocalDateTime.now();
        int status = 2;

        DocEntity newDoc = new DocEntity();
        newDoc.setFileData(fileData);
        newDoc.setTitle(title);
        newDoc.setDescription(description);
        newDoc.setSendDate(sendDate);
        newDoc.setStatus(status);
        newDoc.setRolHasUserIdRolUser(rolHasUser); // Asignar la entidad completa

        DocEntity savedDoc = docDao.save(newDoc);

        return new DocResponse(
                savedDoc.getIdDoc(),
                savedDoc.getTitle(),
                savedDoc.getDescription(),
                savedDoc.getStatus(),
                savedDoc.getSendDate(),
                savedDoc.getFileData()
        );
    }

    public List<DocResponse> getAllDocumentsAsResponse() {
        List<DocEntity> documents = docDao.findAll();

        return documents.stream()
                .map(doc -> new DocResponse(
                        doc.getIdDoc(),
                        doc.getTitle(),
                        doc.getDescription(),
                        doc.getStatus(),
                        doc.getSendDate(),
                        doc.getFileData()
                ))
                .collect(Collectors.toList());
    }

    public Optional<DocResponse> getDocumentById(Long id) {
        return docDao.findById(id).map(doc -> new DocResponse(
                doc.getIdDoc(),
                doc.getTitle(),
                doc.getDescription(),
                doc.getStatus(),
                doc.getSendDate(),
                doc.getFileData()
        ));
    }

    @Transactional
    public List<DocSummaryResponse> getDocumentsByRoleUser(Long idRoleUser) {
        List<DocEntity> documents = docDao.findDocumentsByRoleUser(idRoleUser);

        return documents.stream()
                .map(doc -> new DocSummaryResponse(
                        doc.getIdDoc(),
                        doc.getTitle(),
                        doc.getDescription(),
                        doc.getStatus(),
                        doc.getSendDate()
                ))
                .collect(Collectors.toList());
    }
}
