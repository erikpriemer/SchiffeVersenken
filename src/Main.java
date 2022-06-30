import DataManagement.Point;
import DataManagement.ShipList;
import Interfaces.StartingGUI;
import Database.ScoreHandler;
import DataManagement.GameData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new StartingGUI();


        // Tests
        /*
        ShipPlacementKI ki = new ShipPlacementKI(8);
        ArrayList<Point> i = ki.generateShips(2, 2, 2, 2, 2);

        for(int j = 0; j< i.size(); j++)
        {
            System.out.println(i.get(j).getX() + " " + i.get(j).getY());
        }

         */


        /*
        ScoreHandler s = new ScoreHandler();
        GameData g = new GameData();
        g.setTurn(2);
        int[] j = {1, 2 ,3};
        g.addMyShip(j);
        s.serialization(g);
        GameData v = s.deserialization();
        int k = v.getTurn();
        System.out.println(k);

         */

    }
}
