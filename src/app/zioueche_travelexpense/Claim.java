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
	private Date sdate;
	private Date edate;
	private String status;
	
	/*add this to CONSTRUCTOR:  
	 * Date sdate, Date edate, String status, ArrayList<Expense> expenseList*/
	
	public Claim(String Name){
		this.Name = Name;
		expenseList = new ArrayList<Expense>();
		
		//this.sdate = sdate;
		//this.edate = edate;
		//this.status = status;
		//this.expenseList = expenseList;
	}
	
	public String getName(){
		return this.Name;
	}
	/*
	public ArrayList<Expense> getExpenses(){
		return expenseList;
	}
	*/
	public void addExpense(Expense expense){
		expenseList.add(expense);
	}
	
	public String toString(){
		return getName();
	}
	
	public String getStatus(){
		return status;
	}
	
	public Date getSDate(){
		return sdate;
	}
	
	public Date getEDate(){
		return edate;
	}

	public void editStatus(String status){
		this.status = status;
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

	public ArrayList<Expense> getExpenses() {
		// TODO Auto-generated method stub
		return expenseList;
	}
}
	
