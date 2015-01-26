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

public class ClaimAddSDate extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_add_sdate);
	}
	DatePicker sdatePicker = (DatePicker)findViewById(R.id.sdate_picker);
	String Sdate = "not_empty";
	public void getSDate(View v){
		String name = getIntent().getStringExtra("name");
	if (!TextUtils.isEmpty(Sdate)){
       int day = sdatePicker.getDayOfMonth();
       int month = sdatePicker.getMonth();
       int year = sdatePicker.getYear();
       Intent intent = new Intent(ClaimAddSDate.this, ClaimAddEDate.class);
       intent.putExtra("day", day);
       intent.putExtra("month", month);
       intent.putExtra("year", year);
       intent.putExtra("name", name);
       startActivity(intent);
       
	}else{
		Toast.makeText(ClaimAddSDate.this,"Please enter a Start date before continuing", Toast.LENGTH_SHORT).show();
	}
}
}

