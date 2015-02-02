package app.zioueche_travelexpense;

/* 
 * 
 *Simple activity to change the status of a claim.  once a status is set to approved or Sumbitted, we can no longer edit it. 
 * 
 */



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
