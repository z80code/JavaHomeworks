package Task01.ui;

/*
 * Вывод сообщений меню в консоль
 */
public class Menu {
    private static String[] mainMenuText = new String[]{
            "1. Добавить запись.",
            "2. Вывести записи.",
            "3. Поиск по атрибуту.",
            "4. Удаление по атрибуту.",
            "5. Сортировать по атрибуту.",
            "6. Править атрибут записи."
    };

    private static String[] findMenuText =  new String[]{"1. Номеру ID.\n" +
            "2. Имени.",
            "3. Фамилии.",
            "4. Номеру телефона.",
            "5. Типу телефона.",
            "6. Отношению к Вам."
    };

    private static final int findMenuTextCount = 6;


    private int choose() {
        return IOunit.scannerInt();
    }
    private void printMenu(String[] MenuText){
        for (int i=0;i<MenuText.length;i++){
            IOunit.show(MenuText[i]);
        }
    }




    public int mainMenu(int baseSize) {
        int mode0;
        IOunit.show(String.format("В базе записей - %d\n", baseSize));
        printMenu(mainMenuText);
        do {
            IOunit.show(Message.chooseMessage);
            mode0 = IOunit.scannerInt();
            // TODO magic numbers лучше избегать, все числовые значения, по числу не совсем очевидно что значит 6, лучше
            // заводить константы
            if (mode0 < 0 && mode0 > mainMenuText.length) {
                IOunit.show(String.format(Message.notCorrect, mode0));
            }
        } while (mode0 < 0 && mode0 > mainMenuText.length);
        return mode0;
    }

    public int findMenu() {
        int mode0;
        System.out.print("\n Поиск по атрибуту.\n");
        System.out.print("\n 1. Номеру ID.\n 2. Имени.\n 3. Фамилии.\n 4. Номеру телефона.\n 5. Типу телефона.\n 6. Отношению к Вам.\n");
        do {
            mode0 = choose();
            if (mode0 < 0 && mode0 > 6) {
                System.out.format(Message.notCorrect);
            }
        } while (mode0 < 0 && mode0 > 6);
        return mode0;
    }

    public int sortMenu() {
        int mode0;
        System.out.print("\n Сортировка по атрибуту.\n");
        // TODO нижняя строка несколько раз дубируется, значит следует вынести в одну переменную, дублирование часто
        // приводит ко ошибкам, при исправлении в одном месте можно забыть поправить в другом
        // что касается строк, то их тоже разумно выносить в отдельное место (это будет полезно при локализации приложения)
        System.out.print("\n 1. Номеру ID.\n 2. Имени.\n 3. Фамилии.\n 4. Номеру телефона.\n 5. Типу телефона.\n 6. Отношению к Вам.\n");
        do {
            mode0 = choose();
            if (mode0 < 0 && mode0 > 6) {
                System.out.format(" Нет такого действия!\n");
            }
        } while (mode0 < 0 && mode0 > 6);
        return mode0;
    }

    // TODO методы имеют очень похожую структуру, возможно имеет смысл написать один универсальный метод

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
