package app.zioueche_travelexpense;

/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

//figure out how to add claim in different page.  so we can add date range.
public class AddClaim extends ListActivity {
	Context ctx;
	File filePath = new File("fileName.sav");
	String name;
	Date sdate;
	Date edate;
	CustomAdapterClaim claimAdapter;
	ArrayList<Claim> claim;
	ListView listView;
	//SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
    //SharedPreferences.Editor ed = mPrefs.edit();
    //ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
	//private CustomAdapterClaim claimAdapter;
	//ArrayList<Claim> claim = (ArrayList<Claim>) ClaimListController.getClaimList().getClaim();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim);
		//adapter for claim view
		
		listView = (ListView) findViewById(android.R.id.list);
		
		Collection<Claim> claims = ClaimListController.getClaimList().getClaim();
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
		if (list.size() > 1){
			Collections.sort(list, new CustomComparatorClaim());
		}
		final CustomAdapterClaim claimAdapter = new CustomAdapterClaim(this, R.layout.custom_view_claim, list);
	    setListAdapter(claimAdapter);
		claimAdapter.notifyDataSetChanged();
		
		//Added observer pattern
		ClaimListController.getClaimList().addListener(new Listener(){
			@Override
			public void update(){
			list.clear();
			Collection<Claim> claims = ClaimListController.getClaimList().getClaim();
			list.addAll(claims);
			claimAdapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(AddClaim.this, "Clicked "+list.get(position), Toast.LENGTH_SHORT).show();
				Intent onclick = new Intent(AddClaim.this, ExpenseDetails.class);
				onclick.putExtra("claimpos", position);
				startActivity(onclick);
			}
		});
		
		//LONG CLICK FUNCTIONS
		listView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final int finalPosition = position;
				PopupMenu popup = new PopupMenu(AddClaim.this, view);
				popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
		            public boolean onMenuItemClick(MenuItem item) {  
		            //DELETE button check.  
		            	if (item.getTitle().equals("Delete")){
		            		if (list.get(finalPosition).getStatus().equals("Submitted")){
		            			Toast.makeText(AddClaim.this, "You cannot edit a submitted claim until it has been approved", Toast.LENGTH_SHORT).show();
		            		}else{
		            		  Intent delete = new Intent(AddClaim.this, DeleteClaim.class);
		            		  delete.putExtra("pospos", finalPosition);
		            		  //Toast.makeText(AddClaim.this, ""+finalPosition, Toast.LENGTH_SHORT).show();
		            		  startActivity(delete);
		            		  finish();
				   
		            	  	}
		              }	
		              
		              //START of ADD EXPENSE check
		              if (item.getTitle().equals("Add Expense")){
		            	  if (list.get(finalPosition).getStatus().equals("Submitted") || list.get(finalPosition).getStatus().equals("Approved")){
		            		  Toast.makeText(AddClaim.this, "You cannot edit a submitted or approved claim", Toast.LENGTH_SHORT).show();
		            	  	}else{
			            	  Intent add_exp = new Intent(AddClaim.this, ExpenseAdd.class);
			            	  add_exp.putExtra("somename", finalPosition);
			            	  startActivity(add_exp);
			            	  finish();
		            	  	}
		            	  
		 		              }
		              //what do do if Details button is clicked
		              if (item.getTitle().equals("Get Claim Details")){
		            	  Intent get_detail = new Intent(AddClaim.this, GetDetails.class);
		            	  get_detail.putExtra("claim_position", finalPosition);
		            	  startActivity(get_detail);
		              }
		              
		              //Edit the claim we want.
		              if (item.getTitle().equals("Edit Claim")){
		            	  if (list.get(finalPosition).getStatus().equals("Submitted") || list.get(finalPosition).getStatus().equals("Approved")){
		            		  Toast.makeText(AddClaim.this, "You cannot edit a submitted or approved claim", Toast.LENGTH_SHORT).show();
		            	  	}else{
		            	  		Intent edit = new Intent(AddClaim.this, EditClaim.class);
				            	  edit.putExtra("pos", finalPosition);
				            	  startActivity(edit);
				            	  finish();
			            		  claimAdapter.notifyDataSetChanged();
		            	  	}
		            	  
		            	  }
		              if (item.getTitle().equals("Change Claim Status")){
		            	  if (list.get(finalPosition).getStatus().equals("Submitted")){
		            		  AlertDialog.Builder adb = new AlertDialog.Builder(AddClaim.this);
		          			adb.setMessage("Set "+ list.get(finalPosition).toString()+"'s status to Returned?");
		          			adb.setCancelable(true);
		          			adb.setPositiveButton("Yes",new OnClickListener(){
		          				public void onClick(DialogInterface dialog, int which) {
		          					Claim claim = list.get(finalPosition);
		          					claim.editStatus("Returned");
		          					claimAdapter.notifyDataSetChanged();
		          				}
		          				
		          			});
				            	
		          			adb.setNegativeButton("No",new OnClickListener(){
		          				@Override
				          		public void onClick(DialogInterface dialog, int which) {	
				          			Toast.makeText(AddClaim.this, "You can only change to returned while claim is submitted", Toast.LENGTH_LONG).show();
				          		}
		          			});
				          	adb.show();
				            	  }
		            	  else if (list.get(finalPosition).getStatus().equals("Approved")){
		            		  Toast.makeText(AddClaim.this, "You can no longer make any changes to this claim.", Toast.LENGTH_SHORT).show();
		            	  	}else{
		            	  		Intent changeStatus = new Intent(AddClaim.this, ChangeStatus.class);
		            	  		changeStatus.putExtra("popopo", finalPosition);
		            	  		startActivity(changeStatus);
		            	  		finish();
		            	  	}
		              }
		              if (item.getTitle().equals("Email Claim")){
		            	  Intent email = new Intent(AddClaim.this, EmailClaimInfo.class);
		            	  email.putExtra("emailPos", finalPosition);
		            	  startActivity(email);
		              }
				            	  
		              return true;  
		            }  
		            });  
				popup.show();
			return true;
			}
			
		});
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addClaimView(MenuItem item){
		Toast.makeText(this, "going to claim creation page", Toast.LENGTH_SHORT).show();
		//goToNew();
		
		//setContentView(R.layout.claim_add_page);
	}

	public void goToNew(View v) {
		Intent test = new Intent(this, NewClaim.class);
		startActivity(test);
		finish();
	}
	
	/*@Override
	protected void onStart(){
		super.onStart();
		claim = ClaimListController.getClaimList().loadFromFile();
		//ClaimListController.getClaimList().deserialize();
		
		ClaimListController.getClaimList();
		if (ClaimsList.claimList==null){
			claim = new ArrayList<Claim>();
		}
		Toast.makeText(this, claim.size()+"", Toast.LENGTH_SHORT).show();
		claimAdapter = new CustomAdapterClaim(this,
				R.layout.custom_view_claim, claim);
		listView.setAdapter(claimAdapter);
	}*/

}
