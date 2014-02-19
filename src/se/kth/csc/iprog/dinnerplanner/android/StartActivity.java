package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.android.view.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		// Creating the view class instance
		new StartView(findViewById(R.id.start_view_id));

		// Create and add listner to button start Menu
		Button next = (Button) findViewById(R.id.button);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {				
				Intent intent = new Intent(view.getContext(), MenuActivity.class).putExtra("from", "StartActivity");
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
