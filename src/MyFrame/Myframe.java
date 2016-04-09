package MyFrame;
import com.sun.xml.internal.ws.api.Component;

import javax.swing.JFrame;

public class Myframe extends JFrame { //Наследуя от JFrame мы получаем всю функциональность окна

        public Myframe(){
            super("My First Window"); //Заголовок окна
            setBounds(0, 0, 600, 350); //Если не выставить
            setLocationRelativeTo(null);
            //размер и положение
            //то окно будет мелкое и незаметное
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того чтобы при
            //закрытии окна закрывалась и программа,
            //иначе она останется висеть в процессах
        }

        public static void main(String[] args) { //эта функция может быть и в другом классе
            Myframe app = new Myframe(); //Создаем экземпляр нашего приложения
            app.setVisible(true); //С этого момента приложение запущено!
        }
    }

