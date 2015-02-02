package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.text.DateFormat;
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


//takes the claim and expense information and allows me to email it to whoever I want.
public class EmailClaimInfo extends Activity {
	private String message = "";
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
		message += "This Claim and its constituent expenses have been emailed to you.\n\n";
		message += new StringBuilder()
        .append("Claim Name:     ")
        .append(claim.getName()+"\n")
        .append("Start Date:         ")
        .append(DateFormat.getDateInstance().format(claim.getSDate())+"\n")
        .append("End Date:           ")
        .append(DateFormat.getDateInstance().format(claim.getEDate())+"\n")
        .append("Number of Expenses:     "+claim.getExpenses().size())
        .append("\n").append("\n").append("\n").toString();
		int count = 0;
		for (Expense e: elist){
			count += 1;
			message += new StringBuilder()
		           .append("Expense "+count+"\n")
		           .append("Name:     ")
		           .append(e.getName()+"\n")
		           .append("Date:        ")
		           .append(DateFormat.getDateInstance().format(e.getCDate())+"\n")
		           .append("Price:       ")
		           .append(e.getPrice()+"\n")
		           .append("Currency:     ")
		           .append(e.getCurrency()+"\n")
		           .append("\n")
		           .append("\n")
		           .toString();
			}
		this.subject = list.get(position).getName().toString();
		subjectField.setText("Claim as requested: "+subject);
		EditText messageField = (EditText)findViewById(R.id.editTextMessage);
		messageField.setText(message);
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
				  finish();
				}
			
			});
					
	}
}
