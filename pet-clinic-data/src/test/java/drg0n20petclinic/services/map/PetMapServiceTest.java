package drg0n20petclinic.services.map;

import drg0n20petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetMapServiceTest {

    PetMapService petMapService;

    final Long petId = 1L;
    final String name = "Janusz";

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();

        petMapService.save(Pet.builder().name(name).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertEquals(1, petSet.size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void save() {
        Pet savedPet = petMapService.save(Pet.builder().build());

        assertNotNull(savedPet);

        assertNotNull(savedPet.getId());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);

        assertEquals(petId, pet.getId());
    }
}