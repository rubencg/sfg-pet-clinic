package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
        owner = Owner.builder().build();
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
        assertEquals(owner.getId(), expectedOwner.getId());
    }

    @Test
    void findByLastName() {
        // given
        when(ownerRepository.findByLastName(owner.getLastName()))
                .thenReturn(owner);

        // when
        Owner expectedOwner = ownerSDJpaService.findByLastName(owner.getLastName());

        // then
        assertEquals(owner.getLastName(), expectedOwner.getLastName());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner);

        verify(ownerRepository).delete(owner);
    }

    @Test
    void findByLastNameLike() {
        // given
        when(ownerRepository.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(owner));

        // when
        List<Owner> expectedOwner = ownerSDJpaService.findAllByLastNameLike("rdenas");

        // then
        assertEquals(owner.getLastName(), expectedOwner.get(0).getLastName());
    }
}