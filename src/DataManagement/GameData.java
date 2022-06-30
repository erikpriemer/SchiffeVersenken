package DataManagement;


import DataManagement.ShipList;
import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable {

    private ShipList myShips, opponentShipsList;
    private List<Point> myShipsHit, opponentShipsHit, myShipsMissed, opponentShipsMissed, opponentShips;
    private int[] shipsNeeded;
    private int[] shipsPlaced;
    private int turn, gameType, size;
    private boolean shipsSet;

    public GameData(int[] shipsNeeded)
    {
        this.shipsNeeded = shipsNeeded;
        this.shipsPlaced = new int[5];
        this.shipsSet = false;
        this.turn = 0;  // 1 = my Turn, 2 = opponents Turn,  0 = not set jet
        this.gameType = 0;  // 1 = offline game, 2 = online game as server, 3 = online game as client, 0 = not set jet
        this.size = 0; // größe des Spielfelds
        this.myShips = new ShipList();  // array Liste mit meinen Schiffen
        this.opponentShips = new ArrayList<>();  // array Liste mit den gegnerischen Schiffen  Wird für Single-Player verwendet
        this.opponentShipsList = new ShipList();  // ShipList mit allen gegnerischen Schiffen (geordnet nach Schiffart)
        this.myShipsHit = new ArrayList<>();  // array Liste mit meinen getroffenen Schiffen
        this.opponentShipsHit = new ArrayList<>();  // array Liste mit den Schiffen, die ich getroffen habe
        this.myShipsMissed = new ArrayList<>();  // array Liste mit meinen verfehlten Schiffen
        this.opponentShipsMissed = new ArrayList<>();  // array Liste mit den Schiffen, die ich verfehlt habe
    }


    public void addMyCarrier(Point ship)
    {
        myShips.addCarrier(ship);
    }

    public void addMyBattleship(Point ship)
    {
        myShips.addBattleship(ship);
    }

    public void addMyCruiser(Point ship)
    {
        myShips.addCruiser(ship);
    }

    public void addMySubmarine(Point ship)
    {
        myShips.addSubmarine(ship);
    }

    public void addMyDestroyer(Point ship)
    {
        myShips.addDestroyer(ship);
    }


    public void addOpponentShip(Point ship)
    {
        opponentShips.add(ship);
    }

    public void setOpponentShips(ArrayList<Point> ships)
    {
        opponentShips = ships;

        for(int j = 0; j< ships.size(); j++)
        {
            System.out.println(ships.get(j).getX() + " " + ships.get(j).getY());
        }
    }



    public void addMyShipHit(Point ship)
    {
        myShipsHit.add(ship);
    }

    public void addOpponentShipHit(Point ship)
    {
        opponentShipsHit.add(ship);
    }

    public void addMyShipsMissed(Point ship)
    {
        myShipsMissed.add(ship);
    }

    public void addOpponentShipsMissed(Point ship)
    {
        opponentShipsMissed.add(ship);
    }

    public ShipList getMyShips()
    {
        return this.myShips;
    }

    public List<Point> getOpponentShips() { return this.opponentShips; }

    public List<Point> getMyShipsHit()
    {
        return this.myShipsHit;
    }

    public List<Point> getOpponentShipsHit()
    {
        return this.opponentShipsHit;
    }

    public List<Point> getMyShipsMissed()
    {
        return this.myShipsMissed;
    }

    public List<Point> getOpponentShipsMissed()
    {
        return this.opponentShipsMissed;
    }

    public void setTurn(int i) {this.turn = i;};

    public int getTurn() {return this.turn;}

    public void setGameType(int i) {gameType = i;}

    public int getGameType() {return gameType;}

    public void setSize(int i) {size = i;}

    public int getSize() {return size;}

    public void setShipsSet(boolean i) {shipsSet = i;}

    public boolean isShipsSet() {
        return shipsSet;
    }

    public int[] getShipsNeeded() {
        return shipsNeeded;
    }

    public void setShipsPlaced(int ship) {
        this.shipsPlaced[ship] += 1;
    }

    public boolean checkIfAllShipsOfOneSortArePlaced(int ship) {
        return this.shipsPlaced[ship] == this.shipsNeeded[ship];
    }

    public boolean checkIffAllShipsArePlaced()
    {
        return this.shipsPlaced[0] == this.shipsNeeded[0] &&
                this.shipsPlaced[1] == this.shipsNeeded[1] &&
                this.shipsPlaced[2] == this.shipsNeeded[2] &&
                this.shipsPlaced[3] == this.shipsNeeded[3] &&
                this.shipsPlaced[4] == this.shipsNeeded[4];
    }

    public boolean checkIfHit(Point p)
    {
        for(int i = 0; i < opponentShips.size(); i++)
        {
            if(opponentShips.get(i).getX() == p.getX() && opponentShips.get(i).getY() == p.getY())
            {
                return true;
            }

        }
        return false;
    }

    public boolean checkIfOpponentHit(Point p)
    {
        ArrayList<Point> arr= new ArrayList<>();

        for(int i = 0; i < myShips.getCarrier().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getCarrier().get(i), 6));
        }

        for(int i = 0; i < myShips.getBattleship().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getBattleship().get(i), 5));
        }

        for(int i = 0; i < myShips.getCruiser().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getCruiser().get(i), 4));
        }

        for(int i = 0; i < myShips.getSubmarine().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getSubmarine().get(i), 3));
        }

        for(int i = 0; i < myShips.getDestroyer().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getDestroyer().get(i), 2));
        }

/*
        System.out.println("---------------------");
        for(int j = 0; j< arr.size(); j++)
        {
            System.out.println(arr.get(j).getX() + " " + arr.get(j).getY());
        }

 */

        for(int i = 0; i < arr.size(); i++)
        {
            if(arr.get(i).getX() == p.getX() && arr.get(i).getY() == p.getY())
            {
                return true;
            }

        }
        return false;

    }

    public ArrayList<Point> generateShipCoords(Point p, int length)
    {
        ArrayList<Point> arr = new ArrayList<>();
        for(int i = 0; i < length; i++)
        {
            arr.add(new Point(p.getX() + i, p.getY()));
        }

        return arr;
    }

    public boolean checkIfWon()
    {
        if(opponentShipsHit.size() == opponentShips.size())
        {
            return true;
        }
        return false;
    }

    public boolean checkIfLost()
    {
        ArrayList<Point> arr= new ArrayList<>();

        for(int i = 0; i < myShips.getCarrier().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getCarrier().get(i), 6));
        }

        for(int i = 0; i < myShips.getBattleship().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getBattleship().get(i), 5));
        }

        for(int i = 0; i < myShips.getCruiser().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getCruiser().get(i), 4));
        }

        for(int i = 0; i < myShips.getSubmarine().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getSubmarine().get(i), 3));
        }

        for(int i = 0; i < myShips.getDestroyer().size(); i++)
        {
            arr.addAll(generateShipCoords(myShips.getDestroyer().get(i), 2));
        }

        if(arr.size() == myShipsHit.size())
        {
            return true;
        }
        return false;
    }
}
