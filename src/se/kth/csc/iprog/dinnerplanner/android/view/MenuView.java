package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Set;
import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MenuView {

	View view;
	DinnerModel model;
	Context context;
	PopupWindow popupWindow;

	public MenuView(View view, DinnerModel model, final Context context) {
		this.view = view;
		this.model = model;
		this.context = context;

		//Get create button
		// Button createButton = (Button) view.findViewById(R.id.button_create);

		//Retrieving all dishes in the model
		Set<Dish> starters = model.getDishesOfType(1);
		Set<Dish> mains = model.getDishesOfType(2);
		Set<Dish> desserts = model.getDishesOfType(3);


		//The three for-loops below should create new elements (one vertical view + imagebutton and textview for each dish),
		//but it seems as though setting layout parameters is frowned upon..

		//Setting dish name and dish picture for all the starters in the model
		for (final Dish d:starters) {
			TextView text = (TextView) view.findViewById(R.id.text_starter1);
			text.setText(d.getName());
			final ImageButton button = (ImageButton) view.findViewById(R.id.ImageButton_starter1);
			button.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", context.getPackageName()));
			button.setOnClickListener(new ImageButton.OnClickListener(){
				public void onClick(View arg0) {
					popup(d);
				}
			});

		}

		//Setting dish name and dish picture for all the main courses in the model 
		for (final Dish d:mains) {
			TextView text = (TextView) view.findViewById(R.id.text_main1);
			text.setText(d.getName());
			ImageButton button = (ImageButton) view.findViewById(R.id.ImageButton_main1);
			button.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", context.getPackageName()));
			button.setOnClickListener(new ImageButton.OnClickListener(){
				public void onClick(View arg0) {
					popup(d);
				}
			});
		}

		//Setting dish name and dish picture for all the desserts in the model
		for (final Dish d:desserts) {
			TextView text = (TextView) view.findViewById(R.id.text_dessert1);
			text.setText(d.getName());
			ImageButton button = (ImageButton) view.findViewById(R.id.ImageButton_dessert1);
			button.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", context.getPackageName()));
			button.setOnClickListener(new ImageButton.OnClickListener(){
				public void onClick(View arg0) {
					popup(d);
				}
			});
		}
	}

	public void popup(Dish d) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popupView = inflater.inflate(R.layout.popup_view, null); 
		popupWindow = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

		//set name, image and cost
		TextView popupName = (TextView) popupView.findViewById(R.id.popup_name);
		popupName.setText(d.getName());

		ImageView imageView = (ImageView) popupView.findViewById(R.id.popup_image);        
		imageView.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", context.getPackageName()));

		TextView popupCost = (TextView) popupView.findViewById(R.id.popup_cost);
		popupCost.setText(Float.toString(d.getDishPrice()) + "kr");

		//show popup window
		popupWindow.showAtLocation(popupView,Gravity.CENTER,0,0);

		//close popup
		Button btnDismiss = (Button)popupView.findViewById(R.id.popup_close);
		btnDismiss.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				popupWindow.dismiss();
			}});
	}

}