package app.zioueche_travelexpense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailView extends Activity {
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_detail_view);
		int finalPosition = getIntent().getIntExtra("pos",0);
		int expPosition = getIntent().getIntExtra("epos", 0);
		 Collection col = ClaimListController.getClaimList().getClaim();
		 ArrayList<Claim> claim = new ArrayList<Claim>(col);
		 ArrayList<Expense> expense =  claim.get(finalPosition).getExpenses();
	  	  Expense workwith = expense.get(expPosition);
	  	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	  	  Date date = workwith.getCDate();
	  	  String to_use = sdf.format(date );
	  	  TextView dateview = (TextView) findViewById(R.id.expdate);
	  	  TextView currview = (TextView) findViewById(R.id.currency);
	  	  TextView priceview = (TextView) findViewById(R.id.price_cost);
	  	  dateview.setText(to_use);
	  	  int price = workwith.getPrice();
	  	  priceview.setText(price+"");
	  	  String curr = workwith.getCurrency();
	  	  currview.setText(curr);
	}
}
