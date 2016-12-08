package main.java.player;

import main.java.core.model.Alternative;
import main.java.core.model.DecisionTree;
import main.java.core.model.Node;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

/**
 * Created by arvind on 08/12/16.
 * Main player class which can be used to
 * consult for suggestion by passing consultation
 */
public class Player {

    private List<File> files;
    private String consultation;

    public Player(List<File> files, String consultation) {
        this.files = files;
        this.consultation = consultation;
    }

    //start consultation
    public List<String> consult() throws JAXBException {
        for (File file : files){
            DecisionTreeUnmarshaller decisionTreeUnMarshaller = new DecisionTreeUnmarshaller(file);
            DecisionTree tree = decisionTreeUnMarshaller.unmarshall();
            List<String> list = iterate(tree.getRootNode());
            if(list != null && !list.isEmpty()){
                return list;
            }
        }

        return null;
    }

    private List<String> iterate(Node node){
        //if name matches with consultation
        if(node.getName().equalsIgnoreCase(consultation)){
            return getAlternatives(node);
        }
        //else iterate for each child node
        else{
              for (Alternative alternative : node.getAlternativeList()){
                  if(alternative.getNode() != null) {
                      List<String> list = iterate(alternative.getNode());
                      if (list != null) {
                          return list;
                      }
                  }
              }
        }

        return null;
    }

    private List<String> getAlternatives(Node node){
        //if alternative is with node
        if(node.getAlternativeList() != null && !node.getAlternativeList().isEmpty()){
            return node.getAlterativeStrings();
        }
        //or alternatives are part of separate decision tree
        else{
            return node.getDecisionTree().getRootNode().getAlterativeStrings();
        }
    }


}
