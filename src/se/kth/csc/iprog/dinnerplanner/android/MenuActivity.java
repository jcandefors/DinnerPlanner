package se.kth.csc.iprog.dinnerplanner.android;
  
import se.kth.csc.iprog.dinnerplanner.R;
import se.kth.csc.iprog.dinnerplanner.android.view.MenuButtonListener;
import se.kth.csc.iprog.dinnerplanner.android.view.MenuView;
import se.kth.csc.iprog.dinnerplanner.android.view.TopView;
import se.kth.csc.iprog.dinnerplanner.android.view.TopViewListener;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
  
public class MenuActivity extends Activity {
  
    TopView top;
    DinnerModel model;
    MenuView menuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
  
        model = ((DinnerPlannerApplication) this.getApplication()).getModel();
          
        //Context context = getBaseContext();
        menuView = new MenuView(this,findViewById(R.id.menu_view_id), model);     
        new MenuButtonListener(model, menuView);
        
        // Start top view 
        top = new TopView(findViewById(R.id.top_view_id), model,this);
        top.hideBackArrow();
        new TopViewListener(top,model,this);
 
        // Create button and listener
        Button createButton = (Button) findViewById(R.id.button_create);
        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(model.getFullMenu().isEmpty()){
                    //do nothing                  
                }else{                
                    Intent intent = new Intent(view.getContext(), SummaryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });
  
    }
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
  
}