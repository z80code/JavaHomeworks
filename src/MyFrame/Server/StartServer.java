package MyFrame.Server;

import MyFrame.Packet;
import MyFrame.Server.Transfers.Connection;
import MyFrame.Server.Transfers.Listener;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class StartServer implements Closeable {

    static ServerSocket server;

    private static int userID = 0;

    private static List<Connection> connections = new ArrayList<>();

    public static void main(String[] args) throws IOException {

//        try {
        server = new ServerSocket(8080);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Ошибка создания сервера.");
//        }
        System.out.println("Ожидание соединения ...");

        while (true) {

            Socket connected = server.accept();

            Connection newClient = new Connection(connected, ++userID);
            newClient.send(new Packet(0, userID, "Сервер: ", new ArrayList<String>() {
                {
                    this.add("Добро пожаловать!");
                }
            }));
            newClient.addListener(new Listener() {
                @Override
                public void onDataReceived(Packet message) {
                    sendToAll(message);
                }
            });
            newClient.start();
            connections.add(newClient);
        }
    }

    public static void sendToAll(Packet message) {
        for (Connection client : connections) {
            try {
                if (client.userID != message.ID) {
                    client.send(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void close() throws IOException {
        if (server != null) server.close();
        for (Thread tr : connections) {
            tr.stop();
        }
    }
}
