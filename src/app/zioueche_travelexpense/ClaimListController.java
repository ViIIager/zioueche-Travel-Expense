package app.zioueche_travelexpense;

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
}
