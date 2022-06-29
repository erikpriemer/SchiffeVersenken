package Interfaces;

import Netzwerk.MeinServer;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class PrepareOnlineBattleshipGUIServer {

    JTextField port;
    JButton b;
    JFrame f;
    JLabel lport;


    public PrepareOnlineBattleshipGUIServer()
    {
        f = new JFrame("Menü");
        JPanel p = new JPanel();
        lport = new JLabel();
        lport.setText("Port(6666, 6667)");

        port = new JTextField();
        port.setPreferredSize(new Dimension(150, 25));

        b = new JButton("Enter");
        b.addActionListener(e -> {
            try {
                enter();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        p.add(lport);
        p.add(port);
        p.add(b);
        f.add(p);

        // set the size of frame
        f.setSize(300, 200);
        f.setLocation(500,190);

        f.setVisible(true);
    }

    void enter() throws InterruptedException {
        MeinServer meinServer = new MeinServer(Integer.parseInt(port.getText()));
        meinServer.RUN();
        int fieldsize;
        String ships[];

        int carrier = 0, battleship = 0, cruiser = 0, submarine = 0, destroyer = 0;
        while (true)
        {
            if(meinServer.value != "")
            {
                System.out.println("test");
                fieldsize = Integer.parseInt(meinServer.value.split(" ")[1]);
                meinServer.value = "";
                break;
            }
            TimeUnit.SECONDS.sleep(2);
        }
        while (true)
        {
            if(meinServer.value != "")
            {
                System.out.println("test");
                ships = meinServer.value.split(" ");
                for(int i = 1; i < ships.length; i++)
                {
                    if(ships[i] == "6")
                    {
                        carrier += 1;
                    }
                    if(ships[i] == "5")
                    {
                        battleship += 1;
                    }
                    if(ships[i] == "4")
                    {
                        cruiser += 1;
                    }
                    if(ships[i] == "3")
                    {
                        submarine += 1;
                    }
                    if(ships[i] == "2")
                    {
                        destroyer += 1;
                    }

                }
                meinServer.value = "";
                break;
            }

            TimeUnit.SECONDS.sleep(2);
        }
        int[] t = {carrier, battleship, cruiser, submarine, destroyer};
        BattleshipGUI battleshipGUI = new BattleshipGUI(fieldsize, t, 2);

    }
}
