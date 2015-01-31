package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailClaimInfo extends Activity {
	private String message;
	private String email_to;
	private String subject;
	private int position;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_claim_info);
		Button SendButton = (Button) findViewById(R.id.SendButton);
		final EditText emailfield = (EditText) findViewById(R.id.receiver);
		TextView subjectField = (TextView) findViewById(R.id.subject);
		this.position = getIntent().getIntExtra("emailPos",0);
		Collection<Claim> claims = ClaimListController.getClaimList().getClaim();
		ArrayList<Claim>list = new ArrayList<Claim>(claims);
		Claim claim = list.get(position);
		subjectField.setText(claim+"");
		ArrayList<Expense> elist = claim.getExpenses();
		for (Expense e: elist){
			if (this.message == null){
				this.message = " "+e.getName()+"\n"+e.getCurrency();
			}else{
			this.message += e.getName()+"\t"+e.getCurrency();
			Toast.makeText(EmailClaimInfo.this, message, Toast.LENGTH_SHORT).show();
			}
		}
		this.subject = list.get(position).getName().toString();
		subjectField.setText(subject);
		SendButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				  Intent email = new Intent(Intent.ACTION_SEND);
				  email_to = emailfield.getText().toString();
				  email.putExtra(Intent.EXTRA_EMAIL, new String[]{email_to});
				  email.putExtra(Intent.EXTRA_SUBJECT, subject);
				  email.putExtra(Intent.EXTRA_TEXT, message);
				  
				  email.setType("message/rfc822");
				  startActivity(Intent.createChooser(email, "Choose an Email client :"));
				}
			
			});
					
	}
}
