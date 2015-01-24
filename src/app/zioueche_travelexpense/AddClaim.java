package app.zioueche_travelexpense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
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
	String name;
	String sdate;
	String edate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim);

		//adapter for claim view
		
		ListView listView = (ListView) findViewById(R.id.claimListView);
		Collection<Claim> claims = ClaimListController.getClaimList().getClaim();
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
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

		//Add observer pattern
	//	ExpenseListController.getExpenseList().addListener(new Listener(){
	//		@Override
	//		public void update(){
	//			elist.clear();
	//			Collection<Expense> expenses = ExpenseListController.getExpenseList().getClaim();
	//			elist.addAll(expenses);
	//			expAdapter.notifyDataSetChanged();
	//		}
	//	});
		//SINGLE TAP FUNCTION
		
		//SINGLE TAP FUNCTION maybe make this into an activity 
		//for more functionality to be able to add expenses from the list view
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(AddClaim.this, "Clicked "+list.get(position), Toast.LENGTH_SHORT).show();
				//adapter expenses
				setContentView(R.layout.add_expense);
				ListView expView = (ListView) findViewById(R.id.ExpenseListView);
				Collection<Expense> expenses = list.get(position).getExpenses();
				final ArrayList<Expense> expense = new ArrayList<Expense>(expenses);
				final ArrayAdapter<Expense> expAdap = new ArrayAdapter<Expense>(AddClaim.this, android.R.layout.simple_list_item_1, expense);
				expView.setAdapter(expAdap);
				}

		});
		
		//LONG CLICK FUNCTIONS
		listView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final int finalPosition = position;
				PopupMenu popup = new PopupMenu(AddClaim.this, view);
				popup.getMenuInflater().inflate(R.menu.add_claim, popup.getMenu());
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
		              }//end of delete button check
		              //START of ADD EXPENSE check
		              if (item.getTitle().equals("Add Expense")){
		            	  Intent intent = new Intent(AddClaim.this, ExpenseAdd.class);
		            	  intent.putExtra("somename", finalPosition);
		            	  startActivity(intent);
		            	  
		 		              }
		              //end of add expense check
		              return true;  
		             }  
		            });  
				popup.show();
			return false;
			}
			
		});
		
	}
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
			
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
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
		ct.addClaim(new Claim(name, sdate, edate));
		Toast.makeText(this,"Added "+name, Toast.LENGTH_SHORT).show();
		setContentView(R.layout.activity_main);
			//Intent intent = new Intent(MainActivity.this, AddClaim.class);
			//startActivity(intent);
		
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
		Integer year = sdatePicker.getYear();
        Integer month = sdatePicker.getMonth();
        Integer day = sdatePicker.getDayOfMonth();
        StringBuilder sb=new StringBuilder();
        sb.append(year.toString()).append("-").append(month.toString()).append("-").append(day.toString()).append(" 00:00:00");
        String sdate=sb.toString();
		
		if (!TextUtils.isEmpty(sdate)){
	        this.sdate = sdate;
	        setContentView(R.layout.claim_add_edate);
		}else{
			Toast.makeText(AddClaim.this,"Please enter a Start date before continuing", Toast.LENGTH_SHORT).show();
		}
        setContentView(R.layout.claim_add_edate);
	}
	
	public void getEDate(View v){
		DatePicker edatePicker = (DatePicker)findViewById(R.id.edate_picker);
        Integer year = edatePicker.getYear();
        Integer month = edatePicker.getMonth();
        Integer day = edatePicker.getDayOfMonth();
        StringBuilder sb=new StringBuilder();
        sb.append(year.toString()).append("-").append(month.toString()).append("-").append(day.toString()).append(" 00:00:00");
        String edate=sb.toString();
		if (!TextUtils.isEmpty(edate)){
			
	        this.edate = edate;
	        addClaims(v);
	        setContentView(R.layout.activity_main);
	        
	        setContentView(R.layout.claim_add_edate);
		}else{
			Toast.makeText(AddClaim.this,"Please enter a Start date before continuing", Toast.LENGTH_SHORT).show();
		}	}
	
	
	
	//just a format string maker.
	private String format(String string, String added) {
		String formats = string +" "+ added; 
		return formats;
	}
}
