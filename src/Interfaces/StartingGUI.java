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

        JButton button = new JButton("Online/Client");
        button.addActionListener((e -> {online();}));
        button.setForeground(new Color(24, 109, 158));
        button.setFont(font);
        box.add(button);

        JButton button1 = new JButton("Online/Server");
        button1.addActionListener((e -> {onlineServer();}));
        button1.setForeground(new Color(24, 109, 158));
        button1.setFont(font);
        box.add(button1);

        JButton button2 = new JButton("Offline");
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.addActionListener((e -> {offline();}));
        button2.setForeground(new Color(14, 120, 37));
        button2.setFont(font);
        box.add(button2);

        frame.add(box);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(30));

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

    void online()
    {
        frame.setVisible(false);
        new PrepareOnlineBattleshipGUI();
    }

    void onlineServer()
    {
        frame.setVisible(false);
        new PrepareOnlineBattleshipGUIServer();
    }

    void offline()
    {
        frame.setVisible(false);
        new PrepareBattleshipGUI();
    }
}
