package app.zioueche_travelexpense;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExpenseAdd extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_adder);
		
		}
	
	public void addExpense(View v){
		Intent mIntent = getIntent();
	 	int intValue = mIntent.getIntExtra("intVariableName", 0);
	 	
		EditText expname = (EditText) findViewById(R.id.exp_name);
		String expense = expname.toString();
		ClaimListController ct = new ClaimListController();
		ArrayList<Claim> list = new ArrayList<Claim>();
		ArrayList<Expense>elist = list.get(intValue).getExpenses();
		elist.add(new Expense(expense));
	}
}
