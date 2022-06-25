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

    public List<Point> getCarrier()
    {
        return carrier;
    }

    public void addBattleship(Point i)
    {
        battleship.add(i);
    }

    public List<Point> getBattleship()
    {
        return battleship;
    }

    public void addCruiser(Point i)
    {
        cruiser.add(i);
    }

    public List<Point> getCruiser()
    {
        return cruiser;
    }

    public void addSubmarine(Point i)
    {
        submarine.add(i);
    }

    public List<Point> getSubmarine()
    {
        return submarine;
    }

    public void addDestroyer(Point i)
    {
        destroyer.add(i);
    }

    public List<Point> getDestroyer()
    {
        return destroyer;
    }
}
