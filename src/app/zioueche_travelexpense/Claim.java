package app.zioueche_travelexpense;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import android.widget.Toast;

public class Claim implements Serializable{
	/**
	 * Student serialized ID
	 */
	private static final long serialVersionUID = 3325687864575767244L;
	private String Name;
	private ArrayList<Expense> expenseList;

	public Claim(String Name){
		this.Name = Name;
	}
	public String getName(){
		return this.Name;
	}
	
	public String toString(){
		return getName();
	}
	
	public ArrayList<Expense> getExpenses(){
		return expenseList;
	}
	
	public void addExpense(Expense expense){
		expenseList.add(expense);
		//Toast.makeText(Claim.this, "added a test", Toast.LENGTH_SHORT).show();
	}
	
	public boolean equal(Object compareClaim){
		if (compareClaim != null && compareClaim.getClass()==this.getClass()){
			return this.equals((Claim) compareClaim);
		}else{
			return false;
		}
	}
	
	public int hashCode(){
		return ("Claim"+getName()).hashCode();
	}
}
	
