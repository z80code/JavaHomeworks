import java.util.Scanner;

public class App2 {
    public static void main(String[] args) {
        Members memberList = new Members();

        //закоментируйте, чтобы создать пустую базу
        // начало блока автоввода
        Member Number = new Member("Иван", "Иванов", new Phone("697-45-87", 1), "друг");
        memberList.add(Number);
        Member Number1 = new Member("Сергей", "Петров", new Phone("622-02-13", 2), "знакомый");
        memberList.add(Number1);
        Member Number2 = new Member("Петр", "Сидоров", new Phone("648-56-23", 3), "враг");
        memberList.add(Number2);
        Member Number3 = new Member("Ольга", "Ященко", new Phone("613-82-12", 4), "коллега");
        memberList.add(Number3);
        Member Number4 = new Member("Зина", "Петрова", new Phone("652-34-14", 2), "враг");
        memberList.add(Number4);
        // конец блока автоввода

        int mode;
        Menu menu = new Menu();
        do {
            mode = menu.mainMenu(memberList.getCount());
            switch (mode) {
                case 1: {// добавление
                    Member newMember = memberList.EnterNewMemberAttribute(0, -1);
                    memberList.add(newMember);
                    break;
                }
                case 2: {// вывод всего
                    memberList.showAll();
                    break;
                }
                case 3: {// поиск
                    if (memberList.getCount() <= 0) {
                        System.out.println(" В пустой базе нечего искать.\n");
                        break;
                    }
                    int index = menu.findMenu();
                    if (index == 0) break;
                    Member newMember = memberList.EnterNewMemberAttribute(index, -1);
                    memberList.find(newMember, index);
                    break;
                }
                case 4: {// удаление
                    if (memberList.getCount() <= 0) {
                        System.out.println(" В пустой базе нечего удалять.\n");
                        break;
                    }
                    int index = menu.findMenu();
                    if (index == 0) break;
                    Member newMember = memberList.EnterNewMemberAttribute(index, -1);
                    memberList.delete(newMember, index);
                    break;
                }
                case 5: {// сортировка
                    if (memberList.getCount() <= 0) {
                        System.out.println(" В пустой базе нечего сортировать.\n");
                        break;
                    }
                    int index = menu.sortMenu();
                    if (index == 0) break;
                    memberList.sort(index);
                    break;
                }
                case 6: {// изменение
                    if (memberList.getCount() <= 0) {
                        System.out.println(" В пустой базе нечего изменять.\n");
                        break;
                    }
                    int index = menu.changeMenu();// выбор атрибута для смены
                    if (index == 0) break;
                    memberList.showAll();
                    int select;
                    do {
                        select = menu.selectMenu();// выбор ID для смены
                        select = memberList.idExist(select);
                    } while (select < 0);
                    memberList.EnterNewMemberAttribute(index, select);
                    break;
                }
            }
        } while (mode != 0);
    }
}

class Members {

    private Member[] memberList = null;
    private int getSelector() {
        int max = 0;
        for (int i = 0; i < this.getCount(); i++) {
            max = (memberList[i].getId() > max) ? memberList[i].getId() : max;
        }
        return max + 1;
    }

    /*
    *   поиск по атрибуту (указывается индексом),
    *   заданному в поле "newMember"
    */
    public int getCount() {
        if (memberList != null) return memberList.length;
        else return 0;
    }

    /*
        public void ShowMember(int index) {
            memberList[index].showMember();
        }
    */
    public Member EnterNewMemberAttribute(int index, int select) {
        Member newMember;
        if (select < 0) newMember = new Member();
        else newMember = this.memberList[select];
        Phone phone = new Phone();
        System.out.println("\n Введите следующие данные:\n");
        int rezult = 1;
        if ((index == 1) || (index == 0)) {
            do {
                try {
                    System.out.print(" Номер ID: ");
                    /*
                    Scanner scan = new Scanner(System.in);
                    newMember.setId(scan.nextInt());
                    */
                    System.out.println(getSelector());
                    newMember.setId(getSelector());
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

    /*
    *   поиск по атрибуту (указывается индексом),
    *   заданному в поле "newMember"
    */
    public void find(Member newMember, int index) {
        if (index > 0) {
            int count_wr = 0;
            switch (index) {
                case 1:/// Поиск по номеру id.
                {
                    System.out.print(" Поиск по номеру ID.\n");
                    for (int i = 0; i < memberList.length; i++) {
                        if (memberList[i].getId() == newMember.getId()) {
                            memberList[i].showMember();
                            count_wr++;
                        }
                    }
                    break;
                }
                case 2:/// Поиск по имени.
                {
                    System.out.print(" Поиск по имени.\n");
                    for (int i = 0; i < memberList.length; i++) {
                        if (memberList[i].getFname().compareTo(newMember.getFname()) == 0) {
                            memberList[i].showMember();
                            count_wr++;
                        }
                    }
                    break;
                }
                case 3:/// Поиск по фамилии.
                {
                    System.out.print(" Поиск по фамилии.\n");
                    for (int i = 0; i < memberList.length; i++) {
                        if (memberList[i].getLname().compareTo(newMember.getLname()) == 0) {
                            memberList[i].showMember();
                            count_wr++;
                        }
                    }
                    break;
                }

                case 4:/// Поиск по номеру телефона.
                {
                    System.out.print(" Поиск по номеру телефона.\n");
                    for (int i = 0; i < memberList.length; i++) {
                        if (memberList[i].phoneNumber.getNum().compareTo(newMember.phoneNumber.getNum()) == 0) {
                            memberList[i].showMember();
                            count_wr++;
                        }
                    }
                    break;
                }
                case 5:/// Поиск по типу номера.
                    System.out.print(" Поиск по типу номера.\n");
                    for (int i = 0; i < memberList.length; i++) {
                        if (memberList[i].phoneNumber.getType() == newMember.phoneNumber.getType()) {
                            memberList[i].showMember();
                            count_wr++;
                        }
                    }
                    break;

                case 6:/// Поиск по отношениям.
                {
                    System.out.print(" Поиск по  отношениям.\n");
                    for (int i = 0; i < memberList.length; i++) {
                        if (memberList[i].getRelative().compareTo(newMember.getRelative()) == 0) {
                            memberList[i].showMember();
                            count_wr++;
                        }
                    }
                    break;
                }
            }
            if (count_wr == 0) {
                System.out.println(" Нет записей ...");
            } else {
                System.out.format(" Найдено записей: %d\n\n", count_wr);
            }

        }

    }

    /*
    *   Добавление новой персоны
    *   заданной в поле "newMember"
    */
    public void add(Member newMember) {

        Member[] newMemberList = new Member[this.getCount() + 1];
        int i;
        if (this.getCount() > 0) {
            for (i = 0; i < this.getCount(); i++) {
                newMemberList[i] = memberList[i];
            }
        }
        newMember.setId(getSelector());
        newMemberList[this.getCount()] = newMember;
        memberList = newMemberList;
        //
        System.out.println(" Добавлено!");
    }

    /*
    *   Удаление персоны по атрибуту (указывается индексом),
    *   заданному в поле "newMember"
    */
    public void delete(Member newMember, int index) {

        int[] forDelete = new int[memberList.length];
        int count_wr = 0;
        switch (index) {
            case 1:/// Удаление по номеру ID.
            {
                for (int i = 0; i < memberList.length; i++) {
                    if (memberList[i].getId() == newMember.getId()) {
                        memberList[i].showMember();
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 2:/// Удаление по Имени.
            {
                for (int i = 0; i < memberList.length; i++) {
                    if (memberList[i].getFname().compareTo(newMember.getFname()) == 0) {
                        memberList[i].showMember();
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 3:/// Удаление по фамилии.
            {
                for (int i = 0; i < memberList.length; i++) {
                    if (memberList[i].getLname().compareTo(newMember.getLname()) == 0) {
                        memberList[i].showMember();
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 4:/// Удаление по номеру телефона.
            {
                for (int i = 0; i < memberList.length; i++) {
                    if (memberList[i].phoneNumber.getNum().compareTo(newMember.phoneNumber.getNum()) == 0) {
                        memberList[i].showMember();
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 5:/// Удаление по типу телефона.
            {
                for (int i = 0; i < memberList.length; i++) {
                    if (memberList[i].phoneNumber.getType() == newMember.phoneNumber.getType()) {
                        memberList[i].showMember();
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 6:/// Удаление по типу телефона.
            {
                for (int i = 0; i < memberList.length; i++) {
                    if (memberList[i].getRelative().compareTo(newMember.getRelative()) == 0) {
                        memberList[i].showMember();
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            //ShellsSortNumber(memberList, baseSize, 1);

        }
        if (count_wr == 0) {
            System.out.println(" Нет записей ...");
        } else {
            System.out.format("Найдено записей: %d\n", count_wr);
        }
        //-----------------------------------------------------------
        if (count_wr != 0) {
            if (chose())/// если ДА - Удаляем
            {
                int baseSize = memberList.length;
                for (int i = count_wr - 1; i >= 0; i--) {
                    if (forDelete[i] != baseSize - 1) {
                        memberList[forDelete[i]] = memberList[baseSize - 1];
                    }
                    baseSize--;
                }
                Member[] newMemberList = new Member[baseSize];
                if (newMemberList != null)
                    for (int i = 0; i < newMemberList.length; i++) {
                        newMemberList[i] = memberList[i];
                    }
                this.memberList = newMemberList;
                System.out.println(" Данные удалены.");
            } else System.out.println(" Удаление отменено.");
        } else {
            System.out.println(" Нечего удалять.\n");
        }
    }

    public void sort(int index) {
        int i, j, k, N = memberList.length;
        Member t;
        for (k = N / 2; k > 0; k /= 2)
            for (i = k; i < N; i += 1) {//с середины и до конца
                t = memberList[i];
                for (j = i; j >= k; j -= k) {
                    if (index == 1) {
                        if (t.getId() < memberList[j - k].getId()) {
                            memberList[j] = memberList[j - k];
                        } else
                            break;
                    }
                    if (index == 2) {
                        if (t.getFname().compareTo(memberList[j - k].getFname()) < 0) {

                            memberList[j] = memberList[j - k];
                        } else
                            break;
                    }
                    if (index == 3) {
                        if (t.getLname().compareTo(memberList[j - k].getLname()) < 0) {

                            memberList[j] = memberList[j - k];
                        } else
                            break;
                    }
                    if (index == 4) {
                        if (t.phoneNumber.getNum().compareTo(memberList[j - k].phoneNumber.getNum()) < 0) {
                            memberList[j] = memberList[j - k];
                        } else
                            break;
                    }
                    if (index == 5) {
                        if (t.phoneNumber.getType() < memberList[j - k].phoneNumber.getType()) {

                            memberList[j] = memberList[j - k];
                        } else
                            break;
                    }
                    if (index == 6) {
                        if (t.getRelative().compareTo(memberList[j - k].getRelative()) < 0) {

                            memberList[j] = memberList[j - k];
                        } else
                            break;
                    }
                }
                memberList[j] = t;
            }
        System.out.println(" Отсортировано.\n");
    }

    public void showAll() {
        if (this.getCount() > 0) {
            for (int i = 0; i < this.getCount(); i++) {
                memberList[i].showMember();
            }
        } else {
            System.out.println(" Нет записей ...\n");
        }
    }

    public int idExist(int id) {
        for (int i = 0; i < this.getCount(); i++) {
            if (this.memberList[i].getId() == id) return i;
        }
        System.out.println("Нет такого ID. Повторите ввод ID.");
        return -1;
    }

    /*
    *   Метод предоставления выбора
    *   "да" или "нет"
    */
    private boolean chose() {
        do {
            System.out.println(" Удалить выбранное (д/н или y/n) -> ");
            try {
                Scanner scan = new Scanner(System.in);
                String value = scan.next();
                return (value.compareTo("y") == 0 || value.compareTo("д") == 0 || value.compareTo("Y") == 0 || value.compareTo("Д") == 0);
            } catch (Exception e) {
                System.out.println("Выберите утверждение.");
            }
        } while (true);
    }
}

class Menu {

    private int choose() {
        int rezult = -1;
        try {
            System.out.print(" Выберите действие (0 - Выход) -> ");
            Scanner scan = new Scanner(System.in);
            rezult = scan.nextInt();
        } catch (Exception e) {
            System.out.println(" Это целочисленное значение!");
        }
        return rezult;
    }

    public int mainMenu(int baseSize) {
        int mode0;
        System.out.format("В базе записей - %d\n", baseSize);
        System.out.print("\n 1. Добавить запись.\n 2. Вывести записи.\n 3. Поиск по атрибуту.\n 4. Удаление по атрибуту.\n 5. Сортировать по атрибуту.\n 6. Править атрибут записи.\n");
        do {
            mode0 = choose();
            if (mode0 < 0 && mode0 > 6) {
                System.out.format(" %d - Нет такого действия!\n", mode0);
            }
        } while (mode0 < 0 && mode0 > 6);
        return mode0;
    }

    public int findMenu() {
        int mode0;
        System.out.print("\n Поиск по атрибуту.\n");
        System.out.print("\n 1. Номеру ID.\n 2. Имени.\n 3. Фамилии.\n 4. Номеру телефона.\n 5. Типу телефона.\n 6. Отношению к Вам.\n");
        do {
            mode0 = choose();
            if (mode0 < 0 && mode0 > 6) {
                System.out.format(" Нет такого действия!\n");
            }
        } while (mode0 < 0 && mode0 > 6);
        return mode0;
    }

    public int sortMenu() {
        int mode0;
        System.out.print("\n Сортировка по атрибуту.\n");
        System.out.print("\n 1. Номеру ID.\n 2. Имени.\n 3. Фамилии.\n 4. Номеру телефона.\n 5. Типу телефона.\n 6. Отношению к Вам.\n");
        do {
            mode0 = choose();
            if (mode0 < 0 && mode0 > 6) {
                System.out.format(" Нет такого действия!\n");
            }
        } while (mode0 < 0 && mode0 > 6);
        return mode0;
    }

    public int changeMenu() {
        int mode0;
        System.out.print("\n Изменить атрибут.\n");
        System.out.print("\n 1. Номер ID.\n 2. Имя.\n 3. Фамилию.\n 4. Номер телефона.\n 5. Тип телефона.\n 6. Отношениe к Вам.\n");
        do {
            mode0 = choose();
            if (mode0 < 0 && mode0 > 6) {
                System.out.format(" Нет такого действия!\n");
            }
        } while (mode0 < 0 && mode0 > 6);
        return mode0;
    }

    public int selectMenu() {
        int mode0;
        System.out.print("\n Выберите запись указав ID.\n");
        do {
            mode0 = choose();
            if (mode0 < 0 && mode0 > 6) {
                System.out.format(" Нет такого действия!\n");
            }
        } while (mode0 < 0 && mode0 > 6);
        return mode0;
    }
}

// класс телефона
class Phone {
    String num;//           Номер телефона
    int type;//             Тип (например домашний, рабочий и т.д.)

    public Phone() {
        this.num = "No set...";
        this.type = 0;
    }

    public Phone(String num_phone, int type) {
        this.num = num_phone;
        this.type = type;
    }

    public String getPhone() {
        String[] list = {"Not set", "домашний", "рабочий", "мобильный", "velcom", "mtc", "life"};
        if (this.type < list.length && this.type >= 0) {
            String temp = "";

            int n = 22 - this.num.length() - list[this.type].length();

            while (n > 0) {
                temp = temp + " ";
                n--;
            }

            return this.num + "(" + list[this.type] + ")" + temp;
        } else return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public int getType() {
        return this.type;
    }
}

// класс персоны
class Member {
    private int id;//               Id
    private String fname;//         Имя
    private String lname;//         Фамилия
    Phone phoneNumber;//    Номер телефона (тоже объект)
    private String relative;//      Список тегов (например: друг, коллега, семья и т.д)

    public Member() {
        this.id = 0;
        this.fname = "No name.";
        this.lname = "No name.";
        this.phoneNumber = new Phone();
        this.relative = "Not set.";
    }

    public Member(String fname, String lname, Phone phoneNumber, String relative) {
        this.fname = fname;
        this.lname = lname;
        this.phoneNumber = phoneNumber;
        this.relative = relative;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPhoneNumber(Phone phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Phone getPhoneNumber() {
        return phoneNumber;
    }

    public String getRelative() {
        return relative;
    }

    public void showMember() {
        System.out.format("%2d ", this.id);
        System.out.format("%s %s", this.fname, " " + this.lname);
        int n = 28 - this.fname.length() - this.lname.length();

        while (n != 0) {
            System.out.print(' ');
            n--;
        }
        System.out.format("%s %s", this.phoneNumber.getPhone(), this.relative + '\n');
    }
}
