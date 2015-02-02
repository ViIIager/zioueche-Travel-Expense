package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.util.Comparator;

//allows me to compare the expenses however I want.
public class CustomComparatorExpense implements Comparator<Expense> {
    @Override
    public int compare(Expense e1, Expense e2) {
        return e1.getCDate().compareTo(e2.getCDate());
    }
}