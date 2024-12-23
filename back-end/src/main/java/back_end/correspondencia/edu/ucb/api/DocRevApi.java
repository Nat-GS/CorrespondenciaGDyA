package back_end.correspondencia.edu.ucb.api;

import back_end.correspondencia.edu.ucb.bl.DocRevBl;
import back_end.correspondencia.edu.ucb.dto.request.DocRevRequest;
import back_end.correspondencia.edu.ucb.dto.response.DocRevResponse;
import back_end.correspondencia.edu.ucb.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Globals.apiVersion+"doc/revision")
public class DocRevApi {
    private final DocRevBl docRevBl;

    @Autowired
    public DocRevApi(DocRevBl docRevBl) {
        this.docRevBl = docRevBl;
    }

    @GetMapping("/{docId}")
    public ResponseEntity<List<DocRevResponse>> getRevisionsByDocId(@PathVariable Long docId) {
        List<DocRevResponse> revisions = docRevBl.getRevisionsByDocId(docId);
        return ResponseEntity.ok(revisions);
    }

    @PostMapping
    public ResponseEntity<DocRevResponse> addRevision(@RequestBody DocRevRequest request) {
        DocRevResponse response = docRevBl.addRevision(request);
        return ResponseEntity.ok(response);
    }
}