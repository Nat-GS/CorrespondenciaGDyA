package back_end.correspondencia.edu.ucb.api;

import back_end.correspondencia.edu.ucb.bl.RolesBl;
import back_end.correspondencia.edu.ucb.persistence.entity.RolesEntity;
import back_end.correspondencia.edu.ucb.util.Globals;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Globals.apiVersion + "roles")
public class RolesApi {
    private static final Logger logger = LoggerFactory.getLogger(RolesApi.class);

    @Autowired
    private RolesBl rolesBl;

    @GetMapping("/all")
    public ResponseEntity<List<RolesEntity>> getAllRoles() {
        try {
            logger.info("Iniciando petición GET /all para obtener todos los roles.");
            List<RolesEntity> roles = rolesBl.getAllRoles();
            logger.info("Operación exitosa: Se obtuvieron {} roles.", roles.size());
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            logger.error("Error al obtener la lista de roles: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<RolesEntity> createRole(@Valid @RequestBody RolesEntity role) {
        try {
            logger.info("Iniciando petición POST / para crear un nuevo rol: {}", role);
            RolesEntity createdRole = rolesBl.createRole(role);
            logger.info("Operación exitosa: Rol creado con ID: {}", createdRole.getIdRole());
            return ResponseEntity.ok(createdRole);
        } catch (Exception e) {
            logger.error("Error al crear el rol: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesEntity> getRoleById(@PathVariable Long id) {
        try {
            logger.info("Iniciando petición GET /{} para obtener un rol.", id);
            RolesEntity role = rolesBl.getRoleById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            logger.error("Error al obtener el rol con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesEntity> updateRole(@PathVariable Long id, @Valid @RequestBody RolesEntity updatedRole) {
        try {
            logger.info("Iniciando petición PUT /{} para actualizar un rol.", id);
            RolesEntity role = rolesBl.updateRole(id, updatedRole);
            logger.info("Operación exitosa: Rol con ID {} actualizado.", id);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            logger.error("Error al actualizar el rol con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            logger.info("Iniciando petición DELETE /{} para eliminar un rol.", id);
            rolesBl.deleteRole(id);
            logger.info("Operación exitosa: Rol con ID {} eliminado.", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar el rol con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

