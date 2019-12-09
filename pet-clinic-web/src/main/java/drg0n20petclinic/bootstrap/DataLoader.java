package drg0n20petclinic.bootstrap;

import drg0n20petclinic.model.Owner;
import drg0n20petclinic.model.PetType;
import drg0n20petclinic.model.Vet;
import drg0n20petclinic.services.OwnerService;
import drg0n20petclinic.services.PetTypeService;
import drg0n20petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Micky");
        owner1.setLastName("Mouse");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Bat");
        owner2.setLastName("Man");

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Reksio");
        vet1.setLastName("Piesek");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Pluto");
        vet2.setLastName("Dog");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
