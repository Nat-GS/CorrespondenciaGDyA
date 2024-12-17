package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.persistence.dao.DocRevDao;
import back_end.correspondencia.edu.ucb.persistence.entity.DocRevEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocRevBl {
    private final DocRevDao docRevDao;

    @Autowired
    public DocRevBl(DocRevDao docRevDao) {
        this.docRevDao = docRevDao;
    }

    // Obtener todas las revisiones de documentos
    public List<DocRevEntity> getAllDocRevisions() {
        return docRevDao.findAll();
    }

    // Obtener una revisión de documento por ID
    public Optional<DocRevEntity> getDocRevisionById(Long id) {
        return docRevDao.findById(id);
    }

    // Crear una nueva revisión de documento
    public DocRevEntity createDocRevision(DocRevEntity docRev) {
        return docRevDao.save(docRev);
    }

    // Actualizar una revisión de documento existente
    public DocRevEntity updateDocRevision(Long id, DocRevEntity updatedDocRev) {
        return docRevDao.findById(id).map(docRev -> {
            docRev.setReference(updatedDocRev.getReference());
            docRev.setOffice(updatedDocRev.getOffice());
            docRev.setReceiver(updatedDocRev.getReceiver());
            docRev.setRolHasUserIdRolUser(updatedDocRev.getRolHasUserIdRolUser());
            return docRevDao.save(docRev);
        }).orElseThrow(() -> new RuntimeException("Revisión de documento no encontrada con ID: " + id));
    }

    // Eliminar una revisión de documento por ID
    public void deleteDocRevision(Long id) {
        docRevDao.deleteById(id);
    }
}
