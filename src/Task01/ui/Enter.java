package Task01.ui;

import Task01.model.Member;
import Task01.model.Phone;

public final class Enter {

    public static Member Member(int id) {
        Member member = new Member();
        //
        IOconsole.show(" Введите следующие данные:");
        //
        //member.setId(IOconsole.scannerInt());
        IOconsole.show(" ID: ");
        member.setId(id);
        IOconsole.show(id);
        //
        IOconsole.show(" Имя: ");
        member.setFname(IOconsole.scannerStr());
        //
        IOconsole.show(" Фамилию: ");
        member.setLname(IOconsole.scannerStr());
        //
        IOconsole.show(" Телефон: ");
        member.setPhoneNumber(IOconsole.scannerStr());
        //
        IOconsole.show(" Тип номера: ");
        String[] list = {"Not set", "домашний", "рабочий", "мобильный", "velcom", "mtc", "life"};
        int n = 0;
        for (String temp : list) {
            IOconsole.show(String.format(" %d - %s", n, temp));
            n++;
        }
        member.setPhoneType(IOconsole.scannerInt());
        //
        IOconsole.show(" Он(а) Вам: ");
        member.setRelative(IOconsole.scannerStr());
        return member;
    }

    public static Member changeMember(int id, Member member, int index) {
        Phone phone = new Phone();
        //
        IOconsole.show(" Измените следующие данные:");
        if (index == 1){
            IOconsole.show("Старое значение ID: ");
            IOconsole.show(member.getId());
            member.setId(id);

        }
        //
        if (index == 2){
            IOconsole.show("Старое значение Имя: ");
            IOconsole.show(member.getFname());
            member.setFname(IOconsole.scannerStr());
        }

        //
        if (index == 3) {
            IOconsole.show("Старое значение Фамилию: ");
            IOconsole.show(member.getLname());
            member.setLname(IOconsole.scannerStr());
        }
        //
        if (index == 4) {
            IOconsole.show("Старое значение Телефон: ");
            IOconsole.show(member.getPhoneNumber());
            phone.setNum(IOconsole.scannerStr());
        }
        //
        if (index == 5) {
            IOconsole.show("Старое значение Тип номера: ");
            IOconsole.show(member.getPhoneType());
            String[] list = {"Not set", "домашний", "рабочий", "мобильный", "velcom", "mtc", "life"};
            int n = 0;
            for (String temp : list) {
                IOconsole.show(String.format(" %d - %s", n, temp));
                n++;
            }
            member.setPhoneType(IOconsole.scannerInt());
        }
        //
        if (index == 6) {
            IOconsole.show("Старое значение Он(а) Вам: ");
            IOconsole.show(member.getRelative());
            member.setRelative(IOconsole.scannerStr());
        }
        return member;
    }

}
