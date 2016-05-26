package MyFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

public class Appframe extends JFrame {
    JPanel contentPane;
    JPanel buttomPane;
    JLabel status = new JLabel(" "); //Строка статуса
    JTextArea textArea;
    JTextArea ChatArea;
    JButton send;

    public Appframe() {
        /**Конструктор */
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void jbInit() throws Exception {
        // Создаем нижнюю панель

        JPanel allbuttomPane = new JPanel(new BorderLayout());


        buttomPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //buttomPane.
        ChatArea = new JTextArea("", 3, 40);
        buttomPane.add(ChatArea);
        send = new JButton("Отправить");
        send.addMouseListener(new MenuMouseAdapter("Отправить сообщение.", " ", status));
        send.addMouseListener(new MouseAdapter() {
                                   @Override
                                   public void mousePressed(MouseEvent e) {
                                       status.setText("");
                                       if (ChatArea.getText() == "") return;

                                       // Отправка
                                       JOptionPane.showConfirmDialog(null, "Уже есть открытый документ. Сохраниить его?",
                                               "Внимание!", JOptionPane.YES_NO_OPTION);

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
        add(contentPane);
        //centerPane.setLayout(borderLayout1);

        //System.out.println(centerPane.getLocation().toString()+" " + centerPane.getSize().toString());
        //textArea.setBounds(10,10,150,150);

        //Устанавливаем размер окна
        this.setSize(new Dimension(600, 400));
        //Устанавливаем заголовок окна
        this.setTitle("ПростоЧат");
        //Устанавливаем положение окна центр рабочего стола
        this.setLocationRelativeTo(null);
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
                                               if (!saveFile()) return;
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
                                               if (!saveFile()) return;
                                           } else {// не сохраняем и чистим
                                               textArea.setText("");
                                           }
                                           //System.out.println(wr); // отладка
                                       } else {
                                           textArea = new JTextArea();
                                           contentPane.add(textArea, BorderLayout.CENTER);
                                       }
                                       if (!openFile()) return;
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
                saveFile();
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
        this.setJMenuBar(menuBar);
    }

    private boolean openFile() {
        JFileChooser fs = new JFileChooser();
        File filename;
        int choose = 0;
        fs.showDialog(null, "Открыть");
        filename = fs.getSelectedFile();
        if (filename == null) return false;
        if (!filename.exists()) {
            choose = JOptionPane.showConfirmDialog(null, "Нет такого файла.",
                    "Внимание!",  JOptionPane.OK_OPTION);
            return false;
        }
        String s;
        BufferedReader buffReader = null;
        try {
            buffReader = new BufferedReader(new FileReader(filename));
            while ((s = buffReader.readLine()) != null) {
                textArea.append(s);
                textArea.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffReader!=null) buffReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private boolean saveFile() {
        JFileChooser fs = new JFileChooser();
        File filename;
        int choose = 0;
        fs.showDialog(null, "Сохранить");
        filename = fs.getSelectedFile();
        if (filename == null) return false;
        if (filename.exists())
            choose = JOptionPane.showConfirmDialog(null, "Такой файл уже существует. Перезаписать его?",
                    "Внимание!", JOptionPane.YES_NO_OPTION);
        if (choose == 1) return false;
        String txt = textArea.getText();
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));

            bufferedWriter.append(txt);

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (bufferedWriter!=null)
                {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }
}