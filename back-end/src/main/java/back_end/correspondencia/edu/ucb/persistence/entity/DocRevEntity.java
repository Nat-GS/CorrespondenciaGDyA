package back_end.correspondencia.edu.ucb.persistence.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity(name = "doc_rev")
@Table(name ="doc_rev")
public class DocRevEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doc_rev", nullable = false)
    private Long idDocRev;
    @ManyToOne
    @JoinColumn(name = "rol_has_user_id_rol_user", nullable = false)
    private RolHasUserEntity rolHasUserIdRolUser;
    @ManyToOne
    @JoinColumn(name = "doc_id_doc", nullable = false)
    private DocEntity doc;
    @Column(name = "reference", length = 50, nullable = false)
    private String reference;
    @Column(name = "office", length = 12, nullable = false)
    private String office;
    @Column(name = "receiver", length = 50, nullable = false)
    private String receiver;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getIdDocRev() {
        return idDocRev;
    }

    public void setIdDocRev(Long idDocRev) {
        this.idDocRev = idDocRev;
    }

    public RolHasUserEntity getRolHasUserIdRolUser() {
        return rolHasUserIdRolUser;
    }

    public void setRolHasUserIdRolUser(RolHasUserEntity rolHasUserIdRolUser) {
        this.rolHasUserIdRolUser = rolHasUserIdRolUser;
    }

    public DocEntity getDoc() {
        return doc;
    }

    public void setDoc(DocEntity doc) {
        this.doc = doc;
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
