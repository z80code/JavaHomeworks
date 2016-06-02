package MyFrame.Client.ClientTransfer;

import MyFrame.Packet;

public interface Listener {
    void onDataReceived(Packet message);
}

