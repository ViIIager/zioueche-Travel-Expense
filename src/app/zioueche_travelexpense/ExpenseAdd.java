package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ExpenseAdd extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_adder);
		String[] curr = new String[] {"Select a Currency","USD", "CAD", "GBP", "AUD","EUR"};
		final Spinner curr_spin = (Spinner) findViewById(R.id.currspin);
		ArrayAdapter<String> curr_adapter = new ArrayAdapter<String>(ExpenseAdd.this,
                android.R.layout.simple_spinner_item, curr);
		curr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        curr_spin.setAdapter(curr_adapter);
		//this is the position of the claim.
		int finalPosition = getIntent().getIntExtra("somename",0);
		Toast.makeText(this, ""+finalPosition+" "+"is my position", Toast.LENGTH_LONG).show();
		}

	public void addExpense(View v){
		int finalPosition = getIntent().getIntExtra("somename",0);
		EditText expname = (EditText) findViewById(R.id.name_field);
		String added = expname.getText().toString();
		Collection<Claim> claims = ClaimListController.getClaimList().getClaim();
		ArrayList<Claim> list = new ArrayList<Claim>(claims);
		ArrayList<Expense>elist = list.get(finalPosition).getExpenses();
		elist.add(new Expense(added));
		Toast.makeText(this, finalPosition+" "+"is my position"+" "+list.get(finalPosition).toString()+" "+ elist.size(), Toast.LENGTH_LONG).show();
	}
}