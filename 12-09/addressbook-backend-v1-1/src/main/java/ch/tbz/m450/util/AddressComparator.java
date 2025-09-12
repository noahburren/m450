package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {
    private static final Comparator<Address> C =
            Comparator.comparing(Address::getLastname,
                            Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                    .thenComparing(Address::getFirstname,
                            Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                    .thenComparingInt(Address::getId);

    @Override
    public int compare(Address a1, Address a2) {
        return C.compare(a1, a2);
    }
}
