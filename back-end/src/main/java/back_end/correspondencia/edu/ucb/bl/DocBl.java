package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.persistence.dao.DocDao;
import back_end.correspondencia.edu.ucb.persistence.entity.DocEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocBl {
    private final DocDao docDao;

    @Autowired
    public DocBl(DocDao docDao) {
        this.docDao = docDao;
    }

    // Obtener todos los documentos
    public List<DocEntity> getAllDocuments() {
        return docDao.findAll();
    }

    // Obtener un documento por ID
    public Optional<DocEntity> getDocumentById(Long id) {
        return docDao.findById(id);
    }

    // Crear un nuevo documento
    public DocEntity createDocument(DocEntity document) {
        return docDao.save(document);
    }

    // Actualizar un documento existente
    public DocEntity updateDocument(Long id, DocEntity updatedDoc) {
        return docDao.findById(id).map(doc -> {
            doc.setTitle(updatedDoc.getTitle());
            doc.setDescription(updatedDoc.getDescription());
            doc.setRolHasUserIdRolUser(updatedDoc.getRolHasUserIdRolUser());
            return docDao.save(doc);
        }).orElseThrow(() -> new RuntimeException("Documento no encontrado con ID: " + id));
    }

    // Eliminar un documento por ID
    public void deleteDocument(Long id) {
        docDao.deleteById(id);
    }
}
