package back_end.correspondencia.edu.ucb.persistence.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@Entity(name = "users")
@Table(name ="users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users", nullable = false)
    private Long idUsers;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id_person", referencedColumnName = "id_person")
    private PersonEntity personIdPerson;
    @Column(name = "username", nullable = false, length = 4000)
    private String username;
    @Column(name = "password", nullable = false, length = 4000)
    private String password;
    @Column(name = "salt", nullable = false, length = 4000)
    private String salt;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "usersIdUsers", orphanRemoval = true, cascade = CascadeType.ALL)
    List<RolHasUserEntity> roleHasUserEntityList;

    @PrePersist
    protected void onCreate() {
        //status = 1;
        createdAt = LocalDateTime.now();
    }

    public Long getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }

    public PersonEntity getPersonIdPerson() {
        return personIdPerson;
    }

    public void setPersonIdPerson(PersonEntity personIdPerson) {
        this.personIdPerson = personIdPerson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public List<RolHasUserEntity> getRoleHasUserEntityList() {
        return roleHasUserEntityList;
    }

    public void setRoleHasUserEntityList(List<RolHasUserEntity> roleHasUserEntityList) {
        this.roleHasUserEntityList = roleHasUserEntityList;
    }
}
