package app.zioueche_travelexpense;

import java.util.Comparator;

public class CustomComparatorExpense implements Comparator<Expense> {
    @Override
    public int compare(Expense e1, Expense e2) {
        return e1.getCDate().compareTo(e2.getCDate());
    }
}