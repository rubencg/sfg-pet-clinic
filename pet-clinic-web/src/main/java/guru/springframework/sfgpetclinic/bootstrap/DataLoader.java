package guru.springframework.sfgpetclinic.bootstrap;


import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final SpecialtiesService specialtiesService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      PetService petService, SpecialtiesService specialtiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialtiesService = specialtiesService;
    }

    @Override
    public void run(String... args) throws Exception {
        Set<Owner> all = ownerService.findAll();
        if(all == null || all.size() == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Perro");
        dog = petTypeService.save(dog);
        PetType cat = new PetType();
        cat.setName("Gato");
        cat = petTypeService.save(cat);

        Pet chuchoPet = new Pet();
        chuchoPet.setPetType(dog);
        chuchoPet.setName("Chucho");
        chuchoPet.setBirthDate(LocalDate.now());
        chuchoPet = petService.save(chuchoPet);
        Pet ringoPet = new Pet();
        ringoPet.setPetType(cat);
        ringoPet.setName("Ringo");
        ringoPet.setBirthDate(LocalDate.now());
        ringoPet = petService.save(ringoPet);
        Pet chunga = new Pet();
        chunga.setPetType(cat);
        chunga.setName("Chunga");
        chunga.setBirthDate(LocalDate.now());
        chunga = petService.save(chunga);

        Owner ruben = new Owner();
        ruben.setFirstName("Ruben");
        ruben.setLastName("Cardenas");
        ruben.setAddress("Dalton 103");
        ruben.setCity("Apodaca");
        ruben.setTelephone("811423490");
        ruben = ownerService.save(ruben);

        ruben.getPets().add(chuchoPet);
        ruben.getPets().add(ringoPet);
        ruben = ownerService.save(ruben);

        chuchoPet.setOwner(ruben);
        petService.save(chuchoPet);
        ringoPet.setOwner(ruben);
        petService.save(ringoPet);


        Owner sarahi = new Owner();
        sarahi.setFirstName("Sarahi");
        sarahi.setLastName("Mireles");
        sarahi.setAddress("Saucito 567");
        sarahi.setCity("Chihuahua");
        sarahi.setTelephone("614978465");
        sarahi = ownerService.save(sarahi);

        sarahi.getPets().add(chunga);
        sarahi = ownerService.save(sarahi);

        chunga.setOwner(sarahi);
        petService.save(chunga);

        System.out.println("Loaded Owners...");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        radiology = specialtiesService.save(radiology);
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        surgery = specialtiesService.save(surgery);
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        dentistry = specialtiesService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Alejandro");
        vet1.setLastName("Cardenas");
        vet1 = vetService.save(vet1);
        vet1.getSpecialties().add(surgery);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Edith");
        vet2.setLastName("Mireles");
        vet2 = vetService.save(vet2);
        vet2.getSpecialties().add(dentistry);
        vet2.getSpecialties().add(radiology);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");

        Set<Owner> owners = ownerService.findAll();

        System.out.println("Owners: " + owners.size());
    }
}
