package app.zioueche_travelexpense;
<<<<<<< HEAD

/* 
 * 
 *Simple activity to change the status of a claim.  once a status is set to approved or Sumbitted, we can no longer edit it. 
 * 
 */



=======
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
>>>>>>> 71629b4ee02516c67db3c3ab724f479b70ddb0a2
import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
	
	
public class ChangeStatus extends Activity {
	private int pos;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_claim_status);
		this.pos = getIntent().getIntExtra("popopo",0);
	}
	
	public void ChangeStatus(View v){
		ClaimListController ct = new ClaimListController();
		Collection<Claim>coll = ct.getClaimList().getClaim();
		ArrayList<Claim> list = new ArrayList<Claim>(coll);
		
		RadioGroup rd= (RadioGroup)findViewById(R.id.statusChange);
		
		if(rd.getCheckedRadioButtonId()!=-1){
		    int id= rd.getCheckedRadioButtonId();
		    View radioButton = rd.findViewById(id);
		    int radioId = rd.indexOfChild(radioButton);
		    RadioButton btn = (RadioButton) rd.getChildAt(radioId);
		    String selection = (String) btn.getText();
		list.get(this.pos).editStatus(selection);
		}
	Intent intent = new Intent(this, AddClaim.class);
	startActivity(intent);	
	finish();
	}
}
