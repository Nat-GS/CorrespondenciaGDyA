package back_end.correspondencia.edu.ucb.dto.request;

public class DocRevRequest {
    private Long rolHasUserId;
    private Long docId;
    private String reference;
    private String office;
    private String receiver;

    // Getters and Setters
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
}
