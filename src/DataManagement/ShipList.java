package DataManagement;

import DataManagement.Point;
import java.util.ArrayList;
import java.util.List;

public class ShipList {

    private List<Point> carrier;
    private List<Point> battleship;
    private List<Point> cruiser;
    private List<Point> submarine;
    private List<Point> destroyer;




    public ShipList()
    {
        this.carrier = new ArrayList<>();
        this.battleship = new ArrayList<>();
        this.cruiser = new ArrayList<>();
        this.submarine = new ArrayList<>();
        this.destroyer = new ArrayList<>();
    }


    // getter und setter für alle Schiffarten

    public void addCarrier(Point i)
    {
        carrier.add(i);
    }

    List<Point> getCarrier()
    {
        return carrier;
    }

    public void addBattleship(Point i)
    {
        battleship.add(i);
    }

    List<Point> getBattleship()
    {
        return battleship;
    }

    public void addCruiser(Point i)
    {
        cruiser.add(i);
    }

    List<Point> getCruiser()
    {
        return cruiser;
    }

    public void addSubmarine(Point i)
    {
        submarine.add(i);
    }

    List<Point> getSubmarine()
    {
        return submarine;
    }

    public void addDestroyer(Point i)
    {
        destroyer.add(i);
    }

    List<Point> getDestroyer()
    {
        return destroyer;
    }
}
