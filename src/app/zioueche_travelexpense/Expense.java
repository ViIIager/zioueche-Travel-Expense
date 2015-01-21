package app.zioueche_travelexpense;

import java.util.Date;

public class Expense {
	private String expname;
	private String currency;
	private Date date;
	private int price;
	
	
	public Expense(String expname, String currency, Date date, int price){
		this.expname = expname;
		this.currency = currency;
		this.date = date;
		this.price = price;
	}
	public String getName(){
		return this.expname;
	}
	
	public String getCurrency(){
		return this.currency;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public String toString(){
		return getName();
	}
	//just testing the git repo
}
