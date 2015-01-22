package app.zioueche_travelexpense;

public class ExpenseListController {
	private static ExpenseList expenseList = null;
	
	static public ExpenseList getExpenseList(){
		if (expenseList == null){
			expenseList = new ExpenseList();
		}
		return expenseList;
	}
	
	public void addExpense(Expense expense){
		getExpenseList().addExpense(expense);
	}
}
