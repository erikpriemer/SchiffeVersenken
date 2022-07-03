package Netzwerk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
                            System.out.println("Server: Warte auf Verbindung...");
                            Socket remoteClientSocket = serverSocket.accept();
                            System.out.println("Server: Client verbunden: " + remoteClientSocket.getRemoteSocketAddress());


                            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
                            if (s.hasNextLine()) {
                                //System.out.println("Server: Nachricht vom Client: " + s.nextLine());
                                value = s.nextLine();
                            }

                            s.close();
                            remoteClientSocket.close();
                            serverSocket.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

        }

}
