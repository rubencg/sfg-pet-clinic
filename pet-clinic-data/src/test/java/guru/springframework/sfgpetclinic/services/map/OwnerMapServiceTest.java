package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    @Mock
    private PetService petService;
    @Mock
    private PetTypeService petTypeService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(petService, petTypeService);
        ownerMapService.save(Owner.builder().address("some address").build());
    }

    @Test
    void findAll() {
        // given


        // when
        Set<Owner> ownerSet = ownerMapService.findAll();

        // then
        assertNotNull(ownerSet);
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findByLastName() {
    }

    @Test
    void save() {
    }
}