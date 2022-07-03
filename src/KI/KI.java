package KI;

import DataManagement.KIHelper;
import DataManagement.ShipList;
import DataManagement.Point;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class KI {

    ArrayList<Point> checkList;
    ShipList shipList;
    int fieldSize;
    Point shot;
    public KIHelper helper;

    public KI(int size)
    {
        this.shipList = new ShipList();
        this.fieldSize = size;  // Feldgröße
        checkList = new ArrayList<>();  // Liste mit allen Schiffskoordinaten
        this.helper = new KIHelper();

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
            int z = rand.nextInt(2);
            //System.out.println("random value "  + z);
            ArrayList<Point> tempCheckList = new ArrayList<>();  // erstelle temporäre ArrayList zum speichern des Schiffes
            boolean breaks = false;  // für den Fall, dass sich ein Schiff überschneidet, wird der Wert true gesetzt

            if(z == 0)
            {
                for (int i = 0; i < length; i++) {  //Erstellt vertikales Schiff

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
            }

            else
            {
                for (int i = 0; i < length; i++) {  // erstellt horizontales Schiff

                    for(int j = 0; j < checkList.size(); j++)
                    {
                        if(checkList.get(j).getX() == y && checkList.get(j).getY() == x+i)
                        {
                            breaks = true;
                            break;  // falls das Schiff ein schon generiertes Schiff schneidet, so berechne neu
                        }
                    }
                    if(breaks)
                    {
                        break;
                    }
                    tempCheckList.add(new Point(y, x+i));  // falls die Koordinate des neu generierten Schiffes noch nicht
                    // besetzt ist, so speichere den Wert
                }
            }

            if(breaks) // falls sich Schiffe überschneiden, so fange die while-Schleife wieder von vorne an
            {
                continue;
            }

            if(length == 6)
            {
                shipList.addCarrier(new Point(x, y));
            }

            if(length == 5)
            {
                shipList.addDestroyer(new Point(x, y));
            }

            if(length == 4)
            {
                shipList.addCruiser(new Point(x, y));
            }

            if(length == 3)
            {
                shipList.addSubmarine(new Point(x, y));
            }

            if(length == 2)
            {
                shipList.addDestroyer(new Point(x, y));
            }

            checkList.addAll(tempCheckList);  // speichere alle Koordinaten des neu generierten Schiffes in die checkListe ab
            break;
        }
    }

    public Point shootHard(List<Point> hit, List<Point> miss, boolean lastShot)  // gibt Point zurück
    {
        if(!lastShot && !helper.inProgress)
        {
            while (true) {
                boolean fieldAlreadyShot = false;
                Random random = new Random();
                int x = random.nextInt(fieldSize);
                int y = random.nextInt(fieldSize); // erstelle einen Random Point mit x und y Wert
                int z = random.nextInt(2);
                Point p = new Point(x, y);


                fieldAlreadyShot = isFieldAlreadyShot(hit, miss, p);

                if(!fieldAlreadyShot)  // falls das Feld noch nicht beschossen wurde, so gebe den Point zurück
                {
                    //System.out.println(p.getX() + " " + p.getY());
                    shot = p;
                    return p;
                }
            }
        }
        else
        {
            if(!helper.inProgress)
            {
                helper.start = shot;
                helper.inProgress = true;
            }

            if(!helper.right)
            {
                for(int i = 0; i + helper.start.getX() < fieldSize; i++)
                {
                    if(!isFieldAlreadyShot(hit, miss, new Point(helper.start.getX() + i, helper.start.getY())))
                    {
                        System.out.println("not known");
                        return new Point(helper.start.getX() + i, helper.start.getY());
                    }

                    if(isFieldAlreadyMissed(miss, new Point(helper.start.getX() + i, helper.start.getY())) || helper.start.getX() + i == fieldSize)
                    {
                        System.out.println("missed");
                        helper.right = true;
                        break;
                    }

                    if(isFieldAlreadyHit(hit, new Point(helper.start.getX() + i, helper.start.getY())))
                    {
                        System.out.println("hit");
                        continue;
                    }

                }
            }


            if(!helper.left)
            {
                for(int i = 0; helper.start.getX() - i >= 0; i++)
                {
                    if(!isFieldAlreadyShot(hit, miss, new Point(helper.start.getX() - i, helper.start.getY())))
                    {
                        System.out.println("unknown");
                        return new Point(helper.start.getX() - i, helper.start.getY());
                    }

                    if(isFieldAlreadyMissed(miss, new Point(helper.start.getX() - i, helper.start.getY())) || helper.start.getX() - i == 0)
                    {
                        System.out.println("missed");
                        helper.left = true;
                        break;
                    }

                    if(isFieldAlreadyHit(hit, new Point(helper.start.getX() - i, helper.start.getY())))
                    {
                        System.out.println("hit");
                        continue;
                    }

                }
            }


            if(!helper.bottom)
            {
                for(int i = 0; i + helper.start.getY() < fieldSize; i++)
                {
                    if(!isFieldAlreadyShot(hit, miss, new Point(helper.start.getX(), helper.start.getY() + i)))
                    {
                        System.out.println("unknown");
                        return new Point(helper.start.getX(), helper.start.getY() + i);
                    }

                    if(isFieldAlreadyMissed(miss, new Point(helper.start.getX(), helper.start.getY() + i)) ||helper.start.getY() + i == fieldSize)
                    {
                        System.out.println("missed");
                        helper.bottom = true;
                        break;
                    }

                    if(isFieldAlreadyHit(hit, new Point(helper.start.getX(), helper.start.getY() + i)))
                    {
                        System.out.println("hit");
                        continue;
                    }
                }
            }


            if(!helper.top)
            {
                for(int i = 0; helper.start.getY() - i >= 0; i++)
                {
                    if(!isFieldAlreadyShot(hit, miss, new Point(helper.start.getX(), helper.start.getY() - i)))
                    {
                        return new Point(helper.start.getX(), helper.start.getY() - i);
                    }

                    if(isFieldAlreadyMissed(miss, new Point(helper.start.getX(), helper.start.getY() - i)) ||helper.start.getY() - i == 0)
                    {
                        helper.top = true;
                        helper.inProgress = false;
                        break;
                    }

                    if(isFieldAlreadyHit(hit, new Point(helper.start.getX(), helper.start.getY() - i)))
                    {
                        continue;
                    }
                }
            }

            helper.top = helper.bottom = helper.left = helper.right = false;
            return shoot(hit, miss);

        }
    }

    public Point shoot(List<Point> hit, List<Point> miss)  // gibt Point zurück
    {
        while (true) {
            boolean fieldAlreadyShot = false;
            Random random = new Random();
            int x = random.nextInt(fieldSize);
            int y = random.nextInt(fieldSize); // erstelle einen Random Point mit x und y Wert
            int z = random.nextInt(2);
            Point p = new Point(x, y);


            fieldAlreadyShot = isFieldAlreadyShot(hit, miss, p);


            if(!fieldAlreadyShot)  // falls das Feld noch nicht beschossen wurde, so gebe den Point zurück
            {
                //System.out.println(p.getX() + " " + p.getY());
                return p;
            }
        }
    }

    public ShipList getShipList()
    {
        return shipList;
    }

    private boolean isFieldAlreadyShot(List<Point> hit, List<Point> miss, Point p)
    {
        boolean fieldAlreadyShot = false;

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
        return fieldAlreadyShot;
    }

    private boolean isFieldAlreadyMissed(List<Point> miss, Point p)
    {
        boolean fieldAlreadyShot = false;

        for (int i = 0; i < miss.size(); i++)
        {
            //System.out.println(p.getY() + "==" + miss.get(i).getY() + "&&" +  p.getX()  + "==" + miss.get(i).getX());

            if(p.getY() == miss.get(i).getY() && p.getX() == miss.get(i).getX())  // falls der generierte Point schonmal generiert wurde...
            {
                fieldAlreadyShot = true;
            }
        }
        return fieldAlreadyShot;
    }

    private boolean isFieldAlreadyHit(List<Point> hit, Point p)
    {
        boolean fieldAlreadyShot = false;

        for (int i = 0; i < hit.size(); i++)  // gehe alle treffer durch
        {
            //System.out.println(p.getY() + "==" + hit.get(i).getY() + "&&" +  p.getX()  + "==" + hit.get(i).getX());

            if(p.getY() == hit.get(i).getY() && p.getX() == hit.get(i).getX())  // falls der generierte Point schonmal generiert wurde...
            {
                fieldAlreadyShot = true;
            }
        }

        return fieldAlreadyShot;
    }




}
