package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.dto.request.DocRevRequest;
import back_end.correspondencia.edu.ucb.dto.response.DocRevResponse;
import back_end.correspondencia.edu.ucb.persistence.dao.DocRevDao;
import back_end.correspondencia.edu.ucb.persistence.entity.DocEntity;
import back_end.correspondencia.edu.ucb.persistence.entity.DocRevEntity;
import back_end.correspondencia.edu.ucb.persistence.entity.RolHasUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocRevBl {
    private final DocRevDao docRevDao;

    @Autowired
    public DocRevBl(DocRevDao docRevDao) {
        this.docRevDao = docRevDao;
    }

    @Transactional
    public List<DocRevResponse> getRevisionsByDocId(Long docId) {
        List<DocRevEntity> revisions = docRevDao.findByDocId(docId);
        return revisions.stream().map(revision -> new DocRevResponse(
                revision.getIdDocRev(),
                revision.getRolHasUserIdRolUser().getIdRoleUser(),
                revision.getDoc().getIdDoc(),
                revision.getReference(),
                revision.getOffice(),
                revision.getReceiver(),
                revision.getCreatedAt()
        )).collect(Collectors.toList());
    }

    @Transactional
    public DocRevResponse addRevision(DocRevRequest request) {
        // Crear y guardar la revisión
        DocRevEntity newRevision = new DocRevEntity();

        // Configurar las entidades relacionadas correctamente
        RolHasUserEntity rolHasUser = new RolHasUserEntity();
        rolHasUser.setIdRoleUser(request.getRolHasUserId());

        DocEntity doc = new DocEntity();
        doc.setIdDoc(request.getDocId());

        newRevision.setRolHasUserIdRolUser(rolHasUser);
        newRevision.setDoc(doc);
        newRevision.setReference(request.getReference());
        newRevision.setOffice(request.getOffice());
        newRevision.setReceiver(request.getReceiver());

        DocRevEntity savedRevision = docRevDao.save(newRevision);

        return new DocRevResponse(
                savedRevision.getIdDocRev(),
                savedRevision.getRolHasUserIdRolUser().getIdRoleUser(),
                savedRevision.getDoc().getIdDoc(),
                savedRevision.getReference(),
                savedRevision.getOffice(),
                savedRevision.getReceiver(),
                savedRevision.getCreatedAt()
        );
    }
}
