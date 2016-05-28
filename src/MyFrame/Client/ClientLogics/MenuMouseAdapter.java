package MyFrame.Client.ClientLogics;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MenuMouseAdapter extends MouseAdapter {
    String messageEntered;
    String messageExited;
    JLabel status;

    /**
     * Конструктор
     *
     * @param messageEntered строка которую выводим в строку
     *                       статуса по входу курсора на элемент
     * @param messageExited  строка которую выводим в строку
     *                       статуса по выходу курсора за пределы элемента
     *                       status строка статуса в которую происходит вывод
     */
    public MenuMouseAdapter(String messageEntered, String messageExited, JLabel status) {
        this.messageEntered = messageEntered;
        this.messageExited = messageExited;
        this.status = status;
    }

    public void mouseEntered(MouseEvent e) {
        status.setText(messageEntered);
    }

    public void mouseExited(MouseEvent e) {
        status.setText(messageExited);
    }
}