package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.android.view.SummaryView;
import se.kth.csc.iprog.dinnerplanner.android.view.TopView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

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
    	top = new TopView(findViewById(R.id.top_view_id), model);
    	top.hideSpinner();
		summaryView = new SummaryView(findViewById(R.id.summary_view_id), model);
    	
    	
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}
	
	public void changeTab(View view){
		
		switch (view.getId()){
		case R.id.ingrButton1 :  summaryView.changeTab(SummaryView.INGREDIENTS);
		break;
		case R.id.instrButton1 : summaryView.changeTab(SummaryView.STARTER);
		break;
		case R.id.instrButton2 : summaryView.changeTab(SummaryView.MAIN);
		break;
		case R.id.instrButton3 : summaryView.changeTab(SummaryView.DESERT);
		break;
		}
		
	
	}

}
