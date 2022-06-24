import Interfaces.StartingGUI;
import KI.ShipPlacementKI;
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
        ShipPlacementKI ki = new ShipPlacementKI(11);
        ArrayList<int[]> i = ki.generateShips(2, 2, 2, 2, 2);
        for(int y = 0; y < i.size(); y++)
        {
            System.out.println(i.get(y)[0]);
            System.out.println(i.get(y)[1]);
            System.out.println("---");
        }

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
