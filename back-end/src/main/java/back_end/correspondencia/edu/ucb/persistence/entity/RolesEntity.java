package back_end.correspondencia.edu.ucb.persistence.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity(name = "roles")
@Table(name ="roles")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false)
    private Long idRole;
    @Column(name = "user_role", length = 75, nullable = false)
    private String userRole;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "rolesIdRoles", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolHasUserEntity> rolHasUsersEntityList = new ArrayList<>();

    @PrePersist
    protected void onCreate(){
        userRole = userRole.trim();
        userRole = userRole.toUpperCase();
        status = 1;
        createdAt = LocalDateTime.now();
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RolHasUserEntity> getRolHasUsersEntityList() {
        return rolHasUsersEntityList;
    }

    public void setRolHasUsersEntityList(List<RolHasUserEntity> rolHasUsersEntityList) {
        this.rolHasUsersEntityList = rolHasUsersEntityList;
    }
}
