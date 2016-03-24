package Task00;

import java.util.Scanner;

/**
 * Created by Scorpion on 21.02.2016.
 */
public class App1 {


    // функция печати
    public static void print(String val) {
        System.out.println(val);
    }

    public static void main(String[] args) {
        print("Введите целое число.");
        int value = 1;
        do {
            //
            Scanner scan = new Scanner(System.in);
            // проверка операции присвоения
            try {
                value = scan.nextInt();
                // проверка кратности 3
                if (value % 3 == 0) {
                    print("Число кратно трем.");
                } else {
                    print("Число не кратно трем.");
                }

            } catch (Exception e) {
                //Сообщение об ошибке при возникновении исключения(любого)
                System.out.println("Это должно быть целое число. Будьте внимательны!");
            }
        } while (value != 0);// проверка на выход из программы по нулю
    }
}
