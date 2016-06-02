package MyFrame.Client;

import MyFrame.Client.ClientLogics.UICreator;
import MyFrame.Client.ClientTransfer.Connection;
import MyFrame.Client.ClientTransfer.Listener;
import MyFrame.Packet;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.*;

public class Appframe extends JFrame {

    private Socket socket;
    public Connection conection;
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
            conection = new Connection(socket);
            conection.addListener(new Listener() {
                @Override
                public void onDataReceived(Packet message) {
                    showMessage(message);
                }
            });

            conection.start();
        } catch (IOException e) {
            UI.textArea.append("Сервер отключен." + "\n");
        }

    }

    private void showMessage(Packet message) {
        UI.textArea.append(message.name + ":\n");
        for (String str: message.strings) {
            UI.textArea.append( str+"\n");
        }

    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            //if (inner!=null)
            System.exit(0);
        }
    }
}