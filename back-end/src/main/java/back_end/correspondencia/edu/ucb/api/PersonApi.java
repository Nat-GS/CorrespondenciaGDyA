package back_end.correspondencia.edu.ucb.api;

import back_end.correspondencia.edu.ucb.bl.PersonBl;
import back_end.correspondencia.edu.ucb.dto.response.PersonResponse;
import back_end.correspondencia.edu.ucb.persistence.entity.PersonEntity;
import back_end.correspondencia.edu.ucb.util.Globals;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Globals.apiVersion+"person")
public class PersonApi {
    private static final Logger logger = LoggerFactory.getLogger(PersonApi.class);
    @Autowired
    private PersonBl personBl;

    @GetMapping("/all")
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        try {
            logger.info("Iniciando petición GET /all para obtener todas las personas.");
            List<PersonResponse> persons = personBl.getAllPersons()
                    .stream()
                    .map(person -> new PersonResponse().personEntityToResponse(person))
                    .collect(Collectors.toList());
            logger.info("Operación exitosa: Se obtuvieron {} personas.", persons.size());
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            logger.error("Error al obtener la lista de personas: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<PersonEntity> saveOrUpdatePerson(@Valid @RequestBody PersonEntity person) {
        try {
            logger.info("Iniciando petición POST / para crear o actualizar una persona: {}", person);
            PersonEntity savedPerson = personBl.saveOrUpdatePerson(person);
            logger.info("Operación exitosa: Persona creada o actualizada con ID: {}", savedPerson.getIdPerson());
            return ResponseEntity.ok(savedPerson);
        } catch (IllegalArgumentException e) {
            logger.warn("Advertencia: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            logger.error("Error al crear o actualizar la persona: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
