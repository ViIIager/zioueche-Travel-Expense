package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class ExpenseAdd extends Activity{
	private String name;
	private Date date;
	private float price;
	private static String currency;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_adder);
		
	}
	
	public void addExpense(View v){
		int finalPosition = getIntent().getIntExtra("somename",0);
		Collection<Claim> claims = ClaimListController.getClaimList().getClaim();
		ArrayList<Claim> list = new ArrayList<Claim>(claims);
		ArrayList<Expense>elist = list.get(finalPosition).getExpenses();
		elist.add(new Expense(name, date, currency,price));
		Toast.makeText(ExpenseAdd.this, currency, Toast.LENGTH_SHORT).show();
		Intent back = new Intent(this, MainActivity.class);
		startActivity(back);
		//Toast.makeText(this, name+date+price+currency , Toast.LENGTH_LONG).show();
	}
	
	public void getEName(View v){
		EditText expname = (EditText) findViewById(R.id.name_field);
		String name = expname.getText().toString();
		if (!TextUtils.isEmpty(name)){
		this.name = name;
		expname.setText("");
		setContentView(R.layout.expense_date_add);
		}else{
			Toast.makeText(ExpenseAdd.this, "Please enter a name before continuing", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getEDate(View v){
		DatePicker expPicker = (DatePicker)findViewById(R.id.exp_picker);
		String Sdate = "not_empty";
		if (!TextUtils.isEmpty(Sdate)){
	        this.date = getDateFromDatePicket(expPicker);;
	        setContentView(R.layout.expense_get_currency);
		}else{
			Toast.makeText(ExpenseAdd.this,"Please enter a Start date before continuing", Toast.LENGTH_SHORT).show();
		}
	}
	public void getECurrency(View v){
		RadioGroup rd= (RadioGroup)findViewById(R.id.radioGroup1);
		
		if(rd.getCheckedRadioButtonId()!=-1){
		    int id= rd.getCheckedRadioButtonId();
		    View radioButton = rd.findViewById(id);
		    int radioId = rd.indexOfChild(radioButton);
		    RadioButton btn = (RadioButton) rd.getChildAt(radioId);
		    String selection = (String) btn.getText();
		    
		    ExpenseAdd.currency = selection;
		    Toast.makeText(ExpenseAdd.this, currency, Toast.LENGTH_SHORT).show();
		}
		
        addExpense(v);
        
	}
	
	public void getEPrice(View v){
		EditText expname = (EditText) findViewById(R.id.price_field);
		float price = Float.parseFloat( expname.getText().toString() );
		if (!TextUtils.isEmpty(expname.getText().toString())){
			this.price = price;
		getECurrency(v);
		}else{
			Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public static Date getDateFromDatePicket(DatePicker datePicker){
	    int day = datePicker.getDayOfMonth();
	    int month = datePicker.getMonth();
	    int year =  datePicker.getYear();
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(year, month, day);
	    return calendar.getTime();
	}
}
	