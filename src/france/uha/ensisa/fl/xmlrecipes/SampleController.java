package france.uha.ensisa.fl.xmlrecipes;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 *
 * @author Florent
 */
public class SampleController implements Initializable {
    
    @FXML
    TextField xmlFilePath;
    @FXML
    VBox stepsList;
    @FXML
    ListView ingredientsList;
    @FXML
    Label recipeTitle;
    @FXML
    Label makingTime;
    @FXML
    Label waitingTime;
    @FXML
    Label cookingTime;
    
    
    FileChooser fileChooser = new FileChooser();
    XMLReader xmlreader = new XMLReader();
    
    @FXML
    private void chooseXML(ActionEvent event) {
        File file = this.fileChooser.showOpenDialog(this.xmlFilePath.getScene().getWindow());
        if (file != null) {
            this.stepsList.getChildren().clear();
            this.xmlFilePath.setText(file.getPath());
            this.displayRecipe(this.xmlreader.convertXMLtoRecipe(file));
        }
    }
    
    private void displayRecipe(Recipe recipe) {
        System.out.println(recipe);
        this.recipeTitle.setText(recipe.getTitle());
        this.makingTime.setText("Préparation : " + recipe.getMakingTime());
        this.waitingTime.setText("Repos : " + recipe.getWaitingTime());
        this.cookingTime.setText("Cuisson : " + recipe.getCookingTime());
        this.ingredientsList.setItems(FXCollections.observableList(recipe.getIngredients()));
        int i = 1;
        for(String s : recipe.getGroupTitles()) {
            if(recipe.getAGroupOfStepByGroupTitle(s).size() > 0) {
                if(!s.startsWith("null")) {
                    Label l = new Label(i + ". " + s);
                    l.setWrapText(true);
                    l.getStyleClass().add("label-bold");
                    this.stepsList.getChildren().add(l);
                }
                int j = 1;
                for(String ss : recipe.getAGroupOfStepByGroupTitle(s)) {
                    if(!s.startsWith("null")) {
                        Label ll = new Label("\t" + i + "." + j + ". " + ss);
                        ll.setWrapText(true);
                        this.stepsList.getChildren().add(ll);
                        j++;
                    }
                    else {
                        Label ll = new Label(i + ". " + ss);
                        ll.setWrapText(true);
                        this.stepsList.getChildren().add(ll);
                        i++;
                    }
                }
                this.stepsList.getChildren().add(new Label(""));
                if(!s.startsWith("null")) {
                    i++;
                }
            }
        }
    }
    
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configure FileChooser
        this.fileChooser.setTitle("Select XML File");
        this.fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); 
        this.fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("XML File", "*.xml")
        );
    }    
}
