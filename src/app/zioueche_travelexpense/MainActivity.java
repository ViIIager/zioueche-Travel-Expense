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
	public void goToAdd(View v){
		Intent intent = new Intent(MainActivity.this, AddClaim.class);
		startActivity(intent);
	}
}
	
