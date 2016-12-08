package main.java.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arvind on 08/12/16.
 * POJO to hold node data
 */
public class Node {

    private String name;
    private DecisionTree decisionTree;

    private List<Alternative> alternativeList;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DecisionTree getDecisionTree() {
        return decisionTree;
    }

    public void setDecisionTree(DecisionTree decisionTree) {
        this.decisionTree = decisionTree;
    }

    public List<Alternative> getAlternativeList() {
        alternativeList = alternativeList != null ? alternativeList : new ArrayList<>();
        return alternativeList;
    }

    public Node addAlternative(Alternative alternative){
        alternativeList = alternativeList != null ? alternativeList : new ArrayList<>();
        alternativeList.add(alternative);
        return this;
    }

    public List<String> getAlterativeStrings(){
        return alternativeList.stream()
                .map(x -> x.getName()).collect(Collectors.toList());
    }
}
