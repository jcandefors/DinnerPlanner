package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.android.view.SummaryView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Summary extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		
    	// Creating the view class instance
    	SummaryView startView = new SummaryView(findViewById(R.id.start_view_id));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}

}
