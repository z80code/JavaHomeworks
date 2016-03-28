package Task01.ui;

import Task01.model.Member;
import Task01.model.Phone;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class Enter {

    public Member enterNewMemberAttribute(int index, List members, int ChangingIndex, boolean idSet) {
                Member member;

        if (ChangingIndex<0) member = new Member();
        else member = this.memberList.get(select);
        Phone phone = new Phone();
        System.out.println("\n Введите следующие данные:\n");
        int rezult = 1;
        if ((index == 1) || (index == 0)) {
            do {
                try {
                    System.out.print(" Номер ID: ");
                    if (idSet) {
                        Scanner scan = new Scanner(System.in);
                        newMember.setId(scan.nextInt());
                    }
                    else {
                        System.out.println(getSelector());
                        newMember.setId(getSelector());
                    }

                    rezult = 0;
                } catch (Exception e) {
                    System.out.println(" Это целочисленное значение!");
                    rezult = 1;
                }
            } while (rezult > 0);
        }
        if ((index == 2) || (index == 0)) {
            do {
                try {
                    System.out.print(" Имя: ");
                    Scanner scan = new Scanner(System.in);
                    newMember.setFname(scan.next());
                    rezult = 0;
                } catch (Exception e) {
                    System.out.println(" Строка содержит недопустимые символы.");
                    rezult = 1;
                }
            } while (rezult > 0);
        }
        if ((index == 3) || (index == 0)) {
            do {
                try {
                    System.out.print(" Фамилию: ");
                    Scanner scan = new Scanner(System.in);
                    newMember.setLname(scan.next());
                    rezult = 0;
                } catch (Exception e) {
                    System.out.println(" Строка содержит недопустимые символы.");
                    rezult = 1;
                }
            } while (rezult > 0);
        }
        if ((index == 4) || (index == 0)) {
            do {
                try {
                    System.out.print(" Телефон: ");
                    Scanner scan = new Scanner(System.in);
                    phone.setNum(scan.next());
                    rezult = 0;
                } catch (Exception e) {
                    System.out.println(" Строка содержит недопустимые символы.");
                    rezult = 1;
                }
            } while (rezult > 0);
        }
        if ((index == 5) || (index == 0)) {
            do {
                System.out.print(" Тип номера: \n");
                String[] list = {"Not set", "домашний", "рабочий", "мобильный", "velcom", "mtc", "life"};
                int n = 0;
                for (String temp : list
                        ) {
                    System.out.format(" %d - %s\n", n, temp);
                    n++;
                }
                try {
                    System.out.print(" Ваш выбор: ");
                    Scanner scan = new Scanner(System.in);
                    n = scan.nextInt();
                    if ((n >= 0) && (n < list.length)) {
                        phone.setType(n);
                        rezult = 0;
                    } else System.out.println(" Нет такого значения.");
                } catch (Exception e) {
                    System.out.println(" Введите число в указанном диапазоне.");
                    rezult = 1;
                }
            } while (rezult > 0);
        }
        if ((index == 0) || (index == 4) || (index == 5)) newMember.setPhoneNumber(phone);
        if ((index == 6) || (index == 0)) {
            do {
                try {
                    System.out.print(" Он(а) Вам: ");
                    Scanner scan = new Scanner(System.in);
                    newMember.setRelative(scan.next());
                    rezult = 0;
                } catch (Exception e) {
                    System.out.println(" Строка содержит недопустимые символы.");
                    rezult = 1;
                }
            } while (rezult > 0);
        }
        return newMember;
    }
}
