package MyFrame.Server;

import MyFrame.Server.Transfers.Connecton;
import jdk.nashorn.internal.ir.WhileNode;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class StartServer implements Closeable {

     static ServerSocket server;

    private static List<Thread> connections = new ArrayList<>();

    public static void main(String[] args) throws IOException {

//        try {
            server = new ServerSocket(8080);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Ошибка создания сервера.");
//        }
        System.out.println("Ожидание соединения ...");

        while(true){

            Socket connected = server.accept();
            Thread newClient = new Thread(() -> {
                {
                    Connecton newConnect = new Connecton(connected);
                    newConnect.run();
                }
            });
            connections.add(newClient);
            newClient.start();
        }
    }

    @Override
    public void close() throws IOException {
      if (server!=null) server.close();
        for (Thread tr: connections) {
            tr.stop();
        }
    }
}
