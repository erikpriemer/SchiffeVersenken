package Interfaces;
import javax.swing.*;
import java.awt.*;

public class StartingGUI
{
    static JFrame frame;
    static Font font;
    static Font font2;

    static JLabel lOnline;

    static JButton client;
    static JButton server;
    static JButton clientComputer;
    static JButton serverComputer;
    static JButton offlineButton;
    static JButton loadGameButton;
    static JButton quitButton;

    static Box online;
    static Box onlineComputer;
    static Box offline;
    static Box loadGame;
    static Box quit;

    public StartingGUI()
    {
        font = new Font("Times", Font.PLAIN, 25);
        font2 = new Font("Times", Font.PLAIN, 20);

        frame = new JFrame("Schiffe Versenken");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(Box.createVerticalBox());
        frame.add(Box.createGlue());
        frame.setPreferredSize(new Dimension(600, 400));

        online = Box.createHorizontalBox();
        online.setAlignmentX(Component.CENTER_ALIGNMENT);

        lOnline = new JLabel();
        lOnline.setText("Online:                         ");
        online.add(lOnline);

        client = new JButton("Client");
        client.addActionListener((e -> {onlineClient();}));
        client.setForeground(new Color(24, 109, 158));
        client.setFont(font);
        online.add(client);

        server = new JButton("Server");
        server.addActionListener((e -> {onlineServer();}));
        server.setForeground(new Color(24, 109, 158));
        server.setFont(font);
        online.add(server);


        frame.add(online);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(30));

        onlineComputer = Box.createHorizontalBox();
        onlineComputer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel l2 = new JLabel();
        l2.setText("Online with Computer:");
        onlineComputer.add(l2);

        clientComputer = new JButton("Client");
        clientComputer.addActionListener((e -> {onlineClientKI();}));
        clientComputer.setForeground(new Color(158, 113, 24));
        clientComputer.setFont(font);
        onlineComputer.add(clientComputer);

        serverComputer = new JButton("Server");
        serverComputer.addActionListener((e -> {onlineServerKI();}));
        serverComputer.setForeground(new Color(158, 113, 24));
        serverComputer.setFont(font);
        onlineComputer.add(serverComputer);

        frame.add(onlineComputer);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(10));

        offline = Box.createHorizontalBox();
        offline.setAlignmentX(Component.CENTER_ALIGNMENT);

        offlineButton = new JButton("Offline");
        offlineButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        offlineButton.addActionListener((e -> {offline();}));
        offlineButton.setForeground(new Color(14, 53, 120));
        offlineButton.setFont(font);
        offline.add(offlineButton);

        frame.add(offline);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(10));

        loadGame = Box.createHorizontalBox();
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        loadGameButton = new JButton("Load Game");
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGameButton.addActionListener((e -> {loadGame();}));
        loadGameButton.setForeground(new Color(14, 120, 37));
        loadGameButton.setFont(font);
        loadGame.add(loadGameButton);

        frame.add(loadGame);
        frame.add(Box.createGlue());
        frame.add(Box.createVerticalStrut(10));


        quit = Box.createHorizontalBox();
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);

        quitButton = new JButton("Quit");
        quitButton.addActionListener((e -> {System.exit(0);}));
        quitButton.setForeground(new Color(168, 30, 30));
        quitButton.setFont(font2);
        quit.add(quitButton);

        frame.add(quit);
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

    void loadGame()
    {
        frame.setVisible(false);
        new LoadGame();
    }
}
