package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MenuView {

	View view;
	DinnerModel model;
	
	public MenuView(View view, DinnerModel model) {
		this.view = view;
		this.model = model;



		//ImageButton instrbutton1 = (ImageButton) view.findViewById(R.id.instrButton1);
		
		//Wanted to put spinner here, but it wouldn't work for some undefined reason.
		
	
		Spinner guestsSpinner = (Spinner) view.findViewById(R.id.spinner_guests);
		Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10};
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(),android.R.layout.simple_spinner_item, items);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		guestsSpinner.setAdapter(adapter);


		
		//Total cost for all selected dishes. Needs to be changed after each selection.
		
		TextView totalCost = (TextView) view.findViewById(R.id.text_total_cost);
		totalCost.setText("Total cost: " + model.getTotalMenuPrice() +"kr" );
		
		//Retrieving all dishes in the model
		
		Set<Dish> starters = model.getDishesOfType(1);
		Set<Dish> mains = model.getDishesOfType(2);
		Set<Dish> desserts = model.getDishesOfType(3);
		
		
		//The three for-loops below should create new elements (one vertical view + imagebutton and textview for each dish),
		//but it seems as though setting layout parameters is frowned upon..
		
		//Setting dish name and dish picture for all the starters in the model
		
		for (Dish d:starters) {
			TextView text = (TextView) view.findViewById(R.id.text_starter1);
			text.setText(d.getName());
			ImageButton button = (ImageButton) view.findViewById(R.id.ImageButton_starter1);
			//button.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", "se.kth.csc.iprog.dinnerplanner"));
			//ImageButton button = (ImageButton) this.findViewById(R.id.ImageButton_starter1);
			button.setImageResource(R.drawable.toast);

		}
		
		//Setting dish name and dish picture for all the main courses in the model
		
		for (Dish d:mains) {
			TextView text = (TextView) view.findViewById(R.id.text_main1);
			text.setText(d.getName());
			ImageButton button = (ImageButton) view.findViewById(R.id.ImageButton_main1);
			button.setImageResource(R.drawable.meatballs);

		}
		
		//Setting dish name and dish picture for all the desserts in the model
		
		for (Dish d:desserts) {
			TextView text = (TextView) view.findViewById(R.id.text_dessert1);
			text.setText(d.getName());
			ImageButton button = (ImageButton) view.findViewById(R.id.ImageButton_dessert1);
			button.setImageResource(R.drawable.icecream);

		}
		

	}

}
