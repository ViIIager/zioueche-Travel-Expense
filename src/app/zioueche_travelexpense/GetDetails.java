package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetDetails extends Activity {
	static int usd;
	static int cad;
	static int aud;
	static int gbp;
	static int eur;
	
	protected void onCreate(Bundle savedInstanceState) {
		this.usd = 0;
		this.cad = 0;
		this.aud = 0;
		this.gbp = 0;
		this.eur = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_view);
		int finalPosition = getIntent().getIntExtra("claim_position",0);
		calculatePrices();
	}
	
	public ArrayList<Expense> getExpenses(){
		int finalPosition = getIntent().getIntExtra("claim_position",0);
		Toast.makeText(this, finalPosition+"",Toast.LENGTH_SHORT).show();
		ClaimListController ct = new ClaimListController();
		Collection<Claim> cl = ClaimListController.getClaimList().getClaim();
		ArrayList<Claim> clist = new ArrayList<Claim>(cl);
		Claim claim = clist.get(finalPosition);
		ArrayList<Expense> elist = claim.getExpenses();
		return elist;
	}
	
	public void calculatePrices(){
		ArrayList<Expense> list = getExpenses();
		for (int i = 0; i < list.size(); i++){
			Expense e = list.get(i);
			if (e.getCurrency().equals("USD") ){
				GetDetails.usd += e.getPrice(); 
			}
			if (e.getCurrency().equals("CAD")){
				GetDetails.cad += e.getPrice();
			}
			if (e.getCurrency().equals("AUD")){
				GetDetails.aud += e.getPrice();
			}
			if (e.getCurrency().equals("EUR")){
				GetDetails.eur += e.getPrice();
			}
			if (e.getCurrency().equals("GBP")){
				GetDetails.gbp += e.getPrice();
			}
		}
		
		TextView usds = (TextView) findViewById(R.id.tot_usd);
		usds.setText("" + this.usd);
		TextView cads = (TextView) findViewById(R.id.tot_cad);
		cads.setText("" + this.cad);
		TextView auds = (TextView) findViewById(R.id.tot_aud);
		auds.setText("" + this.aud);
		TextView eurs = (TextView) findViewById(R.id.tot_eur);
		eurs.setText("" + this.eur);
		TextView gbps = (TextView) findViewById(R.id.tot_gbp);
		gbps.setText("" + this.gbp);
		
	}
}
