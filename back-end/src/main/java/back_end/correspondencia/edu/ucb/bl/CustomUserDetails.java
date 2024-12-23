package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.persistence.entity.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetails implements UserDetails {

    private UsersEntity user;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UsersEntity user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    // Retorna el nombre completo incluyendo el nombre, apellido paterno y materno
    public String getFullName() {
        return String.format("%s %s %s",
                user.getPersonIdPerson().getName(),
                user.getPersonIdPerson().getFatherLastName(),
                user.getPersonIdPerson().getMotherLastName());
    }

    public Long getIdUsers() {
        return user.getIdUsers();
    }

    public String getPersonName() {
        return user.getPersonIdPerson().getName();
    }

    public List<String> getUserRoles() {
        return user.getRoleHasUserEntityList().stream()
                .map(rolHasUser -> rolHasUser.getRolesIdRoles().getUserRole())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Métodos de UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus() == 1; // Ejemplo: depende del campo "status" si la cuenta está activa
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() == 1; // Ejemplo: depende del campo "status"
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Siempre retorna true para este caso
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == 1; // Retorna true si el status es activo (1)
    }
}
