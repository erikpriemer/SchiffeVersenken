package Netzwerk;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class MeinClient {


    private InetSocketAddress address;

    public MeinClient(String hostname, int port) {

        address = new InetSocketAddress(hostname, port);
    }

    public void sendeNachricht(String Nachricht) {
        try {
            Socket socket = new Socket();
            socket.connect(address, 100);

            PrintWriter PW = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            PW.println(Nachricht);
            PW.flush();

            PW.close();
            socket.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        MeinClient MeinClient = new MeinClient("192.168.178.81", 40000);
        MeinClient.sendeNachricht(" HAllo ");
    }

    }


