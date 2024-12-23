package back_end.correspondencia.edu.ucb.dto.response;

import java.time.LocalDateTime;

public class DocRevResponse {
    private Long idDocRev;
    private Long rolHasUserId;
    private Long docId;
    private String reference;
    private String office;
    private String receiver;
    private LocalDateTime createdAt;

    public DocRevResponse(Long idDocRev, Long rolHasUserId, Long docId, String reference, String office, String receiver, LocalDateTime createdAt) {
        this.idDocRev = idDocRev;
        this.rolHasUserId = rolHasUserId;
        this.docId = docId;
        this.reference = reference;
        this.office = office;
        this.receiver = receiver;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getIdDocRev() {
        return idDocRev;
    }

    public void setIdDocRev(Long idDocRev) {
        this.idDocRev = idDocRev;
    }

    public Long getRolHasUserId() {
        return rolHasUserId;
    }

    public void setRolHasUserId(Long rolHasUserId) {
        this.rolHasUserId = rolHasUserId;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
