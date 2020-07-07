package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(s -> s.getLastName().equals(lastName))
                .findFirst()
                .orElse(new Owner());
    }
}
