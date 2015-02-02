package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

//Simple activity to delete a claim. reason for the activity is so that I can refresh the listview on restrt without having to 
//call a maze of listeners.
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
					Intent backTOClaim = new Intent(DeleteClaim.this, AddClaim.class);
					startActivity(backTOClaim);
					finish();
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

