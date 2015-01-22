package app.zioueche_travelexpense;

import java.util.Date;

public class Expense {
	private String expname;
	private String expcurrency;
	private Date expdate;
	private int expprice;
	
	
	public Expense(String expname){
		this.expname = expname;
		//this.currency = expcurrency;
		//this.date = expdate;
		//this.price = expprice;
	}
	
	public String getName(){
		return this.expname;
	}
	
	public String getCurrency(){
		return this.expcurrency;
	}
	
	public Date getDate(){
		return this.expdate;
	}
	
	public int getPrice(){
		return this.expprice;
	}
	
	public String toString(){
		return getName();
	}
	//just testing the git repo
}
