package Netzwerk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MeinServer {

    public static void main(String[] args) {
        MeinServer SchiffeVersenken = new MeinServer(50000);
        SchiffeVersenken.RUN();
    }

    private int port= 50000;
    public String value;
    protected Socket s;

    public MeinServer(int port) {
        this.port = port;
        this.value = "";
    }

        public void RUN(){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true) {

                        try {
                            System.out.println("Server: Server starten....");
                            ServerSocket serverSocket = new ServerSocket(port);
                            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            Writer out = new OutputStreamWriter(s.getOutputStream());
                            BufferedReader usr = new BufferedReader(new InputStreamReader(System.in));
                            //new NetzwerkProtokoll() ;
                            while (true) {
                                String line = in.readLine();
                                if (line == null) break;
                                System.out.println("<<< " + line);
                                System.out.print(">>> ");
                                line = usr.readLine();
                                out.write(usr.readLine());
                                if (line == null || line.equals("")) break;
                                out.write(String.format("%s%n", line));
                                out.flush();
                                System.out.println("Server: Warte auf Verbindung...");
                                Socket remoteClientSocket = serverSocket.accept();
                                System.out.println("Server: Client verbunden: " + remoteClientSocket.getRemoteSocketAddress());


                                Scanner s = new Scanner(new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
                                if (s.hasNextLine()) {
                                    System.out.println("Server: Nachricht vom Client: " + s.nextLine());
                                }

                                s.close();
                                remoteClientSocket.close();
                                serverSocket.close();
                            }

                            } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

        }

}
