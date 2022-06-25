import DataManagement.Point;
import DataManagement.ShipList;
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

        ShipPlacementKI ki = new ShipPlacementKI(11);
        ShipList i = ki.generateShips(2, 2, 2, 2, 2);
        List<Point> a = i.getCarrier();
        List<Point> b = i.getBattleship();
        List<Point> c = i.getCruiser();
        List<Point> d = i.getSubmarine();
        List<Point> e = i.getDestroyer();

        for(int j = 0; j< a.size(); j++)
        {
            System.out.println(a.get(j).getX() + " " + a.get(j).getY());
        }

        for(int j = 0; j< b.size(); j++)
        {
            System.out.println(b.get(j).getX() + " " + b.get(j).getY());
        }

        for(int j = 0; j< c.size(); j++)
        {
            System.out.println(c.get(j).getX() + " "+ c.get(j).getY());
        }

        for(int j = 0; j< d.size(); j++)
        {
            System.out.println(d.get(j).getX() + " "+ d.get(j).getY());
        }

        for(int j = 0; j< e.size(); j++)
        {
            System.out.println(e.get(j).getX() + " "+ e.get(j).getY());
        }


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
