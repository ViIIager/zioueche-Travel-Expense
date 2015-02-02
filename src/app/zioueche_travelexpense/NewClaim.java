package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
//simple activity to add a claim.
public class NewClaim extends Activity {
	String name;
	Date sdate;
	Date edate;
	ArrayList<Claim> claim;
	ListView listView;
	Claim claimadd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_add_page);
	}
	
	public void addClaims(View v) throws IOException{
		ClaimListController ct = new ClaimListController();	
		ct.addClaim(claimadd);
		Toast.makeText(this,"Added "+name, Toast.LENGTH_SHORT).show();
		
	}
	
	public void getClaimName(View v){
		EditText textView = (EditText) findViewById(R.id.add_claim_field);
		String added = textView.getText().toString();
		if (!TextUtils.isEmpty(added)){
			//1. Gets name of Claim
			this.name = added;
			textView.setText("");
			setContentView(R.layout.claim_add_sdate);
		}else{
			Toast.makeText(this,"Please enter a Name before continuing", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getSDate(View v){
		DatePicker sdatePicker = (DatePicker)findViewById(R.id.sdate_picker);
		String Sdate = "not_empty";
		if (!TextUtils.isEmpty(Sdate)){
	        this.sdate = getDateFromDatePicket(sdatePicker);;
	        setContentView(R.layout.claim_add_edate);
		}else{
			Toast.makeText(this,"Please enter a Start date before continuing", Toast.LENGTH_SHORT).show();
		}
        setContentView(R.layout.claim_add_edate);
	}
	
	public void getEDate(View v) throws IOException{
		DatePicker edatePicker = (DatePicker)findViewById(R.id.edate_picker);
		if (!getDateFromDatePicket(edatePicker).before(this.sdate)){
	        this.edate = getDateFromDatePicket(edatePicker);
	        claimadd = new Claim(name, sdate, edate);
	        addClaims(v);
			Intent intent = new Intent(this, AddClaim.class);
			startActivity(intent);
			finish();
		}else{
			Toast.makeText(this,"End Date cannot come before start date. Please select another date", Toast.LENGTH_SHORT).show();
		}
	}
	

	//just a format string maker.
	private String format(String string, String added) {
		String formats = string +" "+ added; 
		return formats;
	}
	
	//Get Date from DatePicker as a date;
	public static java.util.Date getDateFromDatePicket(DatePicker datePicker){
	    int day = datePicker.getDayOfMonth();
	    int month = datePicker.getMonth();
	    int year =  datePicker.getYear();

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(year, month, day);
	    return calendar.getTime();
	}
}

