package app.zioueche_travelexpense;
/* TO-DO list:
 * 
 * -
 * - adding expenses to the claim WORK ON THIS NEXT> THIS IS CRUCIAL
 * - finish the expense add page THIS GOES WITH 2
 * - returned/approved/accepted are statuses(check-boxes) that affect the class Claim's STATUS AFTER CLAIM IS EDITED
 * - add date to claim AFTER ALL ABOVE
 * - figure out how to link claims and expenses. MAIN PART OF CRUCIAL
 * - Look at claim CLass to see what I need to add to the constructor MAYBE AT END?  EDIT from later?  easier than doing everything else.
 * - 
 * -
 * -
 * -
 * -
 * -*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	
	public void editClaims(MenuItem item){
		Toast.makeText(this, "edit students pressed", Toast.LENGTH_SHORT).show();
	}
	
	public void goToAdd(View v){
		Intent intent = new Intent(MainActivity.this, AddClaim.class);
		startActivity(intent);
	}
		
	public void CheckList(View v){
		//int size = ClaimsList.claimList.size();
		//String Added = String.format("there are %d claims in this list", size);
		if (ClaimsList.isEmpty()){
			Toast.makeText(this, "empty List!!!", Toast.LENGTH_SHORT).show();
		}
		else{Toast.makeText(MainActivity.this,"not empty" , Toast.LENGTH_SHORT).show();
		}
	}
}
	
