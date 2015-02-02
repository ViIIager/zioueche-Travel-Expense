package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/* 
 * 
 *As Seen in Student Picker, this allows me to access the Claimslist without the use of a user interface. 
 * 
 */

public class ClaimListController {
	private static ClaimsList claimList = null;
	
	static public ClaimsList getClaimList(){
		if (claimList == null){
			claimList = new ClaimsList();
		}
		return claimList;
	}
	
	public void addClaim(Claim claim) throws IOException{
		getClaimList().addClaim(claim);
	}
	
	public void addClaimIn(int position, Claim string){
		getClaimList().addClaimAt(position, string);
	}
	
}
