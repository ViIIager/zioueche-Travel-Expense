package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
//figure out how to add claim in different page.  so we can add date range.
public class AddClaim extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim);
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
		            	  Toast.makeText(AddClaim.this,"added expense",Toast.LENGTH_SHORT).show();
		            	  
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
		Toast.makeText(this, "changing view", Toast.LENGTH_SHORT).show();
		setContentView(R.layout.claim_add_page);
	}
	
	public void addClaims(View v){
		ClaimListController ct = new ClaimListController();
		EditText textView = (EditText) findViewById(R.id.add_claim_field);
		String added = textView.getText().toString();
		
		if (!TextUtils.isEmpty(added)){
			final String t = format("added",added);
			ct.addClaim(new Claim(added));
			Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
			textView.setText("");
			//Intent intent = new Intent(MainActivity.this, AddClaim.class);
			//startActivity(intent);
		}else{
			Toast.makeText(AddClaim.this,"Please type something before adding", Toast.LENGTH_SHORT).show();
		}
	}
	private String format(String string, String added) {
		String formats = string +" "+ added; 
		return formats;
	}
}
