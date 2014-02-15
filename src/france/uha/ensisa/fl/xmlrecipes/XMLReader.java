package france.uha.ensisa.fl.xmlrecipes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Florent
 */
public class XMLReader {
    public XMLReader() {}
    
    public Recipe convertXMLtoRecipe(File file) {
        
        Document document;
        Element root;
        SAXBuilder sxb = new SAXBuilder();
        Recipe recipe = new Recipe();
     
        try {
            document = sxb.build(file);
            root = document.getRootElement();
            if(root.getChild("title") == null) {
                recipe.setTitle("Non Indiqué");
            }
            else {
                recipe.setTitle(root.getChild("title").getText());
            }
            
            if(root.getChild("makingTime") == null) {
                recipe.setMakingTime("Non indiqué");
            }
            else {
                recipe.setMakingTime(root.getChild("makingTime").getText());
            }
            
            if(root.getChild("waitingTime") == null) {
                recipe.setWaitingTime("Non indiqué");
            }
            else {
                recipe.setWaitingTime(root.getChild("waitingTime").getText());
            }
            
            if(root.getChild("cookingTime") == null) {
                recipe.setCookingTime("Non indiqué");
            }
            else {
                recipe.setCookingTime(root.getChild("cookingTime").getText());
            }
            
            List<Element> ingredients = root.getChildren("ingredients");
            for(Element current : ingredients) {
                List<Element> ingredient = current.getChildren("ingredient");
                for(Element element : ingredient) {
                    String s = element.getChildText("name") + " : " + element.getChildText("quantity") + " " + element.getChildText("unit");
                    recipe.addIngredient(s);
                }
            }
            List<Element> groups = root.getChildren("groups");
            for(Element current : groups) {
                List<Element> group = current.getChildren("group");
                String groupTitle = "";
                String[] groupStep = new String[0];
                int i=0;
                for(Element element : group) {
                    groupTitle = element.getChildText("title");
                    List<Element> subGroup = element.getChildren("step");
                    groupStep = new String[subGroup.size()];
                    for(Element e : subGroup) {
                        groupStep[i] = e.getText();
                        i++;
                    }
                    i=0;
                    recipe.addGroup(groupTitle, groupStep);
                }
            }
            return recipe;
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
