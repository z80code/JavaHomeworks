package MyFrame.Client.ClientLogics;

import MyFrame.Client.Appframe;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class UICreator {

    Appframe Form;

    JPanel contentPane;
    JPanel buttomPane;
    JLabel status = new JLabel(" "); //Строка статуса
    JTextArea textArea;
    JTextArea chatArea;
    JButton send;


    public UICreator(Appframe Form) {
        this.Form = Form;
    }

    public void initUI() {
        // Создаем нижнюю панель
        JPanel allbuttomPane = new JPanel(new BorderLayout());


        buttomPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //buttomPane.
        chatArea = new JTextArea("", 3, 40);
        chatArea.setBorder(BorderFactory.createEtchedBorder(Color.orange, Color.white));
        buttomPane.add(chatArea);
        send = new JButton("Отправить");


        send.addMouseListener(new MenuMouseAdapter("Отправить сообщение.", " ", status));
        send.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                status.setText(" ");
                if (chatArea.getText() == "") return;

                // Отправка
                try {
                    Form.connection.Send(chatArea.getText());
                    textArea.append(chatArea.getText()+"\n");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                chatArea.setText("");
//                JOptionPane.showConfirmDialog(null, "Уже есть открытый документ. Сохраниить его?",
//                        "Внимание!", JOptionPane.YES_NO_OPTION);
                //contentPane.updateUI();
            }
        });

        buttomPane.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        buttomPane.add(send);

        allbuttomPane.add(buttomPane, BorderLayout.CENTER);

        //Создаём рамку для строки статуса
        Border border = BorderFactory.createEtchedBorder(Color.white, Color.white);
        status.setBorder(border);
        //Делаем фон строки непрозрачным
        status.setOpaque(true);
        //Добавляем стоку статуса на панель
        allbuttomPane.add(status, BorderLayout.SOUTH);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollBar vertical = new JScrollBar();
        textArea.add(vertical);

        Border border1 = BorderFactory.createEtchedBorder(Color.CYAN, Color.CYAN);
        textArea.setBorder(border1);

        contentPane = new JPanel(new BorderLayout());

        JScrollPane scrPane = new JScrollPane(textArea);

        contentPane.add(scrPane, BorderLayout.CENTER);
        //contentPane.setBackground(new Color(250, 250, 250));
        contentPane.setBackground(Color.blue);
        contentPane.add(allbuttomPane, BorderLayout.SOUTH);
        Form.add(contentPane);
        //centerPane.setLayout(borderLayout1);

        //System.out.println(centerPane.getLocation().toString()+" " + centerPane.getSize().toString());
        //textArea.setBounds(10,10,150,150);

        //Устанавливаем размер окна
        Form.setSize(new Dimension(600, 400));
        //Устанавливаем заголовок окна
        Form.setTitle("ПростоЧат");
        //Устанавливаем положение окна центр рабочего стола
        Form.setLocationRelativeTo(null);
        //Создаем основное меню
        JMenuBar menuBar = new JMenuBar();
        //Создаем подменю
        JMenu menuFile = new JMenu("Файл");
        JMenu menuView = new JMenu("Просмотр");
        //Добавляем к нашем подменю обработчики событий
        menuFile.addMouseListener(new MenuMouseAdapter("Файловое меню", " ", status));
        menuView.addMouseListener(new MenuMouseAdapter("Меню просмотра", " ", status));
        //Создаем элементы подменю File с обработчиками событий
        JMenuItem item1 = new JMenuItem("Новый");
        item1.addMouseListener(new MouseAdapter() {
                                   @Override
                                   public void mousePressed(MouseEvent e) {

                                       status.setText(" ");
                                       if (textArea != null) {
                                           if (JOptionPane.showConfirmDialog(null, "Уже есть открытый документ. Сохраниить его?",
                                                   "Внимание!", JOptionPane.YES_NO_OPTION) == 0) {
                                               // Сохраняем
                                               if (!IO.saveFile(textArea)) return;
                                           } else {// не сохраняем и чистим
                                               textArea.setText("");
                                           }
                                           //System.out.println(wr); // отладка
                                       } else {
                                           textArea = new JTextArea();
                                           contentPane.add(textArea, BorderLayout.CENTER);
                                       }
                                       contentPane.updateUI();
                                   }
                               }
        );
        item1.addMouseListener(new MenuMouseAdapter("Создать новый файл.", " ", status));

        JMenuItem item2 = new JMenuItem("Открыть");
        item2.addMouseListener(new MouseAdapter() {
                                   @Override
                                   public void mousePressed(MouseEvent e) {
                                       status.setText(" ");
                                       if (textArea != null) {
                                           if (JOptionPane.showConfirmDialog(null, "Есть открытый документ. Сохраниить его?",
                                                   "Внимание!", JOptionPane.YES_NO_OPTION) == 0) {
                                               // Сохраняем
                                               if (!IO.saveFile(textArea)) return;
                                           } else {// не сохраняем и чистим
                                               textArea.setText("");
                                           }
                                           //System.out.println(wr); // отладка
                                       } else {
                                           textArea = new JTextArea();
                                           contentPane.add(textArea, BorderLayout.CENTER);
                                       }
                                       if (!IO.openFile(textArea)) return;
                                       contentPane.updateUI();
                                   }
                               }
        );
        item2.addMouseListener(new MenuMouseAdapter("Открыть новый файл.", " ", status));

        JMenuItem item3 = new JMenuItem("Сохранить");
        item3.addMouseListener(new MenuMouseAdapter("Соxранить текущий файл.", " ", status));
        item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                IO.saveFile(textArea);
            }
        });
        JMenuItem item5 = new JMenuItem("Закрыть");
        item5.addMouseListener(new MenuMouseAdapter("Закрыть файл.", " ", status));
        JMenuItem item6 = new JMenuItem("Выход");
        item6.addMouseListener(new MenuMouseAdapter("Выход из программы.", " ", status));
        //Добавляем обработчик события по нажатию
        item6.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);//Выход из системы
            }
        });
        //Добавляем созданные элементы подменю File
        menuFile.add(item1);
        menuFile.add(item2);
        menuFile.addSeparator();//Разделитель
        menuFile.add(item3);
        menuFile.addSeparator();//Разделитель
        menuFile.add(item5);
        menuFile.addSeparator();//Разделитель
        menuFile.add(item6);
        //Создаем элементы подменю View с обработчиками событий
        JMenuItem itemv1 = new JMenuItem("Цвет фона");
        itemv1.addMouseListener(new MenuMouseAdapter("Изменение цвета фона", " ", status));
        itemv1.setAccelerator(KeyStroke.getKeyStroke(67, InputEvent.CTRL_MASK));
        //Создаём обработчик события по нажатию на элемент itemv1
        itemv1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Случайным образом меняем цвет фона панели
                int r = (int) (Math.random() * 63 + 192);
                int g = (int) (Math.random() * 63 + 192);
                int b = (int) (Math.random() * 63 + 192);
                contentPane.setBackground(new Color(r, g, b));
                status.setText(" ");
            }
        });
        itemv1.addMouseListener(new MenuMouseAdapter("Изменение цвета фона.", " ", status));
        //Добавляем "горячею клавишу"
        //itemv1.setAccelerator(KeyStroke.getKeyStroke(67, InputEvent.CTRL_MASK));
        //Добавляем элемент
        menuView.add(itemv1);

        //Добавляем подменю в основное меню
        menuBar.add(menuFile);
        menuBar.add(menuView);
        //Устанавливаем полученное меню на окно
        Form.setJMenuBar(menuBar);
        chatArea.setFocusable(true);
    }
}

