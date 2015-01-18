package app.zioueche_travelexpense;

import java.util.ArrayList;
import java.util.Collection;

import android.text.InputFilter.LengthFilter;
import android.widget.Toast;

public class ClaimsList{
	protected ArrayList<Claim> claimList;
	protected ArrayList<Listener> listeners;
	
	public ClaimsList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	
	public Collection<Claim> getClaim(){
		return claimList;
	}
	
	public void addClaim(Claim string){
		claimList.add(string);
		notifyListeners();
	}
	
	public void deleteClaim(Claim removeclaim){
		assert(claimList.size() > 0);
		claimList.remove(removeclaim);
		notifyListeners();
	}
	
	public boolean isEmpty(){
		return claimList.size()== 0;
	}
	
	public void notifyListeners(){
		for (Listener listener: listeners){
			listener.update();
		}
	}
	public void addListener(Listener l){
		listeners.add(l);
	}
	
	public void removeListener(Listener l){
		listeners.remove(l);
		
	}
	
}
