package app.zioueche_travelexpense;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ClaimListManager {
	static String prefFile = "ClaimList";
	static String slkey = "claimList";
	Context context;
	
	public ClaimListManager(Context context){
		this.context = context;
	}
	
	public ClaimsList loadClaimList(){
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String ClaimListData = settings.getString("ClaimList", "");
		if (ClaimListData.equals("")){
			return new ClaimsList();
		}
		else{
			return ClaimsListFromString(ClaimListData);
		}
	}

	public void saveStudentList(){
		SharedPreferences settings = context.getSharedPreferences(prefFile,Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(slkey, ClaimsListToString());
		
	}

	private String ClaimsListToString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ClaimsList ClaimsListFromString(String claimListData) {
		// TODO Auto-generated method stub
		return null;
	}
}
