package se.kth.csc.iprog.dinnerplanner.android.view;


import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;



public class SummaryView {
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

		TextView totalcost = (TextView) view.findViewById(R.id.totalcost_text);


		totalcost.setText(view.getResources().getString(R.string.total_cost)+" "+ model.getTotalMenuPrice() + " kr");

		ImageButton instrbutton1 = (ImageButton) view.findViewById(R.id.instrButton1);
		TextView instrtext1 = (TextView) view.findViewById(R.id.instrText1_id);
		if(starter != null){			
			instrbutton1.setImageResource(view.getResources().getIdentifier(starter.getImage(), "Drawable", this.getClass().getPackage().toString()));			
			instrbutton1.setClickable(true);
			instrtext1.setText(starter.getName());
		}else{
			instrbutton1.setImageResource(R.drawable.img_holder);
			instrbutton1.setClickable(false);
		}


		ImageButton instrbutton2 = (ImageButton) view.findViewById(R.id.instrButton2);
		TextView instrtext2 = (TextView) view.findViewById(R.id.instrText2_id);
		if(main != null){
			//TODO läsa in bilderna
			instrbutton1.setImageResource(view.getResources().getIdentifier(main.getImage(), "Drawable", this.getClass().getPackage().toString()));
			instrtext2.setText(main.getName());
		}else{
			instrbutton2.setClickable(false);
			instrbutton2.setImageResource(R.drawable.img_holder);
		}


		ImageButton instrbutton3 = (ImageButton) view.findViewById(R.id.instrButton3);
		TextView instrtext3 = (TextView) view.findViewById(R.id.instrText3_id);
		if(desert != null){
			instrbutton3.setImageDrawable(Drawable.createFromPath("@drawable/"+desert.getImage()));
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

		switch(type){
		case INGREDIENTS : 			
			title.setText(R.string.ingredients_text);			
			coursetitle.setVisibility(view.GONE);			
			nrofpers.setVisibility(view.VISIBLE);	
			nrofpers.setText(model.getNumberOfGuests()+" pers");
			Set<Ingredient> ingredients = model.getAllIngredients();
			StringBuilder sb = new StringBuilder(); 
			for (Ingredient i : ingredients) {
				sb.append(i.getName() +" "+i.getQuantity()+" "+i.getUnit()+"\n");
			}
			textArea.setText(sb.toString());
			break;
		case STARTER :  
			title.setText(R.string.starter_text);
			coursetitle.setText(starter.getName());
			coursetitle.setVisibility(view.VISIBLE);
			nrofpers.setVisibility(view.GONE);
			textArea.setText(starter.getDescription());
			break;
		case MAIN :
			title.setText(R.string.main_text);
			coursetitle.setText(main.getName());
			coursetitle.setVisibility(view.VISIBLE);
			nrofpers.setVisibility(view.GONE);
			textArea.setText(main.getDescription());
			break;
		case DESERT :
			title.setText(R.string.desert_text);
			coursetitle.setText(desert.getName());
			coursetitle.setVisibility(view.VISIBLE);
			nrofpers.setVisibility(view.GONE);
			textArea.setText(desert.getDescription());
			break;
		}
	}

}
