package test.java.builder;

import main.java.builder.DecisionTreeBuilder;
import main.java.builder.DecisionTreeMarshaller;
import main.java.core.model.DecisionTree;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by arvind on 08/12/16.
 */
public class TestBuilder {

    public static void main(String args[]) throws JAXBException {
        DecisionTree decisionTree = new DecisionTreeBuilder("file.xml", "Start").addBuilder().addAlternative("Increase temp").addNode("Increase")
                .addAlternative("Increase by 1 degree")
                .addAlternative("Increase by 5 degree")
                .getOriginal()
                .addBuilder().addAlternative("Decrease temp").addNode("Decrease")
                .addAlternative("Decrease by 1 degree")
                .addAlternative("Decrease by 1 degree")
                .getOriginal()
                .addAlternative("Unknown")
                .addNode("Unknown")
                .addDecisionTreeBuilder("PROCESSUNKNOWN.xml", "Unknown")
                .addAlternative("Map to hot")
                .addAlternative("Map to cold")
                .addDecisionTree()
                .getOriginal()
                .build();

        DecisionTreeMarshaller decisionTreeMarshaller = new DecisionTreeMarshaller(decisionTree);
        decisionTreeMarshaller.marshall();

        //TODO: compare 2 files are same or not. Need thirdpart libray like apache or guava
        File originalFile = new File("data/file.xml");
        File generatedFile = new File("file.xml");
        File originalFile2 = new File("data/PROCESSUNKNOWN.xml");
        File generatedFile2 = new File("PROCESSUNKNOWN.xml");
        //boolean isTwoEqual = FileUtils.contentEquals(originalFile, generatedFile);
        //boolean isTwoEqual = FileUtils.contentEquals(originalFile2, generatedFile2);

        generatedFile.delete();
        generatedFile2.delete();


        System.out.println("hi");
    }
}
