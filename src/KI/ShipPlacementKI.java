package KI;
import java.util.Random;
import java.util.ArrayList;

public class ShipPlacementKI {

    ArrayList<int[]> shipPlacement = new ArrayList<>();
    ArrayList<int[]> checkList = new ArrayList<>();
    int fieldSize;

    public ShipPlacementKI(int size)
    {
        fieldSize = size;
    }

    public ArrayList generateShips(int carrier, int battleship, int cruiser, int submarine, int destroyer)
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
            ArrayList<int[]> tempCheckList = new ArrayList<>();  // erstelle temporäre ArrayList zum speichern des Schiffes


            for (int i = 0; i < length; i++) {
                if(checkList.contains(new int[]{x + i, y}))
                {
                    continue;  // falls das Schiff ein schon generiertes Schiff schneidet, so berechne neu
                }
                tempCheckList.add(new int[]{x + i, y});  // falls die Koordinate des neu generierten Schiffes noch nicht
                                                        // besetzt ist, so speichere den Wert
            }
            shipPlacement.add(new int[]{x, y});  // falls eine leere Stelle für das neue Schiff gefunden wurde, so speichere den Startwert des Schiffes ab
            checkList.addAll(tempCheckList);  // speichere alle Koordinaten des neu generierten Schiffes in die checkListe ab
            break;
        }
    }


}
