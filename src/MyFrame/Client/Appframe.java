package MyFrame.Client;

import MyFrame.Client.ClientLogics.UICreator;
import MyFrame.Client.ClientTransfer.Connection;
import MyFrame.Client.ClientTransfer.Listener;
import MyFrame.Server.Transfers.Connecton;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;

public class Appframe extends JFrame {

    private Socket socket;
    public Connection connection;
    UICreator UI;

    private Thread inner;

    public Appframe() {
        /**Конструктор */
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        UI = new UICreator(this);
        try {
            UI.initUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        socket = new Socket();

        try {
            socket.connect(new InetSocketAddress("192.168.1.2", 8080));
            connection = new Connection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.addListener(new Listener() {
            @Override
            public void onDataReceived(String message) {
                showMessage(message);
            }
        });
        connection.start();
    }

    private void showMessage(String message) {
        UI.textArea.append(message+"\n");

    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            //if (inner!=null)
            System.exit(0);
        }
    }
}