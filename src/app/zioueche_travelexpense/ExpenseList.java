package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;

public class ExpenseList {
	protected static ArrayList<Expense> expenseList;
	protected ArrayList<Listener> listeners;
	
	public ExpenseList(){
		expenseList = new ArrayList<Expense>();
		listeners = new ArrayList<Listener>();
	}
	
	public Collection<Expense> getClaim(){
		return expenseList;
	}
	
	public void addExpense(Expense string){
		expenseList.add(string);
		notifyListeners();
	}
	
	public void deleteExpense(Expense expense){
		assert(expenseList.size() > 0);
		expenseList.remove(expense);
		notifyListeners();
	}
	
	public void notifyListeners(){
		for (Listener listener: listeners){
			listener.update();
		}
	}
	public void addListener(Listener l){
		listeners.add(l);
	}
	
	public void removeListener(Listener l){
		listeners.remove(l);
		
	}
}
