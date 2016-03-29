package Task01.ui;

import Task01.model.Member;
import Task01.model.Phone;

import java.util.List;

public final class Enter {

    public static Member Member(int id) {
        Member member = new Member();
        //
        IO.show(" Введите следующие данные:");
        //
        //member.setId(IO.scannerInt());
        IO.show(" ID: ");
        member.setId(id);
        IO.show(id);
        //
        IO.show(" Имя: ");
        member.setFname(IO.scannerStr());
        //
        IO.show(" Фамилию: ");
        member.setLname(IO.scannerStr());
        //
        IO.show(" Телефон: ");
        member.setPhoneNumber(IO.scannerStr());
        //
        IO.show(" Тип номера: ");
        String[] list = {"Not set", "домашний", "рабочий", "мобильный", "velcom", "mtc", "life"};
        int n = 0;
        for (String temp : list) {
            IO.show(String.format(" %d - %s", n, temp));
            n++;
        }
        member.setPhoneType(IO.scannerInt());
        //
        IO.show(" Он(а) Вам: ");
        member.setRelative(IO.scannerStr());
        return member;
    }

    public static Member changeMember(int id, Member member, int index) {
        Phone phone = new Phone();
        //
        IO.show(" Измените следующие данные:");
        if (index == 1) {
            IO.show("Старое значение ID: ");
            IO.show(member.getId());
            member.setId(id);
        }
        //
        if (index == 2) {
            IO.show("Старое значение Имя: ");
            IO.show(member.getFname());
            member.setFname(IO.scannerStr());
        }
        //
        if (index == 3) {
            IO.show("Старое значение Фамилию: ");
            IO.show(member.getLname());
            member.setLname(IO.scannerStr());
        }
        //
        if (index == 4) {
            IO.show("Старое значение Телефон: ");
            IO.show(member.getPhoneNumber());
            member.setPhoneNumber(IO.scannerStr());
        }
        //
        if (index == 5) {
            IO.show("Старое значение Тип номера: ");
            IO.show(member.getPhoneType());
            String[] list = {"Not set", "домашний", "рабочий", "мобильный", "velcom", "mtc", "life"};
            int n = 0;
            for (String temp : list) {
                IO.show(String.format(" %d - %s", n, temp));
                n++;
            }
            member.setPhoneType(IO.scannerInt());
        }
        //
        if (index == 6) {
            IO.show("Старое значение Он(а) Вам: ");
            IO.show(member.getRelative());
            member.setRelative(IO.scannerStr());
        }
        return member;
    }

    public static Object getValue(int index) {
        Object object;
        if (index == 1 || index == 5) {
            System.out.println(" Введите значение: ");
            int number = IO.scannerInt();
            object = number;
        } else {
            System.out.println(" Введите строку: ");
            String number = IO.scannerStr();
            object = number;
        }
        return object;
    }

    public static void showList(List<Member> list) {
        for (Member member : list) {
            IO.show(member.toString());
        }
    }

    public static boolean chose() {
        do {
            System.out.println(" Удалить выбранное (д/н или y/n) -> ");
            String value = IO.scannerStr();
            return (value.compareTo("y") == 0 || value.compareTo("д") == 0 || value.compareTo("Y") == 0 || value.compareTo("Д") == 0);
        } while (true);
    }
}
