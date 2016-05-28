package MyFrame.Client;

import MyFrame.Client.ClientLogics.UICreator;
import MyFrame.Client.ClientTransfer.Connection;
import MyFrame.Client.ClientTransfer.Listener;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;

public class Appframe extends JFrame implements Listener {

    private Socket socket;
    public Connection connection;

    private Thread inner;

    public Appframe() {
        /**Конструктор */
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);

        UICreator UI = new UICreator(this);

        try {
            UI.initUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /**
         * Добавляем отправителя
         */
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 8080));
            connection = new Connection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Запускаем слушателя.
         */
        inner = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        inner.start();
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            //if (inner!=null)
            System.exit(0);
        }
    }

    @Override
    public void onDataReceived() {

        ArrayList<String> ComInner = (ArrayList<String>) connection.getTexts();

        for (String str : ComInner
                ) {
            //textArea.append(str);
        }
    }
}