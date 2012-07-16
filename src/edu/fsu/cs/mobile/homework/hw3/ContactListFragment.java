package edu.fsu.cs.mobile.homework.hw3;

import java.util.ArrayList;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class ContactListFragment extends ListFragment {

	int selected = 0;
	
	ArrayList<String> mContacts = new ArrayList<String>();
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
		for(int i = 0; i < 7; i++)
		{
			mContacts.add("" + i + i + i);
		}
		
		Bundle extras = getArguments();
		
		if(extras != null) {
			mContacts.add(extras.getString("contact"));
		}
		
		setListAdapter(new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, 
				mContacts));

		getListView().setOnItemClickListener( new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long rowId) {
				
				selected = position;
				
				Configuration config = getActivity().getResources().getConfiguration();
				
				if(config.orientation == Configuration.ORIENTATION_PORTRAIT) {
					Intent myIntent = new Intent(getActivity(), MessageActivity.class);
					myIntent.putExtra("selected", position);
					startActivity(myIntent);
				}
				else if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
					
					MessageFragment messages = new MessageFragment();
					Bundle bundle = getActivity().getIntent().getExtras();
					
					if(bundle == null) {
						bundle = new Bundle();
					}
					
					bundle.putInt("selected", position);
					
				    messages.setArguments(bundle);
				    
				    FragmentTransaction mTrans = getActivity().getFragmentManager().beginTransaction();
				    mTrans.replace(R.id.messages_container, messages, MainActivity.MESSAGES_FRAGMENT_TAG);
				    mTrans.commit();
				}
			}
		});
	}
}
