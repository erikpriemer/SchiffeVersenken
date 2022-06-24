package DataManagement;

import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable {

    private List<int[]> myShips, opponentShips, myShipsHit, opponentShipsHit;
    private int turn;

    public GameData()
    {
        turn = 0;
        myShips = new ArrayList<>();
        opponentShips = new ArrayList<>();
        myShipsHit = new ArrayList<>();
        opponentShipsHit = new ArrayList<>();
    }


    public void addMyShip(int[] ship)
    {
        myShips.add(ship);
    }

    public void addOpponentShip(int[] ship)
    {
        opponentShips.add(ship);
    }

    public void addMyShipHit(int[] ship)
    {
        myShipsHit.add(ship);
    }

    public void addOpponentShipHit(int[] ship)
    {
        opponentShips.add(ship);
    }

    public List<int[]> getMyShips()
    {
        return this.myShips;
    }

    public List<int[]> getOpponentShips() { return this.opponentShips; }

    public List<int[]> getMyShipsHit()
    {
        return this.myShipsHit;
    }

    public List<int[]> getOpponentShipsHit()
    {
        return this.opponentShipsHit;
    }

    public void setTurn(int i) {this.turn = i;};

    public int getTurn() {return this.turn;}
}
