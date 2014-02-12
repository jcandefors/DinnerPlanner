package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Observable;
import java.util.Observer;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class TopView implements Observer {

	View view;
	DinnerModel model;
	public final static int MENU=1, SUMMARY=2;
	public Spinner guestsSpinner;
	public ImageButton backbutton;
	public TextView personText;
	public TextView totalCost;

	public TopView(View view, DinnerModel model, Activity activity) {
		this.view = view;
		this.model = model;
		TopViewListener topListener = new TopViewListener(this, model, activity);

		personText = (TextView) view.findViewById(R.id.TextParticipants);
		backbutton = (ImageButton) view.findViewById(R.id.backarrow_id);
		backbutton.setOnClickListener(topListener);
		
		guestsSpinner = (Spinner) view.findViewById(R.id.spinner_guests);
		Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10};
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(),android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		guestsSpinner.setAdapter(adapter);

		//Total cost for all selected dishes. Needs to be changed after each selection.
		totalCost = (TextView) view.findViewById(R.id.totalcost_text);
		totalCost.setText("Total cost: " + model.getTotalMenuPrice() +"kr" );		
	}

	public void setTotalCost() {
		totalCost.setText("Total cost: " + model.getTotalMenuPrice() +"kr" );

	}

	public void hideSpinner() {
		guestsSpinner.setVisibility(View.GONE);
		personText.setVisibility(View.GONE);
		backbutton.setVisibility(View.VISIBLE);

	}

	public void hideBackArrow() {
		backbutton.setVisibility(View.GONE);
		personText.setVisibility(View.VISIBLE);
		guestsSpinner.setVisibility(View.VISIBLE);

	}

	@Override
	public void update(Observable observable, Object data) {
		observable.addObserver(this);
		// TODO Auto-generated method stub
		// Pass in notifyObservers method i.e
		
	}
}
