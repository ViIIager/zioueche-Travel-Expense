package app.zioueche_travelexpense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ExpenseDetails extends Activity {
	static int position;
	ListView expView;
	protected void onCreate(Bundle SavedInstanceState){
		
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.add_expense);
		ClaimListController ct = new ClaimListController();
		expView = (ListView) findViewById(R.id.ExpenseListView);
		
		//ExpenseList Adaptor for the Listview.
		Collection<Claim> coll = ClaimListController.getClaimList().getClaim();
		final ArrayList<Claim> list = new ArrayList<Claim>(coll);
		int finalPosition = getIntent().getIntExtra("claimpos",0);
		this.position = finalPosition;
		//ListView expView = (ListView) findViewById(R.id.ExpenseListView);
		Collection<Expense> expenses = list.get(finalPosition).getExpenses();
		final ArrayList<Expense> expense = new ArrayList<Expense>(expenses);
		if (expense.size() > 1){
			Collections.sort(expense, new CustomComparatorExpense());
		}
		final ArrayAdapter<Expense> expAdap = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list.get(finalPosition).getExpenses());
	    expView.setAdapter(expAdap);
		expAdap.notifyDataSetChanged();
		
		
		expView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final int expPosition = position;
				final int finalPosition = ExpenseDetails.position;
				PopupMenu popup = new PopupMenu(ExpenseDetails.this, view);
				popup.getMenuInflater().inflate(R.menu.popup_expense, popup.getMenu());
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
		            public boolean onMenuItemClick(MenuItem item) {  
		              if (item.getTitle().equals("Delete Expense")){
		            	AlertDialog.Builder adb = new AlertDialog.Builder(ExpenseDetails.this);
		  				adb.setMessage("Delete "+ list.get(finalPosition).getExpenses().get(expPosition)+"?");
		  				adb.setCancelable(true);
		  				adb.setPositiveButton("Delete",new OnClickListener(){
		  					@Override
		  					public void onClick(DialogInterface dialog, int which) {
		  						ArrayList<Expense> expense = list.get(finalPosition).getExpenses();
		  						Toast.makeText(ExpenseDetails.this, expense.get(expPosition)+" Deleted", Toast.LENGTH_SHORT).show();
		  						expense.remove(expPosition);
		  						expAdap.clear();
		  						expAdap.addAll(expense);
		  						expAdap.notifyDataSetChanged();
		  					}
		  					
		  				});
		  				adb.setNegativeButton("Cancel",new OnClickListener(){

		  					@Override
		  					public void onClick(DialogInterface dialog, int which) {						
		  					}
		  					
		  				});
		  				adb.show();
		              }
		              
		            	  

		              //Edit the claim we want.
		              if (item.getTitle().equals("Get Expense Details")){
		            	  setContentView(R.layout.expense_detail_view);
		            	  Expense workwith = expense.get(expPosition);
		            	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		            	  Date date = workwith.getCDate();
		            	  String to_use = sdf.format(date );
		            	  TextView dateview = (TextView) findViewById(R.id.expdate);
		            	  TextView currview = (TextView) findViewById(R.id.currency);
		            	  TextView priceview = (TextView) findViewById(R.id.price_cost);
		            	  dateview.setText(to_use);
		            	  int price = workwith.getPrice();
		            	  priceview.setText(price+"");
		            	  String curr = workwith.getCurrency();
		            	  currview.setText(curr);
		            	  }
		            	  //Toast.makeText(ExpenseDetails.this, "Editing Claim: "+ list.get(finalPosition), Toast.LENGTH_SHORT).show();
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
		getMenuInflater().inflate(R.menu.on_expense_pop, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.add_exp) {
		//  final int finalPosition = getIntent().getIntExtra("claimpos",0);
      	  Intent from_within = new Intent(ExpenseDetails.this, ExpenseAdd.class);
      	  from_within.putExtra("somename", this.position);
      	  startActivity(from_within);
      	  finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
//	public void editClaims(MenuItem item){
//		Toast.makeText(this, "edit students pressed", Toast.LENGTH_SHORT).show();
//	}
}
