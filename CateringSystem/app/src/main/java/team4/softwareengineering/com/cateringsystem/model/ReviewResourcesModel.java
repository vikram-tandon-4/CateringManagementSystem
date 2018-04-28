package team4.softwareengineering.com.cateringsystem.model;

import java.io.Serializable;

/**
 * Created by Prady on 4/27/2018.
 */

public class ReviewResourcesModel implements Serializable{
    private String foodvenue,mealformality,mealtype,drink,entertainment;

    public String getFoodvenue() {
        return foodvenue;
    }

    public void setFoodvenue(String foodvenue) {
        this.foodvenue = foodvenue;
    }

    public String getMealformality() {
        return mealformality;
    }

    public void setMealformality(String mealformality) {
        this.mealformality = mealformality;
    }

    public String getMealtype() {
        return mealtype;
    }

    public void setMealtype(String mealtype) {
        this.mealtype = mealtype;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }
}
