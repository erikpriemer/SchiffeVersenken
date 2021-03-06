package Interfaces;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PrepareBattleshipGUI {

    // frame
    static JFrame f;

    static JButton b;

    // slider
    static JSlider fieldSize;
    static JSlider carrier;
    static JSlider battleship;
    static JSlider cruiser;
    static JSlider submarine;
    static JSlider destroyer;

    // label
    static JLabel lfieldSize;
    static JLabel lcarrier;
    static JLabel lbattleship;
    static JLabel lcruiser;
    static JLabel lsubmarine;
    static JLabel ldestroyer;

    public PrepareBattleshipGUI()
    {
        // create a new frame
        f = new JFrame("Menü");

        // create label
        lfieldSize = new JLabel();
        lcarrier = new JLabel();
        lbattleship = new JLabel();
        lcruiser = new JLabel();
        lsubmarine = new JLabel();
        ldestroyer = new JLabel();

        // create a panel
        JPanel p = new JPanel();

        // create button

        b = new JButton("Enter");
        b.addActionListener(e -> {
            start();
        });

        // create a slider
        fieldSize = new JSlider(5, 30, 15);
        carrier = new JSlider(0, 0, 0);
        battleship = new JSlider(0, 13, 5);
        cruiser = new JSlider(0, 13, 5);
        submarine = new JSlider(1, 13, 5);
        destroyer = new JSlider(0, 12, 5);

        // paint the ticks and tracks
        fieldSize.setPaintTrack(true);
        fieldSize.setPaintTicks(true);
        fieldSize.setPaintLabels(true);

        carrier.setPaintTrack(true);
        carrier.setPaintTicks(true);
        carrier.setPaintLabels(true);

        battleship.setPaintTrack(true);
        battleship.setPaintTicks(true);
        battleship.setPaintLabels(true);

        cruiser.setPaintTrack(true);
        cruiser.setPaintTicks(true);
        cruiser.setPaintLabels(true);

        submarine.setPaintTrack(true);
        submarine.setPaintTicks(true);
        submarine.setPaintLabels(true);

        destroyer.setPaintTrack(true);
        destroyer.setPaintTicks(true);
        destroyer.setPaintLabels(true);


        // set spacing
        fieldSize.setMajorTickSpacing(4);
        fieldSize.setMinorTickSpacing(1);

        carrier.setMajorTickSpacing(2);
        carrier.setMinorTickSpacing(1);

        battleship.setMajorTickSpacing(2);
        battleship.setMinorTickSpacing(1);

        cruiser.setMajorTickSpacing(2);
        cruiser.setMinorTickSpacing(1);

        submarine.setMajorTickSpacing(2);
        submarine.setMinorTickSpacing(1);

        destroyer.setMajorTickSpacing(2);
        destroyer.setMinorTickSpacing(1);

        // setChangeListener
        ChangeListener s = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(fieldSize.getValue() < 20)
                {
                    carrier.setMaximum(0);
                    carrier.setMinimum(0);
                    destroyer.setMaximum(11);
                    destroyer.setMinimum(1);
                }
                if(fieldSize.getValue() >= 20)
                {
                    carrier.setMinimum(1);
                    carrier.setMaximum(13);
                    destroyer.setMaximum(0);
                    destroyer.setMinimum(0);
                }
            }
        };
        fieldSize.addChangeListener(s);



        // add slider/buttons/fields to panel

        p.add(fieldSize);
        p.add(lfieldSize);

        p.add(carrier);
        p.add(lcarrier);

        p.add(battleship);
        p.add(lbattleship);

        p.add(cruiser);
        p.add(lcruiser);

        p.add(submarine);
        p.add(lsubmarine);

        p.add(destroyer);
        p.add(ldestroyer);

        p.add(b);


        f.add(p);

        // set the text of label
        lfieldSize.setText("Feldgröße");
        lcarrier.setText("Carrier    ");
        lbattleship.setText("Battleship");
        lcruiser.setText("Cruiser    ");
        lsubmarine.setText("Submarine");
        ldestroyer.setText("Destroyer");

        // set the size of frame
        f.setSize(300, 420);
        f.setLocation(500,190);

        f.setVisible(true);

    }

    void start()
    {
        int ships = carrier.getValue()*6 + battleship.getValue()*5 + cruiser.getValue()*4 +
                submarine.getValue()*3 + destroyer.getValue()*2;
        int field = fieldSize.getValue() * fieldSize.getValue();
        if((field / ships)*10 > 30)
        {
            JOptionPane.showMessageDialog(f, "You need more ships!");
            return;
        }

        if((field / ships)*10 < 25)
        {
            JOptionPane.showMessageDialog(f, "You have to many ships!");
            return;
        }

        f.setVisible(false);
        int[] s = {carrier.getValue(), battleship.getValue(), cruiser.getValue(), submarine.getValue(), destroyer.getValue()};
        new BattleshipGUI(fieldSize.getValue(), s, 1);


    }
}
