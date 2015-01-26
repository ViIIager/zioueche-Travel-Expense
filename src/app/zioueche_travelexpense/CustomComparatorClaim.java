package app.zioueche_travelexpense;

import java.util.Comparator;

public class CustomComparatorClaim implements Comparator<Claim> {
    @Override
    public int compare(Claim c1, Claim c2) {
        return c1.getSDate().compareTo(c2.getSDate());
    }
}