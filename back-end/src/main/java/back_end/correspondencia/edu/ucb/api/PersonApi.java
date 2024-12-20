package back_end.correspondencia.edu.ucb.api;

import back_end.correspondencia.edu.ucb.bl.PersonBl;
import back_end.correspondencia.edu.ucb.persistence.entity.PersonEntity;
import back_end.correspondencia.edu.ucb.util.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Globals.apiVersion+"person")
public class PersonApi {
    private static final Logger logger = LoggerFactory.getLogger(PersonApi.class);
    @Autowired
    private PersonBl personBl;

    @GetMapping("/all")
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        try {
            logger.info("Iniciando petición GET /all para obtener todas las personas.");
            List<PersonEntity> persons = personBl.getAllPersons();
            logger.info("Operación exitosa: Se obtuvieron {} personas.", persons.size());
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            logger.error("Error al obtener la lista de personas: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
        try {
            logger.info("Iniciando petición POST / para crear una nueva persona: {}", person);
            PersonEntity createdPerson = personBl.createPerson(person);
            logger.info("Operación exitosa: Persona creada con ID: {}", createdPerson.getIdPerson());
            return ResponseEntity.ok(createdPerson);
        } catch (Exception e) {
            logger.error("Error al crear la persona: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
