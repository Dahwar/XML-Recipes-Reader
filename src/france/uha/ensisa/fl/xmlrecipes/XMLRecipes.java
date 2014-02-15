package france.uha.ensisa.fl.xmlrecipes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Florent
 */
public class XMLRecipes extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(XMLRecipes.class.getResource("css/stylesheet.css").toString());
        Image icon = new Image(XMLRecipes.class.getResource("images/icon.png").toString());
        stage.getIcons().add(icon);
        
        stage.setMinHeight(300.0);
        stage.setMinWidth(300.0);
        stage.setScene(scene);
        stage.setTitle("XML Recipes Reader");
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}