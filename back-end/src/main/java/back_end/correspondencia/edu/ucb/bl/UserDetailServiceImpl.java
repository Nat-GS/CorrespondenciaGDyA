package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.configurations.security.jwt.JwtUtils;
import back_end.correspondencia.edu.ucb.dto.request.AuthLoginrequest;
import back_end.correspondencia.edu.ucb.dto.response.AuthResponse;
import back_end.correspondencia.edu.ucb.persistence.dao.UsersDao;
import back_end.correspondencia.edu.ucb.persistence.entity.RolHasUserEntity;
import back_end.correspondencia.edu.ucb.persistence.entity.UsersEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("Cargando el usuario por el nombre de usuario: {}", username);

        UsersEntity usersEntity = usersDao.findUsersEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        // Crear una lista de roles como autoridades
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (RolHasUserEntity rolHasUser : usersEntity.getRoleHasUserEntityList()) {
            String role = "ROLE_".concat(rolHasUser.getRolesIdRoles().getUserRole());
            authorityList.add(new SimpleGrantedAuthority(role));
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(usersEntity, authorityList);
        LOG.info("Usuario cargado: {}", customUserDetails.getUsername());
        return customUserDetails;
    }


    public AuthResponse loginUser(AuthLoginrequest authLoginrequest, HttpServletResponse response) {
        String username = authLoginrequest.username();
        Optional<UsersEntity> usersEntity = usersDao.findUsersEntityByUsername(username);

        // Verifica si el usuario existe
        if (usersEntity.isEmpty()) {
            throw new UsernameNotFoundException("El usuario " + username + " no existe.");
        }

        // Concatenar la contraseña ingresada con el salt del usuario
        String saltedPassword = authLoginrequest.password() + usersEntity.get().getSalt();
        LOG.info("Contraseña con salt aplicada: {}", saltedPassword);

        // Autentica al usuario
        Authentication authentication = this.authenticate(username, saltedPassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Genera el JWT token
        String accessToken = jwtUtils.createToken(authentication);

        // Devuelve la respuesta con el token
        return new AuthResponse(username, "Usuario autenticado exitosamente", accessToken, true);
    }

    public Authentication authenticate(String username, String password) {
        // Carga los detalles del usuario
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Usuario o contraseña inválidos");
        }

        // Verifica si la contraseña coincide
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        // Crea el objeto de autenticación
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        LOG.info("Autenticación exitosa, tipo de principal: {}", authentication.getPrincipal().getClass());
        return authentication;
    }
}
