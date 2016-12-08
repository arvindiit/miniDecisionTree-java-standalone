package main.java.builder;

import main.java.core.model.Alternative;
import main.java.core.model.DecisionTree;
import main.java.core.model.Node;
import main.java.core.xml.XmlAlternative;
import main.java.core.xml.XmlFile;
import main.java.core.xml.XmlNode;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvind on 08/12/16.
 * This is marshaller class to marshall
 * decision tree object to xml file
 */
public class DecisionTreeMarshaller {

    private Node rootNode;
    private String filePath;

    public DecisionTreeMarshaller(DecisionTree decisionTree) {
        this.rootNode = decisionTree.getRootNode();
        this.filePath = decisionTree.getTreeName();
    }


    public File marshall() throws JAXBException {
        File file = new File(filePath);

        //creating marshaller
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlNode.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        XmlNode node = buildNode(rootNode);
        jaxbMarshaller.marshal(node, file);
        return file;
    }

    private XmlNode buildNode(Node node) throws JAXBException {

        XmlNode xmlNode = new XmlNode();
        xmlNode.setName(node.getName());

        //set xml tree
        if(node.getDecisionTree() != null){
            XmlFile xmlFile = new XmlFile();
            DecisionTreeMarshaller builder = new DecisionTreeMarshaller(node.getDecisionTree());
            xmlFile.setName(builder.marshall().getName());
            xmlFile.setNodeName(node.getDecisionTree().getRootNode().getName());
            xmlNode.setElement(xmlFile);
        }

        //set alternatives
        xmlNode.setAlternativeList(getAlternatives(node));

        //set mappings
        xmlNode.setMappingList(getMappings(node));


        //set child nodes
        xmlNode.setList(getChildNodes(node));

        return xmlNode;
    }

    private List<XmlAlternative> getAlternatives(Node node){
        List<XmlAlternative> alternatives = new ArrayList<>();;
        if(node.getAlternativeList() != null && !node.getAlternativeList().isEmpty()){
            for (Alternative alternative : node.getAlternativeList()){
                if(alternative.getNode() == null) {
                    XmlAlternative xmlAlternative = new XmlAlternative();
                    xmlAlternative.setName(alternative.getName());
                    alternatives.add(xmlAlternative);
                }
            }
        }
        return alternatives.size() == 0 ? null : alternatives;
    }

    private List<XmlAlternative> getMappings(Node node){
        List<XmlAlternative> alternatives = new ArrayList<>();
        if(node.getAlternativeList() != null && !node.getAlternativeList().isEmpty()){
            for (Alternative alternative : node.getAlternativeList()){
                if(alternative.getNode() != null) {
                    XmlAlternative xmlAlternative = new XmlAlternative();
                    xmlAlternative.setName(alternative.getName());
                    xmlAlternative.setMapsTo(alternative.getNode().getName());
                    alternatives.add(xmlAlternative);
                }
            }
        }
        return alternatives.size() == 0 ? null : alternatives;
    }

    private List<XmlNode> getChildNodes(Node node) throws JAXBException {
        List<XmlNode> childNodes = null;
        if(node.getAlternativeList() != null && !node.getAlternativeList().isEmpty()){
            for (Alternative alternative : node.getAlternativeList()){
                if(alternative.getNode() != null) {
                    childNodes = childNodes == null ? new ArrayList<>() : childNodes;
                    ///building child node for all child node: iterative
                    childNodes.add(buildNode(alternative.getNode()));
                }
            }
        }
        return childNodes;
    }
}
