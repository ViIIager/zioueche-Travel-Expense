package app.zioueche_travelexpense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import android.text.InputFilter.LengthFilter;
import android.widget.Toast;

public class ExpenseList {
	
	protected static ArrayList<Expense> expenseList;
	protected ArrayList<Listener> listeners;
	
	public ExpenseList(){
		expenseList = new ArrayList<Expense>();
		listeners = new ArrayList<Listener>();
	}
	
	public Collection<Expense> getExpense(){
		return expenseList;
	}
	
	public void addExpense(Expense string){
		expenseList.add(string);
		notifyListeners();
	}
	
	public void deleteExpense(Expense removeexp){
		expenseList.remove(removeexp);
		notifyListeners();
	}
	
	public static boolean isEmpty(){
		return expenseList.size()== 0;
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

