package app.zioueche_travelexpense;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ClaimListManager {
	static final String prefFile = "ClaimList";
	static final  String clkey = "claimList";
	Context context;
	
	public ClaimListManager(Context context){
		this.context = context;
	}
	
	public ClaimsList loadClaimList() throws IOException, ClassNotFoundException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String ClaimListData = settings.getString("clkey", "");
		if (ClaimListData.equals("")){
			return new ClaimsList();
		}
		else{
			return ClaimsListFromString(ClaimListData);
		}
	}

	public void saveClaimList(ClaimsList cl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile,Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(clkey, ClaimsListToString(cl));
		editor.commit(); 
		
	}

	private ClaimsList ClaimsListFromString(String ClaimsListData) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(ClaimsListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ClaimsList)oi.readObject();
	}
	
	private String ClaimsListToString(ClaimsList cl) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(cl);
		oo.close();
		byte bytes [] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
}
