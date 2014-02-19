package se.kth.csc.iprog.dinnerplanner.android.view;
 
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
 
public class MenuView implements Observer {
 
    View view;
    DinnerModel model;
    Activity activity;
    PopupWindow popupWindow;
 
    public MenuButtonListener buttonListener;
    public ArrayList<ImageButton> buttonList;
    public Button btnDismiss;
    public Button btnChoose;
 
    public MenuView(Activity activity,View view, DinnerModel model) {
        this.view = view;
        this.model = model;
        this.activity = activity;
        buttonList = new ArrayList<ImageButton>();
 
        model.addObserver(this);
        buttons();
    }
 
    public void buttons() {
        //Retrieving all dishes in the model
        Set<Dish> starters = model.getDishesOfType(1);
        Set<Dish> mains = model.getDishesOfType(2);
        Set<Dish> desserts = model.getDishesOfType(3);
 
        LinearLayout.LayoutParams layout1 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layout2 = new LinearLayout.LayoutParams(100, 100);
 
        LinearLayout starterLayout = (LinearLayout) view.findViewById(R.id.linear_starter);
        LinearLayout mainLayout = (LinearLayout) view.findViewById(R.id.layout_main);
        LinearLayout dessertLayout = (LinearLayout) view.findViewById(R.id.layout_dessert);
 
 
        //Setting dish name and dish picture for all the starters in the model
        for (final Dish d:starters) {             
            TextView text = new TextView(view.getContext());
            text.setText(d.getName());
            text.setTextSize(12);
            text.setLayoutParams(layout1);
 
            layout1.setMargins(0, 0, 10, 0);
            LinearLayout layout = new LinearLayout(view.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(layout1);
 
 
            ImageButton starterButton = new ImageButton(view.getContext());
            starterButton.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", activity.getPackageName()));
            starterButton.setLayoutParams(layout2);
            starterButton.setTag(d.getName());
            starterButton.setPadding(5, 5, 5, 5);
            starterButton.setScaleType(ScaleType.FIT_XY);
            starterButton.setOnClickListener(buttonListener);
 
            layout.addView(starterButton,0);
            layout.addView(text,1);
            starterLayout.addView(layout);
             
            buttonList.add(starterButton);
        }
 
 
        //Setting dish name and dish picture for all the main courses in the model
        for (final Dish d:mains) {
            TextView text = new TextView(view.getContext());
            text.setText(d.getName());
            text.setTextSize(12);
            text.setLayoutParams(layout1);
 
            layout1.setMargins(0, 0, 10, 0);
            LinearLayout layout = new LinearLayout(view.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(layout1);
 
 
            ImageButton button = new ImageButton(view.getContext());
            button.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", activity.getPackageName()));
            button.setLayoutParams(layout2);
            button.setTag(d.getName());
            button.setPadding(5, 5, 5, 5);
            button.setScaleType(ScaleType.FIT_XY);
            button.setOnClickListener(buttonListener);
 
            layout.addView(button,0);
            layout.addView(text,1);
            mainLayout.addView(layout);
 
            buttonList.add(button);
        }
 
        //Setting dish name and dish picture for all the desserts in the model
        for (final Dish d:desserts) {
            TextView text = new TextView(view.getContext());
            text.setText(d.getName());
            text.setTextSize(12);
            text.setLayoutParams(layout1);
 
            layout1.setMargins(0, 0, 10, 0);
            LinearLayout layout = new LinearLayout(view.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(layout1);
 
 
            ImageButton button = new ImageButton(view.getContext());
            button.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", activity.getPackageName()));
            button.setLayoutParams(layout2);
            button.setTag(d.getName());
            button.setPadding(10, 10, 10, 10);
            button.setScaleType(ScaleType.FIT_XY);
            button.setOnClickListener(buttonListener);
 
            layout.addView(button,0);
            layout.addView(text,1);
            dessertLayout.addView(layout);
 
            buttonList.add(button);
        }
    }
 
    public void popup(Dish d) {
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_view, null);
 
        popupWindow = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
 
        //set name, image and cost
        TextView popupName = (TextView) popupView.findViewById(R.id.popup_name);
        popupName.setText(d.getName());
 
        ImageView imageView = (ImageView) popupView.findViewById(R.id.popup_image);      
        imageView.setImageResource(view.getResources().getIdentifier(d.getImage(), "drawable", activity.getPackageName()));
 
        TextView popupCost = (TextView) popupView.findViewById(R.id.popup_cost);
        popupCost.setText(Float.toString(d.getDishPrice()) + "kr/person\n" + Float.toString(d.getDishPrice()*model.getNumberOfGuests())+"kr total");
 
        //show popup window
        popupWindow.showAtLocation(popupView,Gravity.CENTER,0,0);
 
        //close popup
        btnDismiss = (Button)popupView.findViewById(R.id.popup_close);
        btnChoose = (Button)popupView.findViewById(R.id.popup_choose);
        btnDismiss.setOnClickListener(buttonListener);
        btnChoose.setOnClickListener(buttonListener);
    }
 
    @Override
    public void update(Observable mod, Object o) {
        //buttons();
    }
}