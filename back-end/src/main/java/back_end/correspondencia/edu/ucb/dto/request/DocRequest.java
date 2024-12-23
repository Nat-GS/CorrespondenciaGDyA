package back_end.correspondencia.edu.ucb.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class DocRequest {
    private String title;
    private String description;
    private Long rolHasUserId;
    private MultipartFile file; // Archivo en formato MultipartFile

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRolHasUserId() {
        return rolHasUserId;
    }

    public void setRolHasUserId(Long rolHasUserId) {
        this.rolHasUserId = rolHasUserId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
