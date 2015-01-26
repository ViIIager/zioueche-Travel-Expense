package app.zioueche_travelexpense;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

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
	
	//Claim object constructor NEED TO ADD STATUS
	public Claim(String Name, Date sdate2, Date edate2){
		this.Name = Name;
		this.expenseList = new ArrayList<Expense>();
		this.sdate = sdate2;
		this.edate = edate2;
		//this.status = status;
	}
	
	//get the claim name
	public String getName(){
		return this.Name;
	}

	//add an expense to the claim's expense list
	public void addExpense(Expense expense){
		expenseList.add(expense);
	}
	
	//change the name to a string.
	public String toString(){
		return getName();
	}
	
	//return the status of the string
	public String getStatus(){
		return status;
	}
	
	//get the start date of the claim
	public Date getSDate(){
		return sdate;
	}
	
	//get the end date of the claim
	public Date getEDate(){
		return edate;
	}
	
	//change the status of the Claim.
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
	
	//return the expenses list of the Claim
	public ArrayList<Expense> getExpenses() {
		// TODO Auto-generated method stub
		return expenseList;
	}
}
	
