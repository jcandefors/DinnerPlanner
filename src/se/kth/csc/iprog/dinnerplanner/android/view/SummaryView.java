package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SummaryView implements Observer {
	View view;
	DinnerModel model;
	Dish starter;
	Dish main;
	Dish desert;
	public static final int INGREDIENTS = 4, STARTER = 1, MAIN = 2, DESERT = 3;

	public SummaryView(View view, DinnerModel model) {

		this.model = model;
		starter = model.getSelectedDish(STARTER);
		main = model.getSelectedDish(MAIN);
		desert = model.getSelectedDish(DESERT);

		// store in the class the reference to the Android View
		this.view = view;

		// Get Starter ImageButton and TextView
		ImageButton instrbutton1 = (ImageButton) view.findViewById(R.id.instrButton1);		
		TextView instrtext1 = (TextView) view.findViewById(R.id.instrText1_id);		

		// Change TextView below to Starter text
		if(starter != null){		
			instrbutton1.setImageResource(view.getResources().getIdentifier(starter.getImage(), "drawable", "se.kth.csc.iprog.dinnerplanner"));
			instrtext1.setText(starter.getName());
		}else{
			instrbutton1.setImageResource(R.drawable.img_holder);
			instrbutton1.setClickable(false);
		}

		// Get Main ImageButton and TextView
		ImageButton instrbutton2 = (ImageButton) view.findViewById(R.id.instrButton2);
		TextView instrtext2 = (TextView) view.findViewById(R.id.instrText2_id);

		// Change TextView below to Main text
		if(main != null){
			instrbutton2.setImageResource(view.getResources().getIdentifier(main.getImage(), "drawable", "se.kth.csc.iprog.dinnerplanner"));
			instrtext2.setText(main.getName());
		}else{
			instrbutton2.setClickable(false);
			instrbutton2.setImageResource(R.drawable.img_holder);
		}

		// Get Desert ImageButton and TextView
		ImageButton instrbutton3 = (ImageButton) view.findViewById(R.id.instrButton3);
		TextView instrtext3 = (TextView) view.findViewById(R.id.instrText3_id);

		// Change TextView below to Desert text
		if(desert != null){
			instrbutton3.setImageResource(view.getResources().getIdentifier(desert.getImage(), "drawable", "se.kth.csc.iprog.dinnerplanner"));
			instrtext3.setText(desert.getName());
		}else{
			instrbutton3.setClickable(false);
			instrbutton3.setImageResource(R.drawable.img_holder);
		}

		changeTab(INGREDIENTS);
	}


	public void changeTab(int type){		

		TextView title = (TextView) view.findViewById(R.id.summary_text_title_id);
		TextView coursetitle = (TextView) view.findViewById(R.id.summary_Course_Title_id);
		TextView nrofpers = (TextView) view.findViewById(R.id.ingr_nrpersons);
		TextView textArea = (TextView) view.findViewById(R.id.summary_textarea_id);
		TextView unitText = (TextView) view.findViewById(R.id.summary_unittext_id);		

		switch(type){
		case INGREDIENTS : 
			title.setText(R.string.ingredients_text);			
			coursetitle.setVisibility(View.GONE);			
			nrofpers.setVisibility(View.VISIBLE);	
			nrofpers.setText(model.getNumberOfGuests()+" pers");
			Set<Ingredient> ingredients = model.getAllIngredients();
			StringBuilder namesb = new StringBuilder(); 
			StringBuilder unitsb = new StringBuilder();
			for (Ingredient i : ingredients) {
				namesb.append(i.getName() +"\n");
				unitsb.append(i.getQuantity()*model.getNumberOfGuests()+" "+i.getUnit()+"\n");
			}
			textArea.setText(namesb.toString());
			unitText.setText(unitsb.toString());
			unitText.setVisibility(View.VISIBLE);
			break;
		case STARTER :  
			title.setText(R.string.starter_text);
			coursetitle.setText(starter.getName());
			coursetitle.setVisibility(View.VISIBLE);
			nrofpers.setVisibility(View.GONE);
			textArea.setText(starter.getDescription());
			unitText.setVisibility(View.GONE);
			break;
		case MAIN :
			title.setText(R.string.main_text);
			coursetitle.setText(main.getName());
			coursetitle.setVisibility(View.VISIBLE);
			nrofpers.setVisibility(View.GONE);
			textArea.setText(main.getDescription());
			unitText.setVisibility(View.GONE);
			break;
		case DESERT :
			title.setText(R.string.desert_text);
			coursetitle.setText(desert.getName());
			coursetitle.setVisibility(View.VISIBLE);
			nrofpers.setVisibility(View.GONE);
			textArea.setText(desert.getDescription());
			unitText.setVisibility(View.GONE);
			break;
		}
	}


	@Override
	public void update(Observable observable, Object data) {
		observable.addObserver(this);
		// TODO Auto-generated method stub
		// Pass in notifyObservers method i.e
	}

}