package Interfaces;
import DataManagement.GameData;
import DataManagement.Point;
import DataManagement.ShipList;
import KI.KI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class BattleshipGUI extends JFrame
{
    // Spielerfeld und gegnerisches Feld bestehend aus JButtons
    JButton[][] myBoard;
    JButton[][] opponentBoard;
    int fieldSize;
    GameData gameData;
    Point myFieldClicked;
    Point opponentFieldClicked;
    JFrame battleshipFrame;
    KI ki;

    public BattleshipGUI(int size, int[] quantityOfShips, int gameType) {

        // Daten in GameData schreiben
        myFieldClicked = new Point(0, 0);
        opponentFieldClicked = new Point(0, 0);
        this.gameData = new GameData(quantityOfShips);
        gameData.setGameType(gameType);
        gameData.setSize(size);

        // Feldgröße wird mit der Variable size initialisiert
        this.fieldSize = size;

        // KI starten
        if(gameData.getGameType() == 1)
        {
            this.ki = new KI(gameData.getSize());
            gameData.setOpponentShips(ki.generateShips(quantityOfShips[0], quantityOfShips[1], quantityOfShips[2], quantityOfShips[3], quantityOfShips[4]));
            gameData.setOpponentShipsList(ki.getShipList());
        }


        // Spielerfeld und gegnerisches Feld sind fieldSize x fieldSize groß
        myBoard = new JButton[fieldSize][fieldSize];
        opponentBoard = new JButton[fieldSize][fieldSize];

        // Hier werden alle JPanels für das JFrame erstellt
        JPanel boardPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        // Größe der JPanels anpassen
        boardPanel.setPreferredSize(new Dimension(100, 100));
        bottomPanel.setPreferredSize(new Dimension(100, 350));
        leftPanel.setPreferredSize(new Dimension(200, 100));
        rightPanel.setPreferredSize(new Dimension(200, 100));

        // Hintergrund der JPanels setzen
        boardPanel.setBackground(new Color(210, 210, 210));
        bottomPanel.setBackground(new Color(210, 210, 210));
        leftPanel.setBackground(new Color(224, 224, 224));
        rightPanel.setBackground(new Color(224, 224, 224));

        // Layouts der Panels bestimmen
        boardPanel.setLayout(new GridLayout());
        leftPanel.setLayout(new GridBagLayout());
        bottomPanel.setLayout(new BorderLayout());


        // Das 2D Spielerfeld erstellen
        JPanel board1 = new JPanel(new GridLayout(fieldSize, fieldSize));
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                //The background of the buttons is darkblue and the border is black
                myBoard[i][j] = new JButton();
                myBoard[i][j].setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
                myBoard[i][j].setOpaque(true);
                int finalI = i;
                int finalJ = j;
                myBoard[i][j].addActionListener(e -> {myFieldClicked = new Point(finalJ, finalI);});
                setImageIcon("Resources/water.png", myBoard[i][j], calcSize(), calcSize());
                board1.add(myBoard[i][j]);
            }
        }
        // Grüne Umrandung hinzufügen
        Border border = board1.getBorder();
        Border margin = new LineBorder(new Color(77, 173, 91),10);
        board1.setBorder(new CompoundBorder(border, margin));
        boardPanel.add(board1, BorderLayout.SOUTH);


        // Gegnerisches Spielfeld erstellen
        JPanel board2 = new JPanel(new GridLayout(fieldSize, fieldSize));
        for(int i = 0; i < fieldSize; i++) {
            for(int j = 0; j < fieldSize; j++) {
                //The background of the buttons is darkblue and the border is black
                opponentBoard[i][j] = new JButton();
                opponentBoard[i][j].setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
                opponentBoard[i][j].setOpaque(true);
                int finalI = i;
                int finalJ = j;
                opponentBoard[i][j].addActionListener(e -> {opponentFieldClicked = new Point(finalI, finalJ);});
                setImageIcon("Resources/water.png", opponentBoard[i][j], calcSize(), calcSize());
                board2.add(opponentBoard[i][j]);
            }
        }
        // Rote Umrandung erstellen
        Border border2 = board2.getBorder();
        Border margin2 = new LineBorder(new Color(210, 91, 91),10);
        board2.setBorder(new CompoundBorder(border2, margin2));
        boardPanel.add(board2, BorderLayout.NORTH);



        // Den shootButton erstellen und in das linke Panel packen
        JButton shootButton = new JButton("Shoot");
        shootButton.setPreferredSize(new Dimension(100, 50));
        shootButton.setForeground(Color.gray);
        shootButton.addActionListener(e -> {
            try {
                shoot();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(shootButton, gbc);


        // Verschachtelung des bottomPanels erstellen, sodass die Buttons für die Schiffe nicht direkt unter dem Spielfeld liegen
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setPreferredSize(new Dimension(100, 300));
        buttonPanel2.setBackground(new Color(210, 210, 210));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.fill = GridBagConstraints.VERTICAL;

        //Beschreibung zum bottomPanel hinzufügen
        JLabel shipText = new JLabel("Add ships to Battlefield!");
        bottomPanel.add(shipText, BorderLayout.CENTER);


        // Schiffe zum buttonPanel2 hinzufügen
        addCarrierButton(buttonPanel2,gbc2);
        addBattleshipButton(buttonPanel2,gbc2);
        addCruiserButton(buttonPanel2,gbc2);
        addSubmarineButton(buttonPanel2,gbc2);
        addDestroyerButton(buttonPanel2,gbc2);


        // Das buttonPanel2 zum bottomPanel hinzufügen
        bottomPanel.add(buttonPanel2, BorderLayout.SOUTH);


        //JMenu bar erstellen
        JMenuBar bar = new JMenuBar();
        {
            //Menu (enthällt Save Game und Exit)
            JMenu menuSettings = new JMenu("Menu");
            {
                //Save Game
                JMenuItem menuItemSaveGame = new JMenuItem("Save Game");
                menuItemSaveGame.addActionListener(
                        e -> {System.out.println("Save Game");}
                );
                menuSettings.add(menuItemSaveGame);
                menuSettings.addSeparator();

                //Exit
                JMenuItem menuItemExit = new JMenuItem("Exit");
                menuItemExit.addActionListener(
                        e -> {System.exit(0);}
                );
                menuSettings.add(menuItemExit);
            }
            bar.add(menuSettings);


            //Game (enthällt Online Game, Offline Game und Load Game)
            JMenu gameSetting = new JMenu("Game");
            {
                //Load Game
                JMenuItem menuItemLoadGame = new JMenuItem("Load Game");
                menuItemLoadGame.addActionListener(
                        (e -> {
                            System.out.println("Load Game");
                        })
                );
                gameSetting.add(menuItemLoadGame);
            }
            bar.add(gameSetting);
        }
        //Größe des JMenu ändern
        bar.setPreferredSize(new Dimension(10, 30));



        // JFrame erstellen
        battleshipFrame = new JFrame("Battleship");
        battleshipFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Verwendet Border Layout
        battleshipFrame.setLayout(new BorderLayout());
        battleshipFrame.setSize(1500, 1500);
        battleshipFrame.setVisible(true);
        battleshipFrame.setResizable(true);

        //alle Panels zum JFrame hinzufügen
        battleshipFrame.add(bar, BorderLayout.NORTH);
        battleshipFrame.add(bottomPanel, BorderLayout.SOUTH);
        battleshipFrame.add(boardPanel, BorderLayout.CENTER);
        battleshipFrame.add(leftPanel, BorderLayout.WEST);
        battleshipFrame.add(rightPanel, BorderLayout.EAST);


        if(gameData.getGameType() == 4 || gameData.getGameType() == 5)
        {
            this.ki = new KI(gameData.getSize());
            gameData.setOpponentShips(ki.generateShips(quantityOfShips[0], quantityOfShips[1], quantityOfShips[2], quantityOfShips[3], quantityOfShips[4]));
            gameData.setOpponentShipsList(ki.getShipList());
            ShipList s = ki.getShipList();

            for(int i = 0; i < s.getCarrier().size(); i++)
            {
                addCarrier(s.getCarrier().get(i).getX(), s.getCarrier().get(i).getY());
            }

            for(int i = 0; i < s.getBattleship().size(); i++)
            {
                addBattleship(s.getBattleship().get(i).getX(), s.getBattleship().get(i).getY());
            }

            for(int i = 0; i < s.getCruiser().size(); i++)
            {
                addCruiser(s.getCruiser().get(i).getX(), s.getCruiser().get(i).getY());
            }

            for(int i = 0; i < s.getSubmarine().size(); i++)
            {
                addSubmarine(s.getSubmarine().get(i).getX(), s.getSubmarine().get(i).getY());
            }

            for(int i = 0; i < s.getDestroyer().size(); i++)
            {
                addDestroyer(s.getDestroyer().get(i).getX(), s.getDestroyer().get(i).getY());
            }
        }
    }

    //Ab hier werden alle Buttons für die Schiffe erstellt-----------------------------------------------
    // Bei jedem Button wird die größe und das Image hinzugefügt
    private void addCarrierButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(300, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship6.png", ship1, 320, 70);
        ship1.addActionListener(e -> {addCarrier(myFieldClicked.getX(), myFieldClicked.getY());});
    }

    private void addBattleshipButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(250, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship5.png", ship1, 270, 70);
        ship1.addActionListener(e -> {addBattleship(myFieldClicked.getX(), myFieldClicked.getY());});

    }

    private void addCruiserButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(200, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship4.png", ship1, 220, 70);
        ship1.addActionListener(e -> {addCruiser(myFieldClicked.getX(), myFieldClicked.getY());});

    }

    private void addSubmarineButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(150, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship3.png", ship1, 170, 70);
        ship1.addActionListener(e -> {addSubmarine(myFieldClicked.getX(), myFieldClicked.getY());});

    }

    private void addDestroyerButton(JPanel buttonPanel, GridBagConstraints gbc2)
    {
        JButton ship1 = new JButton();
        ship1.setPreferredSize(new Dimension(100, 50));
        ship1.setBorder(createBorder(Color.BLACK, 5, 15, 5, 15));
        ship1.setOpaque(true);
        buttonPanel.add(ship1, gbc2);
        setImageIcon("Resources/fullship2.png", ship1, 120, 70);
        ship1.addActionListener(e -> {addDestroyer(myFieldClicked.getX(), myFieldClicked.getY());});

    }



    // Ab hier sind die Funktionen zum Verändern des Spielfelds -----------------------------------------
    // Wenn das Spieler-Schiff getroffen wird
    private void colorHitMyShip(int i, int j)
    {
        setImageIcon("Resources/hitIcon.png", myBoard[j][i], calcSize(), calcSize());
        myBoard[j][i].setBackground(new Color(165, 25, 25));
    }

    // Wenn das Spieler-Schiff verfehlt wird
    private void colorMissMyShip(int i, int j)
    {
        setImageIcon("Resources/miss.png", myBoard[j][i], calcSize(), calcSize());
        myBoard[j][i].setBackground(Color.RED);
    }

    // Wenn das Gegner-Schiff getroffen wird
    private void colorHitOpponentShip(int j, int i)
    {
        setImageIcon("Resources/hitOpponent.png", opponentBoard[j][i], calcSize(), calcSize());
        opponentBoard[j][i].setBackground(new Color(165, 25, 25));
    }

    // Wenn das Gegner-Schiff verfehlt wird
    private void colorMissOpponentShip(int j, int i)
    {
        setImageIcon("Resources/miss.png", opponentBoard[j][i], calcSize(), calcSize());
        opponentBoard[j][i].setBackground(Color.RED);
    }



    // Funktionen zum erstellen der Schiffe auf dem Spielerfeld--------------------------------------------
    private void addCarrier(int start, int row)
    {
        if(!gameData.checkIfAllShipsOfOneSortArePlaced(0) && start + 5 < fieldSize)
        {
            setImageIcon("Resources/ship6/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
            setImageIcon("Resources/ship6/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
            setImageIcon("Resources/ship6/row-1-column-3.png", myBoard[row][start+2], calcSize(), calcSize());
            setImageIcon("Resources/ship6/row-1-column-4.png", myBoard[row][start+3], calcSize(), calcSize());
            setImageIcon("Resources/ship6/row-1-column-5.png", myBoard[row][start+4], calcSize(), calcSize());
            setImageIcon("Resources/ship6/row-1-column-6.png", myBoard[row][start+5], calcSize(), calcSize());
            gameData.addMyCarrier(new Point(start, row));
            gameData.setShipsPlaced(0);
        }
        else
        {
            if(start + 5 >= fieldSize)
            {
                JOptionPane.showMessageDialog(battleshipFrame, "You cant set your ship like this!");
                return;
            }
            if(gameData.checkIffAllShipsArePlaced())
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships are set!");

            }
            else
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships of this kind are set!");

            }        }
    }

    private void addBattleship(int start, int row)
    {
        if(!gameData.checkIfAllShipsOfOneSortArePlaced(1) && start + 4 < fieldSize)
        {
            setImageIcon("Resources/ship5/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
            setImageIcon("Resources/ship5/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
            setImageIcon("Resources/ship5/row-1-column-3.png", myBoard[row][start+2], calcSize(), calcSize());
            setImageIcon("Resources/ship5/row-1-column-4.png", myBoard[row][start+3], calcSize(), calcSize());
            setImageIcon("Resources/ship5/row-1-column-5.png", myBoard[row][start+4], calcSize(), calcSize());
            gameData.addMyBattleship(new Point(start, row));
            gameData.setShipsPlaced(1);
        }
        else
        {
            if(start + 4 >= fieldSize)
            {
                JOptionPane.showMessageDialog(battleshipFrame, "You cant set your ship like this!");
                return;
            }
            if(gameData.checkIffAllShipsArePlaced())
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships are set!");

            }
            else
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships of this kind are set!");

            }        }
    }

    private void addCruiser(int start, int row)
    {
        if(!gameData.checkIfAllShipsOfOneSortArePlaced(2) && start + 3 < fieldSize)
        {
            setImageIcon("Resources/ship4/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
            setImageIcon("Resources/ship4/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
            setImageIcon("Resources/ship4/row-1-column-3.png", myBoard[row][start+2], calcSize(), calcSize());
            setImageIcon("Resources/ship4/row-1-column-4.png", myBoard[row][start+3], calcSize(), calcSize());
            gameData.addMyCruiser(new Point(start, row));
            gameData.setShipsPlaced(2);
        }
        else
        {
            if(start + 3 >= fieldSize)
            {
                JOptionPane.showMessageDialog(battleshipFrame, "You cant set your ship like this!");
                return;
            }
            if(gameData.checkIffAllShipsArePlaced())
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships are set!");
            }
            else
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships of this kind are set!");

            }        }
    }

    private void addSubmarine(int start, int row)
    {
        if(!gameData.checkIfAllShipsOfOneSortArePlaced(3) && start + 2 < fieldSize)
        {
            setImageIcon("Resources/ship3/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
            setImageIcon("Resources/ship3/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
            setImageIcon("Resources/ship3/row-1-column-3.png", myBoard[row][start+2], calcSize(), calcSize());
            gameData.addMySubmarine(new Point(start, row));
            gameData.setShipsPlaced(3);
        }
        else
        {
            if(start + 2 >= fieldSize)
            {
                JOptionPane.showMessageDialog(battleshipFrame, "You cant set your ship like this!");
                return;
            }
            if(gameData.checkIffAllShipsArePlaced())
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships are set!");

            }
            else
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships of this kind are set!");

            }        }
    }

    private void addDestroyer(int start, int row)
    {
        if(!gameData.checkIfAllShipsOfOneSortArePlaced(4) && start + 1 < fieldSize)
        {
            setImageIcon("Resources/ship2/row-1-column-1.png", myBoard[row][start], calcSize(), calcSize());
            setImageIcon("Resources/ship2/row-1-column-2.png", myBoard[row][start+1], calcSize(), calcSize());
            gameData.addMyDestroyer(new Point(start, row));
            gameData.setShipsPlaced(4);
        }
        else
        {
            if(start + 1 >= fieldSize)
            {
                JOptionPane.showMessageDialog(battleshipFrame, "You cant set your ship like this!");
                return;
            }
            if(gameData.checkIffAllShipsArePlaced())
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships are set!");

            }
            else
            {
                JOptionPane.showMessageDialog(battleshipFrame, "All Ships of this kind are set!");

            }
        }
    }




    //Hilfsfunktionen----------------------------------------------------------------------------------------
    //Funktion, die die Umrandung eines JButtons erstellt
    public Border createBorder(Color color, int top, int left, int bottom, int right)
    {
        Border line = new LineBorder(color);
        Border margin = new EmptyBorder(top, left, bottom, right);
        return new CompoundBorder(line, margin);
    }

    //Funktion, die das ImageIcon zum Schiff hinzufügt
    public void setImageIcon(String source, JButton button, int width, int height)
    {
        try {
            Image img = ImageIO.read(getClass().getResource(source));
            Image dimg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            button.setIcon(new ImageIcon(dimg));
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    // Funktion, die die Größe der JButtons auf dem Spielfeld berechnet im Verhältnis zur größe des Spielfelds
    private int calcSize()
    {
        return 600/fieldSize;
    }


    // Falls Schießen gedrückt wird
    public void shoot() throws InterruptedException {
        // Falls alle Schiffe gesetzt wurden
        if(gameData.checkIffAllShipsArePlaced())
        {

            // Falls ich getroffen habe
            if(gameData.checkIfHit(opponentFieldClicked))
            {
                gameData.addOpponentShipHit(opponentFieldClicked);  // Schiff zu hits hinzufügen
                colorHitOpponentShip(opponentFieldClicked.getX(), opponentFieldClicked.getY());
                if(gameData.checkIfWon())  // Schauen ob ich gewonnen habe
                {
                    JOptionPane.showMessageDialog(battleshipFrame, "Congrats you won!");
                }
            }
            else  // Falls ich nicht getroffen habe
            {
                gameData.addOpponentShipsMissed(opponentFieldClicked);
                colorMissOpponentShip(opponentFieldClicked.getX(), opponentFieldClicked.getY());
            }


            // Falls es ein Singleplayer vs KI game ist
            if(gameData.getGameType() == 1 && !gameData.checkIfHit(opponentFieldClicked))
            {
                boolean hit = false;
                while(true)
                {
                    Point p = this.ki.shootHard(gameData.getMyShipsHit(), gameData.getMyShipsMissed(), hit);
                    if(gameData.checkIfOpponentHit(p))  // falls die KI getroffen hat
                    {
                        hit = true;
                        colorHitMyShip(p.getX(), p.getY());
                        gameData.addMyShipHit(p);
                        if(gameData.checkIfLost())  // überprüfe, ob KI gewonnen hat
                        {
                            JOptionPane.showMessageDialog(battleshipFrame, "You lost!");
                        }
                    }
                    else  // Falls sie nicht getroffen hat
                    {
                        hit = false;
                        //ki.helper.inProgress = false;
                        colorMissMyShip(p.getX(), p.getY());
                        gameData.addMyShipsMissed(p);
                        break;
                    }
                    //TimeUnit.SECONDS.sleep(2);
                }
            }
        }
        else  // Falls nicht alle Schiffe gesetzt wurden
        {
            JOptionPane.showMessageDialog(battleshipFrame, "Not all ships are set yet!");
        }
    }
}
