package se.kth.csc.iprog.dinnerplanner.android.view;
 
import java.util.Set;
 
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
 
public class MenuButtonListener implements OnClickListener{
 
    private MenuView menuView;
    private DinnerModel model;
    String name = null;
 
    Dish starter;
    Dish main;
    Dish desert;
    Set<Dish> d;
    public MenuButtonListener(DinnerModel model, MenuView menuView) {
        this.menuView = menuView;
        this.model = model;
 
        d = model.getDishes();
 
        for(ImageButton button:menuView.buttonList){
            button.setOnClickListener(this);
        }
    }
     
    Dish tmp = null;
 
    @Override
    public void onClick(View v) {
 
        if (v.getId() == R.id.popup_close){
            menuView.popupWindow.dismiss();
        }
        else if(v.getId() == R.id.popup_choose){
            for (ImageButton ib:menuView.buttonList){
                if (ib.getTag().equals(v.getTag())){
                    ib.setBackgroundResource(R.drawable.img_holder);
                    menuView.popupWindow.dismiss();
                    model.setSelectedDish(tmp);
 
                }
            }
 
        }
 
        else{
            name = (String) v.getTag();
            for(Dish dish:d){
                if (dish.getName().equals(name)){
                    tmp = dish;
                    menuView.popup(dish);
                    menuView.btnDismiss.setOnClickListener(this);
                    menuView.btnChoose.setTag(dish.getName());
                    menuView.btnChoose.setOnClickListener(this);
 
                    break;
                }
            }  
        }
    }
}