package DataManagement;


import DataManagement.ShipList;
import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable {

    private ShipList myShips, opponentShips;
    private List<Point> myShipsHit, opponentShipsHit, myShipsMissed, opponentShipsMissed;
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
        this.opponentShips = new ShipList();  // array Liste mit den gegnerischen Schiffen  Wird für Single-Player verwendet
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

    public void setOpponentShips(ShipList ships)
    {
        opponentShips = ships;
    }



    public void addOpponentCarrier(Point ship)
    {
        opponentShips.addCarrier(ship);
    }

    public void addOpponentBattleship(Point ship)
    {
        opponentShips.addBattleship(ship);
    }

    public void addOpponentCruiser(Point ship)
    {
        opponentShips.addCruiser(ship);
    }

    public void addOpponentSubmarine(Point ship)
    {
        opponentShips.addSubmarine(ship);
    }

    public void addOpponentDestroyer(Point ship)
    {
        opponentShips.addDestroyer(ship);
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

    public ShipList getOpponentShips() { return this.opponentShips; }

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
        for(int i = 0; i < opponentShips.getCarrier().size(); i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(opponentShips.getCarrier().get(i).getX() == p.getX() && opponentShips.getCarrier().get(i).getX() + j == p.getY())
                {
                    return true;
                }
            }
        }

        for(int i = 0; i < opponentShips.getBattleship().size(); i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(opponentShips.getBattleship().get(i).getX() == p.getX() && opponentShips.getBattleship().get(i).getX() + j == p.getY())
                {
                    return true;
                }
            }
        }

        for(int i = 0; i < opponentShips.getCruiser().size(); i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(opponentShips.getCruiser().get(i).getX() == p.getX() && opponentShips.getCruiser().get(i).getX() + j == p.getY())
                {
                    return true;
                }
            }
        }

        for(int i = 0; i < opponentShips.getSubmarine().size(); i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(opponentShips.getSubmarine().get(i).getX() == p.getX() && opponentShips.getSubmarine().get(i).getX() + j == p.getY())
                {
                    return true;
                }
            }
        }

        for(int i = 0; i < opponentShips.getDestroyer().size(); i++)
        {
            for(int j = 0; j < 2; j++)
            {
                if(opponentShips.getDestroyer().get(i).getX() == p.getX() && opponentShips.getDestroyer().get(i).getX() + j == p.getY())
                {
                    return true;
                }
            }
        }
        return false;
    }
}
