package app.zioueche_travelexpense;

import java.sql.Date;

public class Claim {
	private String Name;

	public Claim(String Name){
		this.Name = Name;
	}
	public String getName(){
		return this.Name;
	}
	
	public String toString(){
		return getName();
	}
}
	
