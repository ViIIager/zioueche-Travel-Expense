package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class EditClaim extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.name_change);
		
  		 	
	}
	public void editComplete(View v){
		int finalPosition = getIntent().getIntExtra("pos",0);
		EditText new_name = (EditText) findViewById(R.id.new_claim_name);
  	  	String p = new_name.getText().toString();
  	  	String added = p;
  	  	if (!TextUtils.isEmpty(added)){
  		 	ClaimListController ct = new ClaimListController();
  		 	Collection<Claim> mlist = ClaimListController.getClaimList().getClaim();
  		 	ArrayList<Claim> list = new ArrayList<Claim>(mlist);
  		 	ArrayList<Expense> newlist = list.get(finalPosition).getExpenses();
  		 	Date nedate = list.get(finalPosition).getEDate();
  		 	Date nsdate = list.get(finalPosition).getSDate();
  		 	String nstatus = list.get(finalPosition).getStatus();
  		 	Claim remove_claim = list.get(finalPosition);
  		 	Claim claim = new Claim(added, nsdate, nedate, nstatus, newlist);
  		 	ClaimListController.getClaimList().deleteClaim(remove_claim);
  		 	ct.addClaimIn(finalPosition, claim);
		Intent intent = new Intent(this, AddClaim.class);
		startActivity(intent);
	}
	}
}
