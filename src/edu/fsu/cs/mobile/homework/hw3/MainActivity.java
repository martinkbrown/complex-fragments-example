package edu.fsu.cs.mobile.homework.hw3;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends Activity {
    
	FragmentTransaction mTrans;
	public final static String CONTACTS_FRAGMENT_TAG = "contactsFragment";
	public final static String MESSAGES_FRAGMENT_TAG = "messagesFragment";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
    	
    	setupFragments(getResources().getConfiguration());
    }
    
    public void onConfigurationChanged(Configuration newConfig) {
    	
    	setupFragments(newConfig);
    }
    
    private void setupFragments(Configuration config) {
    	
    	ContactListFragment contactsFragment = (ContactListFragment) getFragmentManager().findFragmentByTag(
    			CONTACTS_FRAGMENT_TAG);
    	
    	if(contactsFragment == null)
    	{
    		contactsFragment = new ContactListFragment();
    		contactsFragment.setArguments(getIntent().getExtras());
    	}
    	
    	mTrans = getFragmentManager().beginTransaction();
    	
    	if(config.orientation == Configuration.ORIENTATION_PORTRAIT) {
    		
    		setContentView(R.layout.main);
    		mTrans.replace(R.id.contacts_container, contactsFragment);
    	}
    	
    	else if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
    	
    		setContentView(R.layout.dual);
    		mTrans.replace(R.id.contacts_container, contactsFragment);
    		
    		MessageFragment messagesFragment = new MessageFragment();
    		Bundle args = getIntent().getExtras();
    		
    		if(args == null) {
    			args = new Bundle();
    		}
    		
    		args.putInt("selected", contactsFragment.selected);
    		
    		mTrans.add(R.id.messages_container, messagesFragment, MESSAGES_FRAGMENT_TAG);    		
    	}
    	
    	mTrans.commit();
    }
}