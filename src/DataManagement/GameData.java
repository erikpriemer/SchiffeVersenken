package DataManagement;


import DataManagement.ShipList;
import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable {

    private ShipList myShips, opponentShips;
    private List<Point> myShipsHit, opponentShipsHit, myShipsMissed, opponentShipsMissed;
    private int turn, gameType, size;

    public GameData()
    {
        turn = 0;  // 1 = my Turn, 2 = opponents Turn,  0 = not set jet
        gameType = 0;  // 1 = offline game, 2 = online game as server, 3 = online game as client, 0 = not set jet
        size = 0; // größe des Spielfelds
        myShips = new ShipList();  // array Liste mit meinen Schiffen
        opponentShips = new ShipList();  // array Liste mit den gegnerischen Schiffen  Wird für Single-Player verwendet
        myShipsHit = new ArrayList<>();  // array Liste mit meinen getroffenen Schiffen
        opponentShipsHit = new ArrayList<>();  // array Liste mit den Schiffen, die ich getroffen habe
        myShipsMissed = new ArrayList<>();  // array Liste mit meinen verfehlten Schiffen
        opponentShipsMissed = new ArrayList<>();  // array Liste mit den Schiffen, die ich verfehlt habe
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

}
