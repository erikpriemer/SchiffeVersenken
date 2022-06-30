package Interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartingGUI
{
    static JFrame frame;

    public StartingGUI()
    {
        Font font = new Font("Times", Font.PLAIN, 25);
        Font font2 = new Font("Times", Font.PLAIN, 20);

        frame = new JFrame("Schiffe Versenken");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(Box.createVerticalBox());
        frame.add(Box.createGlue());
        frame.setPreferredSize(new Dimension(600, 400));

        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel l1 = new JLabel();
        l1.setText("Online:                         ");
        box.add(l1);

        JButton button = new JButton("Client");
        button.addActionListener((e -> {onlineClient();}));
        button.setForeground(new Color(24, 109, 158));
        button.setFont(font);
        box.add(button);

        JButton button1 = new JButton("Server");
        button1.addActionListener((e -> {onlineServer();}));
        button1.setForeground(new Color(24, 109, 158));
        button1.setFont(font);
        box.add(button1);


        frame.add(box);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(30));

        Box box1 = Box.createHorizontalBox();
        box.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel l2 = new JLabel();
        l2.setText("Online with Computer:");
        box1.add(l2);

        JButton button4 = new JButton("Client");
        button4.addActionListener((e -> {onlineClientKI();}));
        button4.setForeground(new Color(158, 113, 24));
        button4.setFont(font);
        box1.add(button4);

        JButton button5 = new JButton("Server");
        button5.addActionListener((e -> {onlineServerKI();}));
        button5.setForeground(new Color(158, 113, 24));
        button5.setFont(font);
        box1.add(button5);

        frame.add(box1);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(10));

        Box box3 = Box.createHorizontalBox();
        box.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button2 = new JButton("Offline");
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.addActionListener((e -> {offline();}));
        button2.setForeground(new Color(14, 120, 37));
        button2.setFont(font);
        box3.add(button2);

        frame.add(box3);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(10));


        Box box2 = Box.createHorizontalBox();
        box.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button3 = new JButton("Quit");
        button3.addActionListener((e -> {System.exit(0);}));
        button3.setForeground(new Color(168, 30, 30));
        button3.setFont(font2);
        box2.add(button3);

        frame.add(box2);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(10));

        frame.setLocation(500,190);
        frame.pack();
        frame.setVisible(true);
    }

    void onlineClient()
    {
        frame.setVisible(false);
        new PrepareOnlineBattleshipGUI(false);
    }

    void onlineServer()
    {
        frame.setVisible(false);
        new PrepareOnlineBattleshipGUIServer(false);
    }

    void onlineClientKI()
    {
        frame.setVisible(false);
        new PrepareOnlineBattleshipGUI(true);
    }

    void onlineServerKI()
    {
        frame.setVisible(false);
        new PrepareOnlineBattleshipGUIServer(true);
    }

    void offline()
    {
        frame.setVisible(false);
        new PrepareBattleshipGUI();
    }
}
