package app.zioueche_travelexpense;
/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/
import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

//simple class activity that allows me to delete expenses
public class DeleteExpense extends Activity {
 protected void onCreate(Bundle savedInstanceState){
	 super.onCreate(savedInstanceState);
	 final int finalPosition = getIntent().getIntExtra("cpos",0);
	 final int expPosition = getIntent().getIntExtra("exPos",0);
	 AlertDialog.Builder adb = new AlertDialog.Builder(DeleteExpense.this);
	 Collection<Claim> col = ClaimListController.getClaimList().getClaim();
	 final ArrayList<Claim> list = new ArrayList<Claim>(col);
		adb.setMessage("Delete "+ list.get(finalPosition).getExpenses().get(expPosition)+"?");
		adb.setCancelable(true);
		adb.setPositiveButton("Delete",new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ArrayList<Expense> expense = list.get(finalPosition).getExpenses();
				Toast.makeText(DeleteExpense.this, expense.get(expPosition)+" Deleted", Toast.LENGTH_SHORT).show();
				expense.remove(expPosition);
				Intent returns = new Intent(DeleteExpense.this, ExpenseDetails.class);
				startActivity(returns);
				finish();
			}
		});
			adb.setNegativeButton("Cancel",new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {						
				finish();
				}
				
			});
			adb.show();
      }
 }

