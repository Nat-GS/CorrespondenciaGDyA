package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.api.UserApi;
import back_end.correspondencia.edu.ucb.dto.SuccessfulResponse;
import back_end.correspondencia.edu.ucb.dto.UnsuccessfulResponse;
import back_end.correspondencia.edu.ucb.dto.request.UsersRequest;
import back_end.correspondencia.edu.ucb.dto.response.UsersResponse;
import back_end.correspondencia.edu.ucb.persistence.dao.PersonDao;
import back_end.correspondencia.edu.ucb.persistence.dao.RolHasUserDao;
import back_end.correspondencia.edu.ucb.persistence.dao.RolesDao;
import back_end.correspondencia.edu.ucb.persistence.dao.UsersDao;
import back_end.correspondencia.edu.ucb.persistence.entity.PersonEntity;
import back_end.correspondencia.edu.ucb.persistence.entity.RolHasUserEntity;
import back_end.correspondencia.edu.ucb.persistence.entity.RolesEntity;
import back_end.correspondencia.edu.ucb.persistence.entity.UsersEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsersBl {
    private final UsersDao usersDao;
    private final RolesDao rolesDao;
    private final RolHasUserDao rolHasUserDao;
    private final PersonDao personDao;
    private final PasswordEncoder passwordEncoder;
    private static final Logger LOG = LoggerFactory.getLogger(UsersBl.class);

    @Autowired
    public UsersBl(UsersDao usersDao, RolesDao rolesDao, RolHasUserDao rolHasUserDao, PersonDao personDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.rolesDao = rolesDao;
        this.rolHasUserDao = rolHasUserDao;
        this.personDao = personDao;
        this.passwordEncoder = passwordEncoder;
    }

    // Obtener todos los usuarios
    public List<UsersEntity> getAllUsers() {
        return usersDao.findAll();
    }

    // Obtener un usuario por ID
    public Optional<UsersEntity> getUserById(Long id) {
        return usersDao.findById(id);
    }

    // Crear un nuevo usuario
    public UsersEntity createUser(UsersEntity user) {
        // Lógica adicional como encriptar contraseña si es necesario
        return usersDao.save(user);
    }

    @Transactional
    public Object registerNewUser(UsersRequest request) {
        LOG.info("Procesando registro de nuevo usuario con email: {}", request.getEmail());
        try {
            // Validar si el usuario ya existe por correo
            Optional<UsersEntity> existingUser = usersDao.findUsersEntityByUsername(request.getEmail());
            if (existingUser.isPresent()) {
                return new UnsuccessfulResponse("400", "El usuario ya existe", "Email duplicado");
            }

            // Crear nueva entidad `PersonEntity`
            PersonEntity personEntity = new PersonEntity();
            personEntity.setName(request.getName());
            personEntity.setCi(request.getCi());
            personEntity.setFatherLastName(request.getFatherLastName());
            personEntity.setMotherLastName(request.getMotherLastName());
            personEntity.setEmail(request.getEmail());
            personEntity.setCellPhone(request.getCellPhone());
            personEntity.setDescription(request.getDescription());
            personEntity.setStatus(1);
            personDao.save(personEntity);

            // Generar contraseña y salt
            String generatedSalt = randomAlphaNumericString(24);
            String encodedPassword = passwordEncoder.encode(request.getPassword() + generatedSalt);

            // Crear nueva entidad `UsersEntity`
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setPersonIdPerson(personEntity);
            usersEntity.setUsername(request.getCi());
            usersEntity.setPassword(encodedPassword);
            usersEntity.setSalt(generatedSalt);
            usersEntity.setStatus(1);
            usersDao.save(usersEntity);

            // Buscar el rol predeterminado
            Optional<RolesEntity> role = rolesDao.findByUserRoleAndStatus("ROLE_USER", 1);
            if (role.isEmpty()) {
                throw new IllegalStateException("Rol predeterminado no encontrado");
            }

            // Asignar rol al usuario
            RolHasUserEntity rolHasUserEntity = new RolHasUserEntity();
            rolHasUserEntity.setUsersIdUsers(usersEntity);
            rolHasUserEntity.setRolesIdRoles(role.get());
            rolHasUserDao.save(rolHasUserEntity);

            // Respuesta de éxito
            UsersResponse response = new UsersResponse();
            response = response.usersEntityToResponse(usersEntity);
            return new SuccessfulResponse("201", "Usuario registrado exitosamente", response);

        } catch (IllegalStateException e) {
            LOG.warn("Advertencia: {}", e.getMessage());
            return new UnsuccessfulResponse("400", e.getMessage(), null);
        } catch (Exception e) {
            LOG.error("Error al registrar el nuevo usuario", e);
            return new UnsuccessfulResponse("500", "Error interno del servidor", e.getMessage());
        }
    }


    public String randomAlphaNumericString(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }



    // Actualizar un usuario existente
    public UsersEntity updateUser(Long id, UsersEntity updatedUser) {
        return usersDao.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setStatus(updatedUser.getStatus());
            return usersDao.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // Eliminar un usuario por ID
    public void deleteUser(Long id) {
        usersDao.deleteById(id);
    }
}
