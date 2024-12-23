package back_end.correspondencia.edu.ucb.bl;

import back_end.correspondencia.edu.ucb.persistence.dao.PersonDao;
import back_end.correspondencia.edu.ucb.persistence.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonBl {
    private final PersonDao personDao;

    @Autowired
    public PersonBl(PersonDao personDao) {
        this.personDao = personDao;
    }

    // Obtener todos los registros de personas
    public List<PersonEntity> getAllPersons() {
        return personDao.findAll();
    }

    // Obtener una persona por ID
    public Optional<PersonEntity> getPersonById(Long id) {
        return personDao.findById(id);
    }

    // Verificar si un CI ya existe
    public boolean existsByCi(String ci) {
        return personDao.existsByCi(ci);
    }

    // Crear o actualizar una persona (evitar duplicados)
    public PersonEntity saveOrUpdatePerson(PersonEntity person) {
        Optional<PersonEntity> existingPerson = personDao.findByCi(person.getCi());
        if (existingPerson.isPresent()) {
            // Actualizar el registro existente
            PersonEntity updatePerson = existingPerson.get();
            updatePerson.setName(person.getName());
            updatePerson.setFatherLastName(person.getFatherLastName());
            updatePerson.setMotherLastName(person.getMotherLastName());
            updatePerson.setEmail(person.getEmail());
            updatePerson.setCellPhone(person.getCellPhone());
            return personDao.save(updatePerson);
        } else {
            // Guardar nuevo registro
            return personDao.save(person);
        }
    }

    // Actualizar una persona existente
    public PersonEntity updatePerson(Long id, PersonEntity updatedPerson) {
        return personDao.findById(id).map(person -> {
            person.setName(updatedPerson.getName());
            person.setFatherLastName(updatedPerson.getFatherLastName());
            person.setMotherLastName(updatedPerson.getMotherLastName());
            person.setEmail(updatedPerson.getEmail());
            person.setCellPhone(updatedPerson.getCellPhone());
            return personDao.save(person);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }

    // Eliminar una persona por ID
    public void deletePerson(Long id) {
        personDao.deleteById(id);
    }
}
