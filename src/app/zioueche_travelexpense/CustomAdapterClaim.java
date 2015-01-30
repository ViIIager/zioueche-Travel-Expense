package app.zioueche_travelexpense;
import java.text.DateFormat;
import java.util.ArrayList;


/*DESCRIPTION
 * 
 * 
 * TAKEN AND MODIFIED FROM: https://devtut.wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/ ON 2015/JAN/28
 * 
 * 
 * This is a custom adapter for the claimList. it allows me to see more than just the name of the object i am listing.  I can list things such as date and status on 
 * it as well, which makes the claim more readable and clear. 
 * 
 * 
 * 
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterClaim extends ArrayAdapter<Claim> {

	// declaring our ArrayList of items
	private ArrayList<Claim> objects;

	/* here we must override the constructor for ArrayAdapter
	* the only variable we care about now is ArrayList<Item> objects,
	* because it is the list of objects we want to display.
	*/
	public CustomAdapterClaim(Context context, int textViewResourceId, ArrayList<Claim> objects) {
		super(context, textViewResourceId, objects);
		this.objects = objects;
	}

	/*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
	public View getView(int position, View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.custom_view_claim, null);
		}

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		Claim i = objects.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.
			TextView tt = (TextView) v.findViewById(R.id.toptext);
			TextView ttd = (TextView) v.findViewById(R.id.toptextdata);
			TextView mt = (TextView) v.findViewById(R.id.middletext);
			TextView mtd = (TextView) v.findViewById(R.id.middletextdata);
			TextView bt = (TextView) v.findViewById(R.id.bottomtext);
			TextView btd = (TextView) v.findViewById(R.id.desctext);

			// check to see if each individual textview is null.
			// if not, assign some text!
			if (tt != null){
				tt.setText("Name: ");
			}
			if (ttd != null){
				ttd.setText(i.getName());
			}
			if (mt != null){
				mt.setText("Start Date: ");
			}
			if (mtd != null){
				mtd.setText("" + DateFormat.getDateInstance().format(i.getSDate()));
			}
			if (bt != null){
				bt.setText("Status: ");
			}
			if (btd != null){
				btd.setText(""+i.getStatus());
			}
		}

		// the view must be returned to our activity
		return v;
	}
}
