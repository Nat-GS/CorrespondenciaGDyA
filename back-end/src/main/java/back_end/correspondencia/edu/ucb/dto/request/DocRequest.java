package back_end.correspondencia.edu.ucb.dto.request;

public class DocRequest {
    private String fileLink;
    private String title;
    private String description;
    private Long rolHasUserId;

    // Getters y Setters
    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

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
}
