package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.android.view.SummaryButtonListener;
import se.kth.csc.iprog.dinnerplanner.android.view.SummaryView;
import se.kth.csc.iprog.dinnerplanner.android.view.TopView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SummaryActivity extends Activity {
	TopView top;
	SummaryView summaryView;
	DinnerModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_summary);
    	// Creating the view class instance
		model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		
    	top = new TopView(findViewById(R.id.top_view_id), model, this);
    	top.hideSpinner();
		summaryView = new SummaryView(this,findViewById(R.id.summary_view_id), model);		
		new SummaryButtonListener(model,summaryView);
    	
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}
	

}
