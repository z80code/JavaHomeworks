package MyFrame.Server;

import MyFrame.Server.Transfers.Connecton;
import MyFrame.Server.Transfers.Listener;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class StartServer implements Closeable {

    static ServerSocket server;

    private static List<Connecton> connections = new ArrayList<>();

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

            Connecton newClient = new Connecton(connected);

            newClient.addListener(new Listener() {
                @Override
                public void onDataReceived(String message) {
                    sendToAll(message);
                }
            });
            newClient.start();
            connections.add(newClient);
        }
    }

    public static void sendToAll(String message) {
        for (Connecton client : connections) {
            client.send(message);

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
