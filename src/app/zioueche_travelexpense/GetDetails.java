package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetDetails extends Activity {
	static int usd;
	static int cad;
	static int aud;
	static int gbp;
	static int eur;
	static Date sdate;
	static Date edate;
	
	protected void onCreate(Bundle savedInstanceState) {
		this.usd = 0;
		this.cad = 0;
		this.aud = 0;
		this.gbp = 0;
		this.eur = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_view);
		int finalPosition = getIntent().getIntExtra("claim_position",0);
		calculatePrices();
	}
	
	public ArrayList<Expense> getExpenses(){
		int finalPosition = getIntent().getIntExtra("claim_position",0);
		Toast.makeText(this, finalPosition+"",Toast.LENGTH_SHORT).show();
		ClaimListController ct = new ClaimListController();
		Collection<Claim> cl = ClaimListController.getClaimList().getClaim();
		ArrayList<Claim> clist = new ArrayList<Claim>(cl);
		Claim claim = clist.get(finalPosition);
		ArrayList<Expense> elist = claim.getExpenses();
		Claim c = clist.get(finalPosition);
		this.sdate = c.getSDate();
		this.edate = c.getEDate();
		return elist;
	}
	
	public void calculatePrices(){
		int finalPosition = getIntent().getIntExtra("claim_position",0);
		ArrayList<Expense> list = getExpenses();
		for (int i = 0; i < list.size(); i++){
			Expense e = list.get(i);
			if (e.getCurrency().equals("USD") ){
				GetDetails.usd += e.getPrice(); 
			}
			if (e.getCurrency().equals("CAD")){
				GetDetails.cad += e.getPrice();
			}
			if (e.getCurrency().equals("AUD")){
				GetDetails.aud += e.getPrice();
			}
			if (e.getCurrency().equals("EUR")){
				GetDetails.eur += e.getPrice();
			}
			if (e.getCurrency().equals("GBP")){
				GetDetails.gbp += e.getPrice();
			}
			
		}
		
		TextView usds = (TextView) findViewById(R.id.tot_usd);
		usds.setText("" + this.usd);
		TextView cads = (TextView) findViewById(R.id.tot_cad);
		cads.setText("" + this.cad);
		TextView auds = (TextView) findViewById(R.id.tot_aud);
		auds.setText("" + this.aud);
		TextView eurs = (TextView) findViewById(R.id.tot_eur);
		eurs.setText("" + this.eur);
		TextView gbps = (TextView) findViewById(R.id.tot_gbp);
		gbps.setText("" + this.gbp);
		TextView sdates = (TextView) findViewById(R.id.sdate);
  	  	String sdate = DateFormat.getDateInstance().format(this.sdate);
		sdates.setText(sdate);
		TextView edates = (TextView) findViewById(R.id.edate);
  	  	String edate = DateFormat.getDateInstance().format(this.edate);
		edates.setText(edate);
	}
}
