package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController ownerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void index() throws Exception {
        HashSet<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().build());
        when(ownerService.findAll())
                .thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owners", owners))
                .andExpect(view().name("owners/index"));
    }

    @Test
    void showOwner() throws Exception {
        // given
        Long ownerId = 1L;
        Owner owner = Owner.builder().build();
        owner.setId(ownerId);
        when(ownerService.findById(ownerId))
                .thenReturn(owner);

        // when
        ResultActions resultActions = mockMvc.perform(get(String.format("/owners/%s", ownerId)));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("id", is(ownerId))))
                .andExpect(view().name("owners/ownerDetails"));
    }
}