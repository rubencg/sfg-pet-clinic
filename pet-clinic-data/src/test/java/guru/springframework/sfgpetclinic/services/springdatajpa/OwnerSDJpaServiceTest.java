package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;
    private Owner owner;

    @BeforeEach
    public void setUp(){
        Owner owner = Owner.builder().build();
        owner.setId(1L);
        String lastName = "Cardenas";
        owner.setLastName(lastName);
    }

    @Test
    void findById() {
        // given

        when(ownerRepository.findById(owner.getId()))
                .thenReturn(Optional.of(owner));

        // when
        Owner expectedOwner = ownerSDJpaService.findById(owner.getId());

        // then
        assertEquals(expectedOwner.getId(), expectedOwner.getId());
    }

    @Test
    void findByLastName() {
        // given
        when(ownerRepository.findByLastName(owner.getLastName()))
                .thenReturn(owner);

        // when
        Owner expectedOwner = ownerSDJpaService.findByLastName(owner.getLastName());

        // then
        assertEquals(expectedOwner.getLastName(), expectedOwner.getLastName());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner);

        verify(ownerRepository).delete(owner);
    }
}