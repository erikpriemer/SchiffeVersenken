package KI;

import DataManagement.ShipList;
import DataManagement.Point;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class KI {

    ArrayList<Point> checkList;
    int fieldSize;

    public KI(int size)
    {
        this.fieldSize = size;  // Feldgröße
        checkList = new ArrayList<>();  // Liste mit allen Schiffskoordinaten

    }

    public ArrayList<Point> generateShips(int carrier, int battleship, int cruiser, int submarine, int destroyer)  //liste mit schiffen zurück
    {
        for(int i = 1; i <= carrier; i++)
        {
            calculateShip(6); // berechne Schiffe mit länge 6
        }
        for(int i = 1; i <= battleship; i++)
        {
            calculateShip(5); // mit 5
        }
        for(int i = 1; i <= cruiser; i++)
        {
            calculateShip(4); //..
        }
        for(int i = 1; i <= submarine; i++)
        {
            calculateShip(3);
        }
        for(int i = 1; i <= destroyer; i++)
        {
            calculateShip(2);
        }

        return checkList;
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

            for (int i = 0; i < length; i++) {  //gehe das Komplette checkliste durch und...

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
                tempCheckList.add(new Point(x+i, y));  // falls die Koordinate des neu generierten Schiffes noch nicht
                // besetzt ist, so speichere den Wert
            }

            if(breaks) // falls sich Schiffe überschneiden, so fange die while-Schleife wieder von vorne an
            {
                continue;
            }

            // Füge das Schiff in die ShipList zur passenden Liste hinzu

            checkList.addAll(tempCheckList);  // speichere alle Koordinaten des neu generierten Schiffes in die checkListe ab
            break;
        }
    }

    public Point shoot(List<Point> hit, List<Point> miss)  // gibt Point zurück
    {
        while (true) {
            boolean fieldAlreadyShot = false;
            Random random = new Random();
            int x = random.nextInt(fieldSize);
            int y = random.nextInt(fieldSize); // erstelle einen Random Point mit x und y Wert
            Point p = new Point(x, y);

            for (int i = 0; i < hit.size(); i++)  // gehe alle treffer durch
            {
                //System.out.println(p.getY() + "==" + hit.get(i).getY() + "&&" +  p.getX()  + "==" + hit.get(i).getX());

                if(p.getY() == hit.get(i).getY() && p.getX() == hit.get(i).getX())  // falls der generierte Point schonmal generiert wurde...
                {
                    fieldAlreadyShot = true;
                }
            }

            for (int i = 0; i < miss.size(); i++)
            {
                //System.out.println(p.getY() + "==" + miss.get(i).getY() + "&&" +  p.getX()  + "==" + miss.get(i).getX());

                if(p.getY() == miss.get(i).getY() && p.getX() == miss.get(i).getX())  // falls der generierte Point schonmal generiert wurde...
                {
                    fieldAlreadyShot = true;
                }
            }

            if(!fieldAlreadyShot)  // falls das Feld noch nicht beschossen wurde, so gebe den Point zurück
            {
                System.out.println(p.getX() + " " + p.getY());
                return p;
            }
        }
    }


}
