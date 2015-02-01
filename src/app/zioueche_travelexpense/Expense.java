package app.zioueche_travelexpense;

import java.util.Date;

public class Expense {
	private String expname;
	private String expcurrency;
	private Date expdate;
	private float expprice;

	public Expense(String expname,Date expdate, String expcurrency, float price){
		this.expname = expname;
		this.expdate = expdate;
		this.expcurrency = expcurrency;
		this.expprice = price;
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
	
	public float getPrice(){
		return this.expprice;
	}
	
	public String toString(){
		return getName();
	}
	
	//just testing the git repo
	public Date getCDate() {
		return expdate;
	}

}
