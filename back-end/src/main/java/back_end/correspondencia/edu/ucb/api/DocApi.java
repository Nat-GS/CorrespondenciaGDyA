package back_end.correspondencia.edu.ucb.api;

import back_end.correspondencia.edu.ucb.bl.DocBl;
import back_end.correspondencia.edu.ucb.dto.request.DocRequest;
import back_end.correspondencia.edu.ucb.dto.response.DocResponse;
import back_end.correspondencia.edu.ucb.dto.response.DocSummaryResponse;
import back_end.correspondencia.edu.ucb.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Globals.apiVersion + "doc")
public class DocApi {
    private final DocBl docBl;

    @Autowired
    public DocApi(DocBl docBl) {
        this.docBl = docBl;
    }

    @PostMapping
    public ResponseEntity<DocResponse> uploadDocument(@RequestBody DocRequest request) {
        DocResponse response = docBl.registerDocument(
                request.getFileLink(),
                request.getTitle(),
                request.getDescription(),
                request.getRolHasUserId()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<DocResponse>> getAllDocuments() {
        List<DocResponse> documents = docBl.getAllDocumentsAsResponse();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/sent")
    public ResponseEntity<List<DocSummaryResponse>> getDocumentsBySender(@RequestParam("rolHasUserId") Long rolHasUserId) {
        List<DocSummaryResponse> documents = docBl.getDocumentsByRoleUser(rolHasUserId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocResponse> getDocumentById(@PathVariable Long id) {
        DocResponse doc = docBl.getDocumentById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));
        return ResponseEntity.ok(doc);
    }

    @GetMapping("/{id}/link")
    public ResponseEntity<String> getDocumentLink(@PathVariable Long id) {
        DocResponse doc = docBl.getDocumentById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        return ResponseEntity.ok(doc.getFileData()); // Devolvemos directamente el enlace
    }
}
