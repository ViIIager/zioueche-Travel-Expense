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
	private int price;
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
		Toast.makeText(this, name+date+price+currency , Toast.LENGTH_LONG).show();
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
		/*Integer year = sdatePicker.getYear();
        Integer month = sdatePicker.getMonth();
        Integer day = sdatePicker.getDayOfMonth();
        StringBuilder sb=new StringBuilder();
        sb.append(year.toString()).append("-").append(month.toString()).append("-").append(day.toString()).append(" 00:00:00");
        String sdate=sb.toString();*/
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
        rd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb=(RadioButton)findViewById(checkedId);
                Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
                ExpenseAdd.currency = (String) rb.getText();
            }
        });
        addExpense(v);
        
	}
	
	public void getEPrice(View v){
		EditText expname = (EditText) findViewById(R.id.name_field);
		int price = Integer.parseInt( expname.getText().toString() );
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
	