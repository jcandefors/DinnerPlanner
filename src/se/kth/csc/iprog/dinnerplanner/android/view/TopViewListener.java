package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.View.OnClickListener;

public class TopViewListener implements OnClickListener {

	TopView mainView;
	Activity currentActivity;
	DinnerModel model;

	public TopViewListener(TopView view, DinnerModel model,Activity activity){
		currentActivity = activity;
		this.model = model;
		mainView = view;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.backarrow_id : NavUtils.navigateUpFromSameTask(currentActivity);			
		break;	
		//case : ....
		}

	}
}
