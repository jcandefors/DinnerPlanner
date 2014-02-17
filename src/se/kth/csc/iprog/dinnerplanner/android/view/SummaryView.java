package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SummaryView implements Observer {
	Activity activity;
	View view;
	DinnerModel model;
	Dish starter;
	Dish main;
	Dish desert;
	
	SummaryButtonListener buttonListener;
	public TextView tabTitle;
	public TextView coursetitle;
	public TextView nrofpers;
	public TextView textArea;
	public TextView unitText;
	
	public ImageButton ingrbutton1;	
	public ImageButton instrbutton1;
	public ImageButton instrbutton2;
	public ImageButton instrbutton3;
	
	public static final int INGREDIENTS = 4, STARTER = 1, MAIN = 2, DESERT = 3;

	public SummaryView(Activity activity, View view, DinnerModel model) {
		this.model = model;
		this.activity = activity;
		this.view = view;
		
		start();
	}
	
	public void start(){
		
		starter = model.getSelectedDish(STARTER);
		main = model.getSelectedDish(MAIN);
		desert = model.getSelectedDish(DESERT);

		model.registerObserver(this);

		ingrbutton1 = (ImageButton) view.findViewById(R.id.ingrButton1);
		
		// Get Starter ImageButton and TextView
		instrbutton1 = (ImageButton) view.findViewById(R.id.instrButton1);
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
		instrbutton2 = (ImageButton) view.findViewById(R.id.instrButton2);
		TextView instrtext2 = (TextView) view.findViewById(R.id.instrText2_id);

		// Change TextView below to Main text
		if(main != null){
			instrbutton2.setImageResource(view.getResources().getIdentifier(main.getImage(), "drawable", "se.kth.csc.iprog.dinnerplanner"));
			instrbutton2.setOnClickListener(buttonListener);
			instrtext2.setText(main.getName());
		}else{
			instrbutton2.setClickable(false);
			instrbutton2.setOnClickListener(buttonListener);
			instrbutton2.setImageResource(R.drawable.img_holder);
		}

		// Get Desert ImageButton and TextView
		instrbutton3 = (ImageButton) view.findViewById(R.id.instrButton3);
		TextView instrtext3 = (TextView) view.findViewById(R.id.instrText3_id);

		// Change TextView below to Desert text
		if(desert != null){
			instrbutton3.setImageResource(view.getResources().getIdentifier(desert.getImage(), "drawable", "se.kth.csc.iprog.dinnerplanner"));
			instrbutton3.setOnClickListener(buttonListener);
			instrtext3.setText(desert.getName());
		}else{
			instrbutton3.setClickable(false);
			instrbutton3.setImageResource(R.drawable.img_holder);
		}
		
		
		tabTitle = (TextView) view.findViewById(R.id.summary_text_title_id);
		coursetitle = (TextView) view.findViewById(R.id.summary_Course_Title_id);
		nrofpers = (TextView) view.findViewById(R.id.ingr_nrpersons);
		textArea = (TextView) view.findViewById(R.id.summary_textarea_id);
		unitText = (TextView) view.findViewById(R.id.summary_unittext_id);
		
		
		//load ingredients first time
		tabTitle.setText(R.string.ingredients_text);			
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
	}

	@Override
	public void update(Observable observable, Object data) {
		start();
	}

}