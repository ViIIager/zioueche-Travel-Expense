package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ClaimListController {
	private static ClaimsList claimList = null;
	
	static public ClaimsList getClaimList(){
		if (claimList == null){
			claimList = new ClaimsList();
		}
		return claimList;
	}
	
	public void addClaim(Claim claim){
		getClaimList().addClaim(claim);
	}
	
	public void addClaimIn(int position, Claim string){
		getClaimList().addClaimAt(position, string);
	}
	
}
