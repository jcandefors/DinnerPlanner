package se.kth.csc.iprog.dinnerplanner.model;
 
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
 
public class DinnerModel extends Observable implements IDinnerModel {
    private int nrOfGuests;
    Set<Dish> dishes = new HashSet<Dish>();
    Set<Dish> selectedDishes = new HashSet<Dish>(3);
 
    /**
     * The constructor of the overall model. Set the default values here
     */
    public DinnerModel(){
 
        //Adding some example data, you can add more
        Dish dish1 = new Dish("French toast",Dish.STARTER,"toast","In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
        Ingredient dish1ing1 = new Ingredient("eggs",0.5,"",1);
        Ingredient dish1ing2 = new Ingredient("milk",30,"ml",6);
        Ingredient dish1ing3 = new Ingredient("brown sugar",7,"g",1);
        Ingredient dish1ing4 = new Ingredient("ground nutmeg",0.5,"g",12);
        Ingredient dish1ing5 = new Ingredient("white bread",2,"slices",2);
        dish1.addIngredient(dish1ing1);
        dish1.addIngredient(dish1ing2);
        dish1.addIngredient(dish1ing3);
        dish1.addIngredient(dish1ing4);
        dish1.addIngredient(dish1ing5);
        dishes.add(dish1);
 
        Dish dish2 = new Dish("Meat balls",Dish.MAIN,"meatballs","Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
        Ingredient dish2ing1 = new Ingredient("extra lean ground beef",115,"g",20);
        Ingredient dish2ing2 = new Ingredient("sea salt",0.7,"g",3);
        Ingredient dish2ing3 = new Ingredient("small onion, diced",0.25,"",2);
        Ingredient dish2ing4 = new Ingredient("garlic salt",0.6,"g",3);
        Ingredient dish2ing5 = new Ingredient("Italian seasoning",0.3,"g",3);
        Ingredient dish2ing6 = new Ingredient("dried oregano",0.3,"g",3);
        Ingredient dish2ing7 = new Ingredient("crushed red pepper flakes",0.6,"g",3);
        Ingredient dish2ing8 = new Ingredient("Worcestershire sauce",16,"ml",7);
        Ingredient dish2ing9 = new Ingredient("milk",20,"ml",4);
        Ingredient dish2ing10 = new Ingredient("grated Parmesan cheese",5,"g",8);
        Ingredient dish2ing11 = new Ingredient("seasoned bread crumbs",115,"g",4);
        dish2.addIngredient(dish2ing1);
        dish2.addIngredient(dish2ing2);
        dish2.addIngredient(dish2ing3);
        dish2.addIngredient(dish2ing4);
        dish2.addIngredient(dish2ing5);
        dish2.addIngredient(dish2ing6);
        dish2.addIngredient(dish2ing7);
        dish2.addIngredient(dish2ing8);
        dish2.addIngredient(dish2ing9);
        dish2.addIngredient(dish2ing10);
        dish2.addIngredient(dish2ing11);
        dishes.add(dish2);
 
        Dish dish3 = new Dish("Ice cream",Dish.DESERT,"icecream","Take out from freezer");
        Ingredient dish3ing1 = new Ingredient("Ice",200,"g",34);
        Ingredient dish3ing2 = new Ingredient("Cream",1,"dl",10);
        dish3.addIngredient(dish3ing1);
        dish3.addIngredient(dish3ing2);
        dishes.add(dish3);
 
        Dish dish4 = new Dish("Sour dough",Dish.STARTER,"sourdough","Do something");
        Ingredient dish4ing1 = new Ingredient("sour",50,"mg",20);
        Ingredient dish4ing2 = new Ingredient("dough",1,"kg",120);
        dish4.addIngredient(dish4ing1);
        dish4.addIngredient(dish4ing2);
        dishes.add(dish4);
        //TO check summary activity
        //selectedDishes.add(dish1);
        //selectedDishes.add(dish2);
        setNumberOfGuests(0);
    }
 
    /**
     * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
     */
    public Set<Dish> getDishes(){
        return dishes;
    }
 
    /**
     * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
     */
    public Set<Dish> getDishesOfType(int type){
        Set<Dish> result = new HashSet<Dish>();
        for(Dish d : dishes){
            if(d.getType() == type){
                result.add(d);
            }
        }
        return result;
    }
 
    /**
     * Returns the set of dishes of specific type, that contain filter in their name
     * or name of any ingredient.
     */
    public Set<Dish> filterDishesOfType(int type, String filter){
        Set<Dish> result = new HashSet<Dish>();
        for(Dish d : dishes){
            if(d.getType() == type && d.contains(filter)){
                result.add(d);
            }
        }
        return result;
    }
 
    @Override
    public int getNumberOfGuests() {
        return nrOfGuests;
    }
 
    @Override
    public void setNumberOfGuests(int numberOfGuests) {
        this.nrOfGuests = numberOfGuests;
        setChanged();
        notifyObservers();
    }
 
    @Override
    public Dish getSelectedDish(int type) {    
        for(Dish d : selectedDishes){
            if(d.getType() == type){
                return d;
            }  
        }
        return null;
    }
    @Override
    public void setSelectedDish(Dish d){
        this.selectedDishes.add(d);
        setChanged();
        notifyObservers();
    }
 
    @Override
    public Set<Dish> getFullMenu() {
        return selectedDishes;
    }
 
    @Override
    public Set<Ingredient> getAllIngredients() {
        Set<Ingredient> resultingredients = new HashSet<Ingredient>();     
        for(Dish d : selectedDishes){
            resultingredients.addAll(d.getIngredients());
        }
        return resultingredients;
    }
 
    @Override
    public float getTotalMenuPrice() {
        float price = 0;
        Set<Ingredient> ingredients = getAllIngredients();
        for (Ingredient i : ingredients){
            price += i.getPrice()*nrOfGuests;
        }
        return price;
    }
 
}