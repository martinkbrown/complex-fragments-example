package edu.fsu.cs.mobile.homework.hw3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MessageActivity extends Activity {

	FragmentManager mManager;
	FragmentTransaction mTrans;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.messages);
	    
	    MessageFragment messages = new MessageFragment();
	    messages.setArguments(getIntent().getExtras());
	    mTrans = getFragmentManager().beginTransaction();
	    mTrans.add(R.id.messages_container, messages, MainActivity.MESSAGES_FRAGMENT_TAG);
	    mTrans.commit();
	    
	}

}
