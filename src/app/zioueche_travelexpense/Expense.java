package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
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
