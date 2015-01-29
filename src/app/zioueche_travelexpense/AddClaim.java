package app.zioueche_travelexpense;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
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
public class AddClaim extends Activity {
	private static final String SAVEFILE = "file.sav";
	String name;
	Date sdate;
	Date edate;
	ArrayList<Claim> claim;
	ListView listView;
	//ArrayList<Claim> claim = (ArrayList<Claim>) ClaimListController.getClaimList().getClaim();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim);

		//adapter for claim view
		listView = (ListView) findViewById(R.id.claimListView);
		Collection<Claim> claims = ClaimListController.getClaimList().getClaim();
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
		if (list.size() > 1){
			Collections.sort(list, new CustomComparatorClaim());
		}
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(claimAdapter);
		
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
		            	AlertDialog.Builder adb = new AlertDialog.Builder(AddClaim.this);
		  				adb.setMessage("Delete "+ list.get(finalPosition).toString()+"?");
		  				adb.setCancelable(true);
		  				adb.setPositiveButton("Delete",new OnClickListener(){
		  					@Override
		  					public void onClick(DialogInterface dialog, int which) {
		  						Claim claim = list.get(finalPosition);
		  						ClaimListController.getClaimList().deleteClaim(claim);
		  					}
		  				});
		  				adb.setNegativeButton("Cancel",new OnClickListener(){

		  					@Override
		  					public void onClick(DialogInterface dialog, int which) {						
		  					}
		  					
		  				});
		  				adb.show();
		              }
		              
		              //START of ADD EXPENSE check
		              if (item.getTitle().equals("Add Expense")){
		            	  Intent add_exp = new Intent(AddClaim.this, ExpenseAdd.class);
		            	  add_exp.putExtra("somename", finalPosition);
		            	  startActivity(add_exp);
		            	  
		 		              }
		              //what do do if Details button is clicked
		              if (item.getTitle().equals("Get Claim Details")){
		            	  Intent get_detail = new Intent(AddClaim.this, GetDetails.class);
		            	  get_detail.putExtra("claim_position", finalPosition);
		            	  startActivity(get_detail);
		              }
		              
		              //Edit the claim we want.
		              if (item.getTitle().equals("Edit Claim")){
		            	  if (list.get(finalPosition).getStatus() != "submitted"){
			            	  Intent edit = new Intent(AddClaim.this, EditClaim.class);
			            	  edit.putExtra("pos", finalPosition);
			            	  startActivity(edit);
		            		  claimAdapter.notifyDataSetChanged();
		            	  	}else{
		            	  		Toast.makeText(AddClaim.this, "You cannot edit a submitted claim", Toast.LENGTH_SHORT).show();
		            	  	}
		            	  
		            	  }
		              if (item.getTitle().equals("Change Claim Status")){
		            	  if (list.get(finalPosition).getStatus() == "submitted"){
		            		  Toast.makeText(AddClaim.this, "You cannot edit a submitted claim", Toast.LENGTH_SHORT).show();
		            	  }else{
		            		  Intent changeStatus = new Intent(AddClaim.this, ChangeStatus.class);
		            		  changeStatus.putExtra("popopo", finalPosition);
		            		  startActivity(changeStatus);
		            	  }
		              }
		            	  
		              return true;  
		             }  
		            });  
				popup.show();
			return true;
			}
			
		});
		
	}
	
	/*@Override
	
	protected void onStart(){
		super.onStart();
		this.claim = loadFromFile();
		ClaimsList cl = new ClaimsList();
		cl.setClaimList(this.claim);
		ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, this.claim);
		listView.setAdapter(claimAdapter);
	}*/
	/*
	 * View methods below. required to run the code.
	 * 
	 */
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
		return true;
	}

	protected void onDeleteClick(final int position, final ArrayList<Claim> list){
		AlertDialog.Builder adb = new AlertDialog.Builder(AddClaim.this);
		adb.setMessage("Delete "+ list.get(position).toString()+"?");
		adb.setCancelable(true);
		
		adb.setPositiveButton("Delete",new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Claim claim = list.get(position);
				ClaimListController.getClaimList().deleteClaim(claim);
			}
			
		});
		adb.setNegativeButton("Cancel",new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {						
			}
			
		});
		adb.show();
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
		setContentView(R.layout.claim_add_page);
	}
	
	public void addClaims(View v){
		ClaimListController ct = new ClaimListController();	
		Claim addClaim = new Claim(name, sdate, edate);
		ct.addClaim(addClaim);
	//saveInFile(addClaim); // Get Persistance to work for this
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
			Toast.makeText(AddClaim.this,"Please enter a Name before continuing", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getSDate(View v){
		DatePicker sdatePicker = (DatePicker)findViewById(R.id.sdate_picker);
		String Sdate = "not_empty";
		if (!TextUtils.isEmpty(Sdate)){
	        this.sdate = this.edate = getDateFromDatePicket(sdatePicker);;
	        setContentView(R.layout.claim_add_edate);
		}else{
			Toast.makeText(AddClaim.this,"Please enter a Start date before continuing", Toast.LENGTH_SHORT).show();
		}
        setContentView(R.layout.claim_add_edate);
	}
	
	public void getEDate(View v){
		DatePicker edatePicker = (DatePicker)findViewById(R.id.edate_picker);
		if (!getDateFromDatePicket(edatePicker).before(this.sdate)){
	        this.edate = getDateFromDatePicket(edatePicker);
	        addClaims(v);
	        this.finish();
		}else{
			Toast.makeText(AddClaim.this,"End Date cannot come before start date. Please select another date", Toast.LENGTH_SHORT).show();
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
	
	public void editComplete(View v){
		
	}
	
	//THIS IS THE PERSISTANCE AS DONE IN THE LAB> NOT SURE WHY IT IS NOT WORKING> I WILL LOOK AT IT LATER> 
	
   //Create persistent data file for arrays of expenses and lists
/*	private void saveInFile(Claim claim) {
		Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(SAVEFILE,
					0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(claim, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//recover those persistent data created in the save function
	private ArrayList<Claim> loadFromFile() {
		Gson gson = new Gson();
		ArrayList<Claim> claim = new ArrayList<Claim>();
		try {
			FileInputStream fis = openFileInput(SAVEFILE);
			//Based on http://google.gson.googlecode.com/svn/trunk/gson/dos/javadoc/com/google/gson/Gson.html
			Type listType = new TypeToken<ArrayList<Claim>>(){}.getType();
			InputStreamReader isr = new InputStreamReader(fis);
			claim = gson.fromJson(isr, listType);
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (claim == null){
			claim = new ArrayList<Claim>();
		}
		return claim;
	}*/
}
