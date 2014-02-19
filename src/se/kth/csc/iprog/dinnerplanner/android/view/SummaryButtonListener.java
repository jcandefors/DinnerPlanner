package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class SummaryButtonListener implements OnClickListener{

	private SummaryView mainView;
	private DinnerModel model;
	Dish starter;
	Dish main;
	Dish desert;
	Activity activity;

	public static final int INGREDIENTS = 4, STARTER = 1, MAIN = 2, DESERT = 3;

	public SummaryButtonListener(DinnerModel model, SummaryView mainView, Activity activity){
		this.model = model;
		this.mainView = mainView;
		this.activity = activity;

		starter = model.getSelectedDish(STARTER);
		main = model.getSelectedDish(MAIN);
		desert = model.getSelectedDish(DESERT);		
		
		mainView.ingrbutton1.setOnClickListener(this);
		mainView.instrbutton1.setOnClickListener(this);
		mainView.instrbutton2.setOnClickListener(this);
		mainView.instrbutton3.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()){	

		case R.id.ingrButton1 :
		mainView.tabTitle.setText(R.string.ingredients_text);			
		mainView.coursetitle.setVisibility(View.GONE);			
		mainView.nrofpers.setVisibility(View.VISIBLE);	
		mainView.nrofpers.setText(model.getNumberOfGuests()+" pers");
		Set<Ingredient> ingredients = model.getAllIngredients();
		StringBuilder namesb = new StringBuilder(); 
		StringBuilder unitsb = new StringBuilder();
		for (Ingredient i : ingredients) {
			namesb.append(i.getName() +"\n");
			unitsb.append(i.getQuantity()*model.getNumberOfGuests()+" "+i.getUnit()+"\n");
		}
		mainView.textArea.setText(namesb.toString());
		mainView.unitText.setText(unitsb.toString());
		mainView.unitText.setVisibility(View.VISIBLE);
		break;		

		case R.id.instrButton1 : 
			mainView.tabTitle.setText(R.string.starter_text);
			mainView.coursetitle.setText(starter.getName());
			mainView.coursetitle.setVisibility(View.VISIBLE);
			mainView.nrofpers.setVisibility(View.GONE);
			mainView.textArea.setText(starter.getDescription());
			mainView.unitText.setVisibility(View.GONE);
			break;

		case R.id.instrButton2 : 
			mainView.tabTitle.setText(R.string.main_text);
			mainView.coursetitle.setText(main.getName());
			mainView.coursetitle.setVisibility(View.VISIBLE);
			mainView.nrofpers.setVisibility(View.GONE);
			mainView.textArea.setText(main.getDescription());
			mainView.unitText.setVisibility(View.GONE);
			break;

		case R.id.instrButton3 : 
			mainView.tabTitle.setText(R.string.desert_text);
			mainView.coursetitle.setText(desert.getName());
			mainView.coursetitle.setVisibility(View.VISIBLE);
			mainView.nrofpers.setVisibility(View.GONE);
			mainView.textArea.setText(desert.getDescription());
			mainView.unitText.setVisibility(View.GONE);
			break;			
		}

	}

}