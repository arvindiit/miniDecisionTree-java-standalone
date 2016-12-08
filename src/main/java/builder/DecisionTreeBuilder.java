package main.java.builder;

import main.java.core.model.Alternative;
import main.java.core.model.DecisionTree;
import main.java.core.model.Node;

/**
 * Created by arvind on 08/12/16.
 * This is decision builder class can be used
 * to build a decision tree
 */
public class DecisionTreeBuilder {

    private DecisionTree decisionTree;
    private Node node;
    private Alternative alternative;
    private DecisionTreeBuilder original = null;

    public DecisionTreeBuilder(String decisionTreeName, String nodeName) {
        this.decisionTree = new DecisionTree(decisionTreeName);
        this.node = new Node(nodeName);
        this.decisionTree.setRootNode(node);
    }

    public DecisionTreeBuilder addBuilder() {
        DecisionTreeBuilder decisionTreeBuilder = new DecisionTreeBuilder(this.node, this.alternative);
        decisionTreeBuilder.original = this;
        return decisionTreeBuilder;
    }

    public DecisionTreeBuilder addDecisionTreeBuilder(String decisionTreeName, String nodeName) {
        DecisionTreeBuilder decisionTreeBuilder = new DecisionTreeBuilder(decisionTreeName, nodeName);
        decisionTreeBuilder.original = this;
        return decisionTreeBuilder;
    }

    public DecisionTreeBuilder(Node node, Alternative alternative) {
        this.node = node;
        this.alternative = alternative;
    }

    public DecisionTreeBuilder addDecisionTree(){
        this.original.node.setDecisionTree(this.decisionTree);
        return this;
    }

    public DecisionTreeBuilder addAlternative(String alternativeName){
        this.alternative = new Alternative(alternativeName);
        this.node.addAlternative(alternative);
        return this;
    }

    public DecisionTreeBuilder addNode(String nodename){
        this.node = new Node(nodename);
        this.alternative.setNode(node);
        return this;
    }

    public DecisionTreeBuilder getOriginal(){
        return original;
    }

    public DecisionTree build(){
        return this.decisionTree;
    }

}
