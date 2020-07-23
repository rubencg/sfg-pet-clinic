package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(s -> s.getLastName().equals(lastName))
                .findFirst()
                .orElse(new Owner());
    }

    @Override
    public Owner save(Owner object) {

        if(object != null){
            if(object.getPets() != null){
                object.getPets().forEach(pet -> {
                    if(pet != null){
                        if(pet.getPetType() != null){
                            if(pet.getPetType().getId() == null){
                                pet.setPetType(petTypeService.save(pet.getPetType()));
                            }
                        }

                        if(pet.getId() == null){
                            Pet savedPet = petService.save(pet);
                            pet.setId(savedPet.getId());
                        }
                    }
                });
            }
        }

        return super.save(object);
    }
}
