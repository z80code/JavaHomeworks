package MyFrame.Server.Transfers;

import java.io.*;
import java.net.Socket;

public class Connecton implements Runnable, Closeable {

    private Socket clientSocket;

    private boolean isConnected = false;

    public Connecton(Socket clientSocket) {
        this.clientSocket = clientSocket;
        isConnected = true;
    }

    @Override
    public void run() {

        System.out.println(" Клиент: " + clientSocket.getInetAddress()
                + ": " + clientSocket.getPort() + " подключился.");

        while (true) {
           if(!isConnected) break;
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                String s = reader.readLine();
                System.out.println(" Клиент " + clientSocket.getInetAddress() + ": " + s);

            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Соединение с клиентом"+ clientSocket.getInetAddress()+" потеряно.");
                isConnected = false;
                try {
                    this.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("Ошибка закрытия соединения.");
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        clientSocket.close();
    }
}
