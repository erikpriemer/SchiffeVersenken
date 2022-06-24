import Interfaces.BattleshipGUI;
import Interfaces.StartingGUI;
import KI.ShipPlacementKI;
import Interfaces.PrepareBattleshipGUI;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        new StartingGUI();

        ShipPlacementKI ki = new ShipPlacementKI(11);
        ArrayList<int[]> i = ki.generateShips(2, 2, 2, 2, 2);
        for(int y = 0; y < i.size(); y++)
        {
            System.out.println(i.get(y)[0]);
            System.out.println(i.get(y)[1]);
            System.out.println("---");
        }
    }

}
