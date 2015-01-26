package app.zioueche_travelexpense;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class ClaimAddEDate extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_add_edate);
	}
	DatePicker edatePicker = (DatePicker)findViewById(R.id.edate_picker);
	String Sdate = "not_empty";
	public void getEDate(View v){
		int day = getIntent().getIntExtra("day", 0);
		int month = getIntent().getIntExtra("month", 0);
		int year = getIntent().getIntExtra("year", 0);
		String name = getIntent().getStringExtra("name");
	if (!TextUtils.isEmpty(Sdate)){
       int day2 = edatePicker.getDayOfMonth();
       int month2 = edatePicker.getMonth();
       int year2 = edatePicker.getYear();
       Intent intent = new Intent(ClaimAddEDate.this, ViewClaims.class);
       intent.putExtra("day", day);
       intent.putExtra("month", month);
       intent.putExtra("year", year);
       intent.putExtra("day", day2);
       intent.putExtra("month", month2);
       intent.putExtra("year", year2);
       intent.putExtra("name", name);
       startActivity(intent);
       
	}else{
		Toast.makeText(ClaimAddEDate.this,"Please enter a Start date before continuing", Toast.LENGTH_SHORT).show();
	}
}
}

