package test.java.player;

import main.java.player.Player;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvind on 08/12/16.
 */
public class TestPlayer {


    public static void main(String[] args) throws JAXBException {
        List<File> list = new ArrayList<>();
        list.add(new File("file.xml"));
        Player player = new Player(list, "Unknown");
        List<String> alternatives = player.consult();

        System.out.println(alternatives);
    }

}
