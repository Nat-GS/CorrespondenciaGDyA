package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.persistence.dao.RolesDao;
import back_end.correspondencia.edu.ucb.persistence.entity.RolesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesBl {
    private final RolesDao rolesDao;

    @Autowired
    public RolesBl(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    // Obtener todos los roles
    public List<RolesEntity> getAllRoles() {
        return rolesDao.findAll();
    }

    // Obtener un rol por ID
    public Optional<RolesEntity> getRoleById(Long id) {
        return rolesDao.findById(id);
    }

    // Crear un nuevo rol
    public RolesEntity createRole(RolesEntity role) {
        return rolesDao.save(role);
    }

    // Actualizar un rol existente
    public RolesEntity updateRole(Long id, RolesEntity updatedRole) {
        return rolesDao.findById(id).map(role -> {
            role.setUserRole(updatedRole.getUserRole());
            role.setStatus(updatedRole.getStatus());
            return rolesDao.save(role);
        }).orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }

    // Eliminar un rol por ID
    public void deleteRole(Long id) {
        rolesDao.deleteById(id);
    }
}
