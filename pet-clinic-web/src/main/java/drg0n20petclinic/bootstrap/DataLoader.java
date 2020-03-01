package drg0n20petclinic.bootstrap;

import drg0n20petclinic.model.*;
import drg0n20petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Micky");
        owner1.setLastName("Mouse");
        owner1.setAddress("ul. Mickiewicza 12");
        owner1.setCity("Wroclaw");
        owner1.setTelephone("789456123");

        Pet mickeyPet = new Pet();
        mickeyPet.setPetType(savedDogPetType);
        mickeyPet.setOwner(owner1);
        mickeyPet.setBirthDate(LocalDate.now());
        mickeyPet.setName("Rex");
        owner1.getPets().add(mickeyPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Rosie");
        owner2.setLastName("Mandalope");
        owner2.setAddress("ul. Dluga 2");
        owner2.setCity("Warszawa");
        owner2.setTelephone("789789789");

        Pet rosieCat = new Pet();
        rosieCat.setName("Pan Kot Myszku");
        rosieCat.setOwner(owner2);
        rosieCat.setBirthDate(LocalDate.now());
        rosieCat.setPetType(savedCatPetType);
        owner2.getPets().add(rosieCat);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(rosieCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("John");
        vet1.setLastName("Dope");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Charlie");
        vet2.setLastName("Sheen");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
