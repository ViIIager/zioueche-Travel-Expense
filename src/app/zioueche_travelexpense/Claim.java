package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

/* 
 * 
 *This is the MAIN OBJECT of the app. this is the claim that has the defining attributes of the app. it has name, start and 
 *end dates, as well as its own claim of expenses. 
 * 
 */


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
	
	//Claim object constructor on new create
	public Claim(String Name, Date sdate, Date edate){
		this.Name = Name;
		this.expenseList = new ArrayList<Expense>();
		this.sdate = sdate;
		this.edate = edate;
		this.status = "in Progress";
	}
	
	//contsructor on edit.
	public Claim(String Name, Date sdate, Date edate,String status, ArrayList<Expense> list){
		this.Name = Name;
		this.expenseList = list;
		this.sdate = sdate;
		this.edate = edate;
		this.status = "in Progress";
		this.status = status;
	}
	
	//get the claim name
	public String getName(){
		return this.Name;
	}

	//add an expense to the claim's expense list
	public void addExpense(Expense expense){
		expenseList.add(expense);
	}
	
	public void setExpenses(ArrayList<Expense> list){
		this.expenseList = list;
	}
	
	public void deleteExpense(int position){
		expenseList.remove(position);
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
	
