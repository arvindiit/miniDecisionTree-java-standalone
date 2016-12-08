package main.java.core.model;

/**
 * Created by arvind on 08/12/16.
 * Pojo to hold decision tree data
 */
public class DecisionTree {

    private String treeName;
    private Node rootNode;

    public DecisionTree(String treeName) {
        this.treeName = treeName;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }
}
