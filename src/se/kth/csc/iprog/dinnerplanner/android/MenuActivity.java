package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.R.layout;
import se.kth.csc.iprog.dinnerplanner.R.menu;
import se.kth.csc.iprog.dinnerplanner.android.view.MenuView;
import se.kth.csc.iprog.dinnerplanner.android.view.TopView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MenuActivity extends Activity {

	TopView top;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		DinnerModel model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		top = new TopView(findViewById(R.id.top_view_id), model);
		top.hideBackArrow();
		MenuView mainView = new MenuView(findViewById(R.id.menu_view_id), model);

		
		
	    /*LinearLayout layout = new LinearLayout(this);
	    LinearLayout.LayoutParams layoutP = new LinearLayout.LayoutParams(WRAP_CONTENT, 65);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(layoutP);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
