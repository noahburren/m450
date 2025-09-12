package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    private AddressService addressService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        // Controller manuell mit gemocktem Service verdrahten
        AddressController controller = new AddressController(addressService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    private Address a(int id, String first, String last) {
        Address a = new Address();
        a.setId(id);
        a.setFirstname(first);
        a.setLastname(last);
        return a;
    }

    @Test
    @DisplayName("GET /address -> 200 und JSON-Liste")
    void getAllAddresses() throws Exception {
        when(addressService.getAll()).thenReturn(Arrays.asList(
                a(1, "Beat", "Aebischer"),
                a(2, "Anna", "Müller")
        ));

        mockMvc.perform(get("/address").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].firstname").value("Anna"));
    }

    @Test
    @DisplayName("GET /address/{id} -> 200 bei Treffer, 404 sonst")
    void getAddressById() throws Exception {
        when(addressService.getAddress(9)).thenReturn(Optional.of(a(9, "Lara", "Zahner")));
        when(addressService.getAddress(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/address/9").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname").value("Zahner"));

        mockMvc.perform(get("/address/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
