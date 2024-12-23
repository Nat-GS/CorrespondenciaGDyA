package back_end.correspondencia.edu.ucb.dto.response;

import java.time.LocalDateTime;

public class DocSummaryResponse {
    private Long idDoc;
    private String title;
    private String description;
    private int status;
    private LocalDateTime sendDate;

    public DocSummaryResponse(Long idDoc, String title, String description, int status, LocalDateTime sendDate) {
        this.idDoc = idDoc;
        this.title = title;
        this.description = description;
        this.status = status;
        this.sendDate = sendDate;
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
}

