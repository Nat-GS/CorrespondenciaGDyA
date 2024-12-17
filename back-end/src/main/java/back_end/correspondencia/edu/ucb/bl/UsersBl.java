package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.persistence.dao.UsersDao;
import back_end.correspondencia.edu.ucb.persistence.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersBl {
    private final UsersDao usersDao;

    @Autowired
    public UsersBl(UsersDao usersDao) {
        this.usersDao = usersDao;
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
