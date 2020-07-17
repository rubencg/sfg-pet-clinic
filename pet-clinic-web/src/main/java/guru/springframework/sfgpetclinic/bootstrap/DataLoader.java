package guru.springframework.sfgpetclinic.bootstrap;


import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Perro");
        PetType savedDog = petTypeService.save(dog);

        Pet pet = new Pet();
        pet.setPetType(savedDog);
        Pet savedPet = petService.save(pet);

        Owner owner1 = new Owner();
        owner1.setFirstName("Ruben");
        owner1.setLastName("Cardenas");
        owner1.setAddress("Dalton 103");
        owner1.setCity("Apodaca");
        owner1.setTelephone("811423490");
        owner1.setPets(new HashSet<>(Arrays.asList(savedPet)));
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Sarahi");
        owner2.setLastName("Mireles");
        owner2.setAddress("Saucito 567");
        owner2.setCity("Chihuahua");
        owner2.setTelephone("614978465");
        owner2.setPets(new HashSet<>(Arrays.asList(savedPet)));
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Alejandro");
        vet1.setLastName("Cardenas");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Edith");
        vet2.setLastName("Mireles");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
