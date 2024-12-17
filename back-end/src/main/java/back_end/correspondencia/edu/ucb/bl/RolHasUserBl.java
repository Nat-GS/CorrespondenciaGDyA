package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.persistence.dao.RolHasUserDao;
import back_end.correspondencia.edu.ucb.persistence.entity.RolHasUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolHasUserBl {
    private final RolHasUserDao rolHasUserDao;

    @Autowired
    public RolHasUserBl(RolHasUserDao rolHasUserDao) {
        this.rolHasUserDao = rolHasUserDao;
    }

    // Obtener todos los registros de RolHasUser
    public List<RolHasUserEntity> getAllRolHasUsers() {
        return rolHasUserDao.findAll();
    }

    // Obtener un RolHasUser por ID
    public Optional<RolHasUserEntity> getRolHasUserById(Long id) {
        return rolHasUserDao.findById(id);
    }

    // Crear un nuevo RolHasUser
    public RolHasUserEntity createRolHasUser(RolHasUserEntity rolHasUser) {
        return rolHasUserDao.save(rolHasUser);
    }

    // Actualizar un RolHasUser existente
    public RolHasUserEntity updateRolHasUser(Long id, RolHasUserEntity updatedRolHasUser) {
        return rolHasUserDao.findById(id).map(rolHasUser -> {
            rolHasUser.setStatus(updatedRolHasUser.getStatus());
            rolHasUser.setUsersIdUsers(updatedRolHasUser.getUsersIdUsers());
            rolHasUser.setRolesIdRoles(updatedRolHasUser.getRolesIdRoles());
            return rolHasUserDao.save(rolHasUser);
        }).orElseThrow(() -> new RuntimeException("RolHasUser no encontrado con ID: " + id));
    }

    // Eliminar un RolHasUser por ID
    public void deleteRolHasUser(Long id) {
        rolHasUserDao.deleteById(id);
    }
}
