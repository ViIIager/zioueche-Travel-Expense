package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.util.Comparator;

public class CustomComparatorClaim implements Comparator<Claim> {
    @Override
    public int compare(Claim c1, Claim c2) {
        return c1.getSDate().compareTo(c2.getSDate());
    }
}