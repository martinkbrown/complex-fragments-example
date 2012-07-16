package edu.fsu.cs.mobile.homework.hw3;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MessageFragment extends Fragment {

	String[] mMessages = new String[]{"Where are you?", 
										"Happy Mother's Day!", 
										"Volleyball 2pm Saturday", 
										"Brrrrrt", 
										"Hahahaha", 
										"I inteded to go but I had an emergency. I'll tell you about it when I get back", 
										"YES!"};
	
	ArrayList<String> mArrayMsg = new ArrayList<String>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
		
		for(int i = 0; i < mMessages.length; i++) {
			mArrayMsg.add(mMessages[i]);
		}
		
		TextView tv = new TextView(getActivity());
		
		Bundle args = getArguments();
		
		int selected = 0;
		String msg = null;
		
		if(args != null) {
			
			selected = args.getInt("selected");
			msg = args.getString("message");
			
			if(msg != null) {
				mArrayMsg.add(msg);
				selected = mArrayMsg.size() - 1;
			}
			
		}
		
		tv.setText(mArrayMsg.get(selected));
		
		return tv;
		
	}
}
