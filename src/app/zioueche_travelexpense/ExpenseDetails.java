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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ExpenseDetails extends ListActivity {
	static int position;
	ListView expView;
	protected void onCreate(Bundle SavedInstanceState){
		
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.add_expense);
		ClaimListController ct = new ClaimListController();
		expView = (ListView) findViewById(android.R.id.list);
		
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
		/*final ArrayAdapter<Expense> expAdap = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list.get(finalPosition).getExpenses());
	    expView.setAdapter(expAdap);*/
		//final ArrayAdapter<Expense> expAdap = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list.get(finalPosition).getExpenses());
		final CustomAdapterExpense expAdap = new CustomAdapterExpense(this, R.layout.custom_view_expense, expense);
	    expView.setAdapter(expAdap);
		expAdap.notifyDataSetChanged();
		//expAdap.notifyDataSetChanged();
		
		expView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int finalPosition = ExpenseDetails.position;
				Toast.makeText(ExpenseDetails.this, "Clicked "+list.get(finalPosition), Toast.LENGTH_SHORT).show();
				Intent det = new Intent(ExpenseDetails.this, DetailView.class);
          	  	det.putExtra("pos", finalPosition);
          	  	det.putExtra("epos", position);
          	  	startActivity(det);
			}
		});
		
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
		            	  Intent intent = new Intent(ExpenseDetails.this, DeleteExpense.class);
		            	  intent.putExtra("exPos", expPosition);
		            	  intent.putExtra("cPos", finalPosition);
		            	  startActivity(intent);
		            	  finish();
		              }
		              
		              /*//Get the expense Details.  not sure if I need this.
		              if (item.getTitle().equals("Get Expense Details")){
		            	  Intent det = new Intent(ExpenseDetails.this, DetailView.class);
		            	  det.putExtra("pos", finalPosition);
		            	  det.putExtra("epos", expPosition);
		            	  startActivity(det);

		            	  }*/
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
