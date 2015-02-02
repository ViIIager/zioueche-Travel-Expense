package app.zioueche_travelexpense;

/*Copyright [2015] [Omar Zioueche]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.widget.Toast;

/* I have added the code for persistence, but for some reason I keep getting a null pointer exception.  I cannot figure out what it is
 * but I a starting to think it is the file not being initialized.  all other features work though. see commented out code
 * for saveInFile and LoadFromFile in this class and the commented out ON CREATE method in the ADDCLAIM class.
 */
public class ClaimsList implements Serializable{
	Context ctx;
	
	//private static final String FILENAME = "file.sav";
	//File file = new File(ctx.getFilesDir(), FILENAME);
	//FileOutputStream outputStream;

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
	
	public void addClaim(Claim claim) throws FileNotFoundException{
		//List<Claim> claimsList= new ArrayList<Claim>();
		if (claimList == null){
			claimList = new ArrayList<Claim>();
		}
		claimList.add(claim);
		//Gson gson = new Gson();
		//gson.toJson(claim);
		//outputStream = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);

		//saveInFile();
		notifyListeners();
    }
	
/*	private void saveInFile() {
		Gson gson = new Gson();
		try {
		FileOutputStream fos = this.ctx.openFileOutput(FILENAME,
		0);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		gson.toJson(claimList, osw);
		osw.flush();
		fos.close();
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
	
	public ArrayList<Claim> loadFromFile() {
		Gson gson = new Gson();
		ArrayList<Claim> tweets = new ArrayList<Claim>();
		try {
		FileInputStream fis = ctx.openFileInput(FILENAME);
		//Based on http://google.gson.googlecode.com/svn/trunk/gson/dos/javadoc/com/google/gson/Gson.html
		Type listType = new TypeToken<ArrayList<Claim>>(){}.getType();
		InputStreamReader isr = new InputStreamReader(fis);
		tweets = gson.fromJson(isr, listType);
		fis.close();
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		if (tweets == null){
		tweets = new ArrayList<Claim>();
		}
		return tweets;
		}*/
	
	public void setClaimList(ArrayList<Claim> clist){
		if (clist == null){
			claimList = new ArrayList<Claim>();
		}else{
		claimList = clist;
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
}
