package DataManagement;

import java.util.*;

public class GameData {

    private List<int[]> myShips, opponentShips, myShipsHit, opponentShipsHit;

    public GameData()
    {

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

    public List<int[]> getOpponentShips()
    {
        return this.opponentShips;
    }

    public List<int[]> getMyShipsHit()
    {
        return this.myShipsHit;
    }

    public List<int[]> getOpponentShipsHit()
    {
        return this.opponentShipsHit;
    }
}
