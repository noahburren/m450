package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address a(int id, String first, String last) {
        Address a = new Address();
        a.setId(id);
        a.setFirstname(first);
        a.setLastname(last);
        return a;
    }

    @BeforeEach
    void setup() { }

    @Test
    @DisplayName("save() delegiert ans Repository")
    void saveDelegates() {
        Address in = a(1, "Anna", "Müller");
        when(addressRepository.save(in)).thenReturn(in);

        Address out = addressService.save(in);

        assertSame(in, out);
        verify(addressRepository, times(1)).save(in);
    }

    @Test
    @DisplayName("getAll() sortiert Adressen mit Comparator")
    void getAllSorted() {
        List<Address> unsorted = Arrays.asList(
                a(3, "Zoe", "Meier"),
                a(2, "Anna", "Müller"),
                a(1, "Beat", "Aebischer")
        );
        when(addressRepository.findAll()).thenReturn(unsorted);

        List<Address> result = addressService.getAll();

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(3, result.get(1).getId());
        assertEquals(2, result.get(2).getId());
    }

    @Test
    @DisplayName("getAddress(id) gibt Optional zurück")
    void getAddressById() {
        Address adr = a(9, "Lara", "Zahner");
        when(addressRepository.findById(9)).thenReturn(Optional.of(adr));

        Optional<Address> res = addressService.getAddress(9);

        assertTrue(res.isPresent());
        assertEquals("Lara", res.get().getFirstname());
    }
}
