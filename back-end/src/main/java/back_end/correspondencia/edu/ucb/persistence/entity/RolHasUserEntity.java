package back_end.correspondencia.edu.ucb.persistence.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Entity(name = "rol_has_user")
@Table(name ="rol_has_user")
public class RolHasUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_user", nullable = false)
    private Long idRoleUser;
    @ManyToOne
    @JoinColumn(name = "users_id_users", referencedColumnName = "id_users")
    private UsersEntity usersIdUsers;
    @ManyToOne
    @JoinColumn(name = "roles_id_rol", referencedColumnName = "id_rol")
    private RolesEntity rolesIdRoles;

    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "rolHasUserIdRolUser", cascade = CascadeType.ALL, orphanRemoval = true)
    List<DocEntity> docEntityList;
    @OneToMany(mappedBy = "rolHasUserIdRolUser", cascade = CascadeType.ALL, orphanRemoval = true)
    List<DocRevEntity> docRevEntityList;

    @PrePersist
    protected void onCreate() {
        status = 1;
        createdAt = LocalDateTime.now();
    }

    public Long getIdRoleUser() {
        return idRoleUser;
    }

    public void setIdRoleUser(Long idRoleUser) {
        this.idRoleUser = idRoleUser;
    }

    public UsersEntity getUsersIdUsers() {
        return usersIdUsers;
    }

    public void setUsersIdUsers(UsersEntity usersIdUsers) {
        this.usersIdUsers = usersIdUsers;
    }

    public RolesEntity getRolesIdRoles() {
        return rolesIdRoles;
    }

    public void setRolesIdRoles(RolesEntity rolesIdRoles) {
        this.rolesIdRoles = rolesIdRoles;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<DocEntity> getDocEntityList() {
        return docEntityList;
    }

    public void setDocEntityList(List<DocEntity> docEntityList) {
        this.docEntityList = docEntityList;
    }

    public List<DocRevEntity> getDocRevEntityList() {
        return docRevEntityList;
    }

    public void setDocRevEntityList(List<DocRevEntity> docRevEntityList) {
        this.docRevEntityList = docRevEntityList;
    }
}
