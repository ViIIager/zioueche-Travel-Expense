package app.zioueche_travelexpense;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ClaimAddName extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_add_page);
	}
	DatePicker sdatePicker = (DatePicker)findViewById(R.id.sdate_picker);
	String Sdate = "not_empty";
	public void ClaimaddName(View v){
		EditText textView = (EditText) findViewById(R.id.add_claim_field);
		String added = textView.getText().toString();
		if (!TextUtils.isEmpty(added)){
			Intent intent = new Intent(ClaimAddName.this, ClaimAddSDate.class);
			intent.putExtra("name", added);
			startActivity(intent);
       
	}else{
		Toast.makeText(ClaimAddName.this,"Please enter a valid name", Toast.LENGTH_SHORT).show();
	}
}
}
