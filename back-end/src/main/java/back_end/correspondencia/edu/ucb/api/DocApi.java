package back_end.correspondencia.edu.ucb.api;

import back_end.correspondencia.edu.ucb.bl.DocBl;
import back_end.correspondencia.edu.ucb.dto.response.DocResponse;
import back_end.correspondencia.edu.ucb.dto.response.DocSummaryResponse;
import back_end.correspondencia.edu.ucb.persistence.entity.DocEntity;
import back_end.correspondencia.edu.ucb.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Globals.apiVersion+"doc")
public class DocApi {
    private final DocBl docBl;

    @Autowired
    public DocApi(DocBl docBl) {
        this.docBl = docBl;
    }

    @PostMapping
    public ResponseEntity<DocResponse> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("rolHasUserId") Long rolHasUserId) {
        DocResponse response = docBl.registerDocumentNative(file, title, description, rolHasUserId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DocResponse>> getAllDocuments() {
        List<DocResponse> documents = docBl.getAllDocumentsAsResponse();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/sent")
    public ResponseEntity<List<DocSummaryResponse>> getDocumentsBySender(@RequestParam("rolHasUserId") Long rolHasUserId) {
        List<DocSummaryResponse> documents = docBl.getDocumentsBySender(rolHasUserId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> getDocumentFile(@PathVariable Long id) {
        DocResponse doc = docBl.getDocumentById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + doc.getTitle() + "\"")
                .header("Content-Type", "application/pdf") // Cambia según el tipo de archivo
                .body(doc.getFileData());
    }


}
