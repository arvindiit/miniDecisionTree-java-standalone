package main.java.player;

import main.java.core.model.Alternative;
import main.java.core.model.DecisionTree;
import main.java.core.model.Node;
import main.java.core.util.Utils;
import main.java.core.xml.XmlAlternative;
import main.java.core.xml.XmlNode;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvind on 08/12/16.
 * This is un-marshaller class to Unmarshall
 * xml file to decision tree object
 */
public class DecisionTreeUnmarshaller {

    private File file;

    public DecisionTreeUnmarshaller(File file) {
        this.file = file;
    }

    public DecisionTree unmarshall() throws JAXBException {
        return unmarshall(null);
    }

    public DecisionTree unmarshall(@Nullable String nodeName) throws JAXBException {
        DecisionTree decisionTree = new DecisionTree(file.getName());

        //creating un-marshaller
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlNode.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        XmlNode xmlNode = (XmlNode)jaxbUnmarshaller.unmarshal(file);

        if(nodeName != null){
            xmlNode = xmlNode.getChildNode(nodeName);
        }
        if(xmlNode == null){
            //TODO: custom exception should be thrown
            throw new JAXBException("Specified node is not found in file: "+file.getName());
        }
        decisionTree.setRootNode(buildNode(xmlNode));
        return decisionTree;
    }

    private Node buildNode(XmlNode xmlNode) throws JAXBException {

        Node node = new Node(xmlNode.getName());
        //set decision tree
        node.setDecisionTree(getDecisionTreeWithTargetNode(xmlNode));

        //set alternatives
        List<Alternative> list = getAlternatives(xmlNode);
        if(list != null){
            node.getAlternativeList().addAll(list);
        }

        return node;
    }

    private DecisionTree getDecisionTreeWithTargetNode(XmlNode xmlNode) throws JAXBException {
        if(xmlNode.getElement() != null){
            DecisionTree decisionTree = new DecisionTreeUnmarshaller(
                    new File(xmlNode.getElement().getName()))
                    .unmarshall();
            decisionTree.setRootNode(Utils.getTargetNode(decisionTree.getRootNode(), xmlNode.getElement().getNodeName()));
            return decisionTree;
        }

        return null;
    }

    private List<Alternative> getAlternatives(XmlNode xmlNode) throws JAXBException {
        List<Alternative> alternatives = null;
        if(xmlNode.getAlternativeList() != null && !xmlNode.getAlternativeList().isEmpty()){
            alternatives = new ArrayList<>();
            for (XmlAlternative xmlAlternative : xmlNode.getAlternativeList()){
                alternatives.add(new Alternative(xmlAlternative.getName()));
            }
        }

        if(xmlNode.getMappingList() != null && !xmlNode.getMappingList().isEmpty()){
            alternatives = alternatives == null ? new ArrayList<>() : alternatives;
            for (XmlAlternative xmlAlternative : xmlNode.getMappingList()){
                if(xmlAlternative.getMapsTo() == null || xmlAlternative.getMapsTo().trim().equalsIgnoreCase("")){
                    throw new JAXBException("Property mapTo is not found for alternative: "+xmlAlternative.getName());
                }
                ///building child node for all child node: iteratively
                Alternative alternative =  new Alternative(xmlAlternative.getName());
                alternative.setNode(buildNode(xmlNode.getChildNode(xmlAlternative.getMapsTo())));
                alternatives.add(alternative);
            }
        }

        return alternatives;
    }
}
