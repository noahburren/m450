package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AddressComparatorTest {

    private Address a(int id, String first, String last) {
        Address a = new Address();
        a.setId(id);
        a.setFirstname(first);
        a.setLastname(last);
        a.setRegistrationDate(new Date());
        return a;
    }

    @Test
    @DisplayName("Sortiert nach Lastname, dann Firstname, dann ID")
    void sortsAsSpecified() {
        List<Address> input = new ArrayList<>();
        input.add(a(3, "Zoe", "Meier"));
        input.add(a(2, "Anna", "Müller"));
        input.add(a(4, "Anna", "Müller"));
        input.add(a(1, "Beat", "Aebischer"));
        input.add(a(5, "Beat", "Aebischer"));

        List<Address> sorted = input.stream().sorted(new AddressComparator()).collect(Collectors.toList());

        assertEquals(1, sorted.get(0).getId());
        assertEquals(5, sorted.get(1).getId());
        assertEquals(3, sorted.get(2).getId());
        assertEquals(2, sorted.get(3).getId());
        assertEquals(4, sorted.get(4).getId());
    }

    @Test
    @DisplayName("Null-Werte werden ans Ende sortiert")
    void handlesNulls() {
        Address nullFirst = a(6, null, "Zahner");
        Address nullLast = a(7, "Lara", null);

        List<Address> sorted = List.of(nullLast, nullFirst).stream().sorted(new AddressComparator()).collect(Collectors.toList());

        assertEquals(6, sorted.get(0).getId());
        assertEquals(7, sorted.get(1).getId());
    }
}
