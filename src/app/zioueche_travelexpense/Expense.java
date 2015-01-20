package app.zioueche_travelexpense;

public class Expense {
	private String expname;
	
	public Expense(String expname){
		this.expname = expname;
	}
	public String getName(){
		return this.expname;
	}
	
	public String toString(){
		return getName();
	}
}
