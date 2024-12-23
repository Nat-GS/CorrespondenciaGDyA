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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    public DocResponse registerDocumentNative(MultipartFile file, String title, String description, Long rolHasUserId) {
        try {
            byte[] fileData = file.getBytes();  // Obtiene los bytes del archivo
            LocalDateTime sendDate = LocalDateTime.now();  // Usar LocalDateTime directamente
            int status = 2;  // Estado predeterminado

            // Ejecuta la consulta nativa
            Long idDoc = docDao.insertDocument(description, fileData, rolHasUserId, sendDate, status, title);
            return new DocResponse(idDoc, title, description, status, sendDate, fileData);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el archivo", e);
        }
    }

    public List<DocResponse> getAllDocumentsAsResponse() {
        List<Object[]> rawResults = docDao.findAllDocumentsByStatus(2);

        return rawResults.stream()
                .map(record -> new DocResponse(
                        ((Number) record[0]).longValue(),               // idDoc
                        (String) record[1],                            // title
                        (String) record[2],                            // description
                        ((Number) record[3]).intValue(),               // status
                        (record[4] != null ? ((java.sql.Timestamp) record[4]).toLocalDateTime() : null), // sendDate
                        (byte[]) record[5]                             // fileData
                ))
                .collect(Collectors.toList());
    }

    // Obtener todos los documentos
    public List<DocEntity> getAllDocuments() {
        return docDao.findAll();
    }

    public List<DocSummaryResponse> getDocumentsBySender(Long rolHasUserId) {
        List<Object[]> rawResults = docDao.findDocumentsBySender(rolHasUserId);

        return rawResults.stream()
                .map(record -> new DocSummaryResponse(
                        ((Number) record[0]).longValue(), // idDoc
                        (String) record[1],              // title
                        (String) record[2],              // description
                        ((Number) record[3]).intValue(), // status
                        ((java.sql.Timestamp) record[4]).toLocalDateTime() // sendDate
                ))
                .collect(Collectors.toList());
    }

    // Obtener un documento por ID
    public Optional<DocResponse> getDocumentById(Long id) {
        Optional<Object[]> rawResult = docDao.findDocumentRawById(id);

        if (rawResult.isPresent()) {
            Object record = rawResult.get();

            if (record instanceof Object[]) {
                Object[] fields = (Object[]) record;

                try {
                    // Convertir los índices a sus tipos esperados
                    Long idDoc = fields[0] != null ? ((Number) fields[0]).longValue() : null; // idDoc
                    String title = fields[1] != null ? fields[1].toString() : null;          // title
                    String description = fields[2] != null ? fields[2].toString() : null;   // description
                    Integer status = fields[3] != null ? ((Number) fields[3]).intValue() : null; // status
                    LocalDateTime sendDate = fields[4] != null
                            ? ((java.sql.Timestamp) fields[4]).toLocalDateTime()
                            : null; // sendDate
                    byte[] fileData = fields[5] != null ? (byte[]) fields[5] : null;        // fileData
                    System.out.println("Resultado crudo del DAO: " + record);

                    // Crear el objeto de respuesta
                    return Optional.of(new DocResponse(idDoc, title, description, status, sendDate, fileData));
                } catch (ClassCastException e) {
                    throw new RuntimeException("Error al procesar los datos del documento: " + e.getMessage(), e);
                }
            } else {
                throw new RuntimeException("Resultado inesperado: no es un arreglo de objetos.");
            }
        }

        return Optional.empty();
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

    public List<DocEntity> getAllDocumentsWithDetails() {
        // Recuperar todos los documentos con detalles útiles
        return docDao.findAll()
                .stream()
                .map(doc -> {
                    doc.setFileData(null); // Opcional: eliminar el archivo para no incluirlo en la respuesta
                    return doc;
                })
                .collect(Collectors.toList());
    }
}
