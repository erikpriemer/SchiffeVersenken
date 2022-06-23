import Interfaces.BattleshipGUI;
import KI.ShipPlacementKI;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Interfaces.BattleshipGUI mit der Spielfeldgröße 11
        new BattleshipGUI(11);

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
