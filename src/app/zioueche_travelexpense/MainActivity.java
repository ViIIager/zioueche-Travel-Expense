package app.zioueche_travelexpense;
/* TO-DO list:
 * 
 * - Status update needs to be finished. infrastructure is ready. ChangeStatus.java and change_claim_status.xml
 * - figure out persistence
 * - Change number input in expense price to decimal number
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
	public void goToAdd(View v){
		Intent intent = new Intent(MainActivity.this, AddClaim.class);
		startActivity(intent);
	}
}
	
