package KI;

import DataManagement.ShipList;
import DataManagement.Point;
import java.util.Random;
import java.util.ArrayList;

public class ShipPlacementKI {

    ShipList shipPlacement;
    ArrayList<Point> checkList;
    int fieldSize;

    public ShipPlacementKI(int size)
    {
        this.fieldSize = size;
        shipPlacement = new ShipList();
        checkList = new ArrayList<>();

    }

    public ShipList generateShips(int carrier, int battleship, int cruiser, int submarine, int destroyer)
    {
        for(int i = 1; i <= carrier; i++)
        {
            calculateShip(6);
        }
        for(int i = 1; i <= battleship; i++)
        {
            calculateShip(5);
        }
        for(int i = 1; i <= cruiser; i++)
        {
            calculateShip(4);
        }
        for(int i = 1; i <= submarine; i++)
        {
            calculateShip(3);
        }
        for(int i = 1; i <= destroyer; i++)
        {
            calculateShip(2);
        }

        return shipPlacement;
    }

    void calculateShip(int length)
    {

        while(true)
        {
            Random rand = new Random();  // Klasse Random
            int xMax = fieldSize - length;  // Maximaler (sinnvoller) x-Wert, bei dem ein Schiff beginnen kann
            int yMax = fieldSize;  // Maximaler y-Wert, den ein Schiff annehmen darf
            int x = rand.nextInt(xMax);  // generiere einen x-Wert
            int y = rand.nextInt(yMax);  // generiere einen y-Wert
            ArrayList<Point> tempCheckList = new ArrayList<>();  // erstelle temporäre ArrayList zum speichern des Schiffes
            boolean breaks = false;  // für den Fall, dass sich ein Schiff überschneidet, wird der Wert true gesetzt

            for (int i = 0; i < length; i++) {  //gehe das Komplette Schiff durch und...

                for(int j = 0; j < checkList.size(); j++)
                {
                    if(checkList.get(j).getX() == x+i && checkList.get(j).getY() == y)
                    {
                        breaks = true;
                        break;  // falls das Schiff ein schon generiertes Schiff schneidet, so berechne neu
                    }
                }
                if(breaks)
                {
                    break;
                }
                tempCheckList.add(new Point(x + i, y));  // falls die Koordinate des neu generierten Schiffes noch nicht
                // besetzt ist, so speichere den Wert
            }

            if(breaks) // falls sich Schiffe überschneiden, so fange die while-Schleife wieder von vorne an
            {
                continue;
            }

            // Füge das Schiff in die ShipList zur passenden Liste hinzu
            if(length == 6)
            {
                shipPlacement.addCarrier(new Point(x, y));
            }

            if(length == 5)
            {
                shipPlacement.addBattleship(new Point(x, y));
            }

            if(length == 4)
            {
                shipPlacement.addCruiser(new Point(x, y));
            }

            if(length == 3)
            {
                shipPlacement.addSubmarine(new Point(x, y));
            }

            if(length == 2)
            {
                shipPlacement.addDestroyer(new Point(x, y));
            }


            checkList.addAll(tempCheckList);  // speichere alle Koordinaten des neu generierten Schiffes in die checkListe ab
            break;
        }
    }


}
