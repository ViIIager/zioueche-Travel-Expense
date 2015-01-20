package app.zioueche_travelexpense;

import java.sql.Date;
import java.util.ArrayList;

public class Claim {
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
}
	
