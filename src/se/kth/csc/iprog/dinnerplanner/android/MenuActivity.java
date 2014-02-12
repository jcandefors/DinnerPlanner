package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.android.view.MenuView;
import se.kth.csc.iprog.dinnerplanner.android.view.TopView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

	TopView top;
	DinnerModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		
		Context context = getBaseContext();
		new MenuView(findViewById(R.id.menu_view_id), model, context);		

		top = new TopView(findViewById(R.id.top_view_id), model,this);
		top.hideBackArrow();

		Button createButton = (Button) findViewById(R.id.button_create);
		createButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if(model.getFullMenu().isEmpty()){
					//do nothing					
				}else{					
					Intent myIntent = new Intent(view.getContext(), SummaryActivity.class);
					startActivityForResult(myIntent, 0);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
