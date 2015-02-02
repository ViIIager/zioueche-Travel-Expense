package app.zioueche_travelexpense;

/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/

/* 
 * 
 *This Class is for keepting all the Claims togeher, as well as making any modifications to the claims or expenses.  I tried
 *implimenting the data persistance here. 
 * 
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.widget.Toast;

public class ClaimsList implements Serializable{
	String filePath ="fileName.txt";
	Context ctx;
	
	/**
	 * Claim List Serialization ID
	 */
	private static final long serialVersionUID = 372301924739907840L;
	private static final String SHARED_PREFS_FILE = null;
	
	protected static ArrayList<Claim> claimList;
	protected ArrayList<Listener> listeners;
	
	public ClaimsList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	
	public Collection<Claim> getClaim(){
		return claimList;
	}
	
	public String getFile(){
		return filePath;
	}
	public void addClaim(Claim claim){
		//List<Claim> claimsList= new ArrayList<Claim>();
		if (claimList == null){
			claimList = new ArrayList<Claim>();
		}
		claimList.add(claim);
		//saveInFile(claimList);
		notifyListeners();
    }
	
	public void setClaimList(ArrayList<Claim> clist){
		if (clist == null){
			claimList = new ArrayList<Claim>();
		}else{
		this.claimList = clist;
		}
		
	}
	 
	public void addClaimAt(int position, Claim string){
		claimList.add(position, string);
		notifyListeners();
		
	}

	public void deleteClaim(Claim removeclaim){
		claimList.remove(removeclaim);
		notifyListeners();
	}
	
	public static boolean isEmpty(){
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
	
/*	public void saveInFile(ArrayList<Claim> claim) {
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(filePath));
			os.writeObject(claimList);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deSerealize(){
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(filePath));
			ArrayList<Claim> claim = (ArrayList<Claim>) is.readObject();
			is.close();
			if (claim == null){
				claim = new ArrayList<Claim>();
			}
			//Toast.makeText(ClaimsList.this, claim.size()+"", Toast.LENGTH_SHORT).show();
			claimList = claim;
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}*/
}
