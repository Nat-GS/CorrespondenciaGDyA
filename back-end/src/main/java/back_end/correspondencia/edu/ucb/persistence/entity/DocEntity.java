package back_end.correspondencia.edu.ucb.persistence.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Entity(name = "doc")
@Table(name ="doc")
public class DocEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doc", nullable = false)
    private Long idDoc;
    @ManyToOne
    @JoinColumn(name = "rol_has_user_id_rol_user", nullable = false)
    private RolHasUserEntity rolHasUserIdRolUser;
    @Column(name = "tittle", length = 100, nullable = false)
    private String title;
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    @Column(name = "send_date", nullable = false)
    private LocalDateTime sendDate;

    @PrePersist
    protected void onCreate() {
        sendDate = LocalDateTime.now();
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
    }

    public RolHasUserEntity getRolHasUserIdRolUser() {
        return rolHasUserIdRolUser;
    }

    public void setRolHasUserIdRolUser(RolHasUserEntity rolHasUserIdRolUser) {
        this.rolHasUserIdRolUser = rolHasUserIdRolUser;
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

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
}
