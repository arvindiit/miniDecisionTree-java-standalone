package main.java.core.model;

/**
 * Created by arvind on 08/12/16.
 * Pojo to to hold alternative data
 */
public class Alternative {

    private String name;
    private Node node;

    public Alternative(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
