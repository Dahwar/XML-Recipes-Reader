package france.uha.ensisa.fl.xmlrecipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Florent
 */
public class Recipe {
    private String title;
    private String makingTime;
    private String waitingTime;
    private String cookingTime;
    private ArrayList<String> ingredients;
    private ArrayList<String> groupTitles;
    private HashMap<String, ArrayList<String>> groupOfSteps;
    private int countNullGroupOfStep;
    
    public Recipe() {
        this.title = "";
        this.makingTime = "";
        this.cookingTime = "";
        this.ingredients = new ArrayList<>();
        this.groupTitles = new ArrayList<>();
        this.groupOfSteps = new HashMap<>();
        this.countNullGroupOfStep = 0;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getMakingTime() {
        return this.makingTime;
    }
    
    public void setMakingTime(String makingTime) {
        this.makingTime=makingTime;
    }
    
    public String getWaitingTime() {
        return this.waitingTime;
    }
    
    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }
    
    public String getCookingTime() {
        return this.cookingTime;
    }
    
    public void setCookingTime(String cookingTime) {
        this.cookingTime=cookingTime;
    }
    
    public ArrayList<String> getIngredients() {
        return this.ingredients;
    }
    
    public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }
    
    public ArrayList<String> getGroupTitles() {
        return this.groupTitles;
    }
    
    public HashMap<String, ArrayList<String>> getGroupOfSteps() {
        return this.groupOfSteps;
    }
    
    public ArrayList<String> getAGroupOfStepByGroupTitle(String s) {
        return this.groupOfSteps.get(s);
    }
    
    public void addGroup(String title, String... steps) {
        ArrayList<String> stepsGroup = new ArrayList<>();
        stepsGroup.addAll(Arrays.asList(steps));
        if(title == null) {
            this.groupTitles.add(title + this.countNullGroupOfStep);
            this.groupOfSteps.put(title + this.countNullGroupOfStep, stepsGroup);
            this.countNullGroupOfStep++;
        }
        else {
            this.groupTitles.add(title);
            this.groupOfSteps.put(title, stepsGroup);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append("\n");
        sb.append(makingTime).append("\n");
        sb.append(waitingTime).append("\n");
        sb.append(cookingTime).append("\n");
        for(String s : getIngredients()) {
            sb.append(s).append("\n");
        }
        for(String s : groupTitles) {
            if(groupOfSteps.get(s).size() > 0) {
                sb.append(s).append("\n");
                for(String ss : groupOfSteps.get(s)) {
                    sb.append(ss).append("\n");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
