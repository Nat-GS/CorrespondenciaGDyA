package back_end.correspondencia.edu.ucb.dto.response;

import back_end.correspondencia.edu.ucb.persistence.entity.UsersEntity;

public class UsersResponse {
    private Long id;
    private String username;
    private String email;
    private String role;
    private int status;

    // Constructor vacío
    public UsersResponse() {}

    // Constructor con parámetros
    public UsersResponse(Long id, String username, String email, String role, int status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public UsersResponse usersEntityToResponse(UsersEntity usersEntity) {
        if (usersEntity == null || usersEntity.getPersonIdPerson() == null) {
            throw new IllegalArgumentException("UsersEntity o PersonEntity no pueden ser null");
        }

        // Obtén el rol del usuario (asumiendo que tiene al menos uno)
        String role = usersEntity.getRoleHasUserEntityList() != null && !usersEntity.getRoleHasUserEntityList().isEmpty()
                ? usersEntity.getRoleHasUserEntityList()
                .stream()
                .findFirst() // Tomamos el primer rol
                .map(roleHasUser -> roleHasUser.getRolesIdRoles().getUserRole())
                .orElse("Sin Rol")
                : "Sin Rol";

        return new UsersResponse(
                usersEntity.getIdUsers(),
                usersEntity.getUsername(),
                usersEntity.getPersonIdPerson().getEmail(),
                role,
                usersEntity.getStatus()
        );
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

