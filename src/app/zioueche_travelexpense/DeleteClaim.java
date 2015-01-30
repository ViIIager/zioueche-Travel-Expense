package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

public class DeleteClaim extends Activity {
	int finalPosition;
	protected void onCreate(Bundle SavedInstanceState){
		super.onCreate(SavedInstanceState);
		this.finalPosition = getIntent().getIntExtra("pospos",0);
		Collection<Claim> coll = ClaimListController.getClaimList().getClaim();
		final ArrayList<Claim> list = new ArrayList<Claim>(coll);
		AlertDialog.Builder adb = new AlertDialog.Builder(DeleteClaim.this);
			adb.setMessage("Delete "+ list.get(finalPosition).toString()+"?");
			adb.setCancelable(true);
			adb.setPositiveButton("Delete",new OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					Claim claim = list.get(finalPosition);
					ClaimListController.getClaimList().deleteClaim(claim);
					DeleteClaim.this.finish();
		}
	});
  	
	adb.setNegativeButton("Cancel",new OnClickListener(){

		@Override
		public void onClick(DialogInterface dialog, int which) {	
			DeleteClaim.this.finish();
		}
		
	});
	adb.show();
	
	}	
}

