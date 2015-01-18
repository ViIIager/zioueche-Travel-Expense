package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;

public class ClaimsList{
	protected ArrayList<Claim> claimList;
	
	public ClaimsList(){
		claimList = new ArrayList<Claim>();
	}
	
	public Collection<Claim> getClaim(){
		return claimList;
	}
	
	public void addClaim(Claim addclaim){
		claimList.add(addclaim);
	}
	
	public void deleteClaim(Claim removeclaim){
		assert(claimList.size() > 0);
		claimList.remove(removeclaim);
	}
}
