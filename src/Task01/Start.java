package Task01;

import Task01.bll.InCSVfile;
import Task01.bll.InMemory;
import Task01.model.Member;
import Task01.model.Phone;
import Task01.ui.Enter;
import Task01.ui.IO;
import Task01.ui.Menu;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        // InMemory Members = new InMemory();
        InCSVfile Members = new InCSVfile();
        //закоментируйте, чтобы создать пустую базу
        // начало блока автоввода
        Member Number = new Member(0, "Иван", "Иванов", new Phone("697-45-87", 1), "друг");
        Members.add(Number);
        Member Number1 = new Member(1, "Сергей", "Петров", new Phone("622-02-13", 2), "знакомый");
        Members.add(Number1);
        Member Number2 = new Member(2, "Петр", "Сидоров", new Phone("648-56-23", 3), "враг");
        Members.add(Number2);
        Member Number3 = new Member(3, "Ольга", "Ященко", new Phone("613-82-12", 4), "коллега");
        Members.add(Number3);
        Member Number4 = new Member(4, "Зина", "Петрова", new Phone("652-34-14", 2), "враг");
        Members.add(Number4);
        // конец блока автоввода

        int mode;
        do {
            mode = Menu.mainMenu(Members.count());
            switch (mode) {
                case 1: {// добавление
                    IO.show("Добавление в список: ");
                    Members.add(Enter.Member(Members.getSelector()));
                    break;
                }
                case 2: {// вывод всего
                    IO.show("Вывод списка: ");
                    IO.showList(Members.getAll());
                    break;
                }
                case 3: {// поиск
                    IO.show("Поиск по списку: ");
                    if (Members.count() <= 0) {
                        System.out.println(" В пустой базе нечего искать.\n");
                        break;
                    }

                    int index = Menu.findMenu();
                    if (index == 0) break;

                    Object object = Enter.getValue(index);
                    List<Member> listM = Members.find(index, object);
                    IO.showList(listM);
                    break;
                }
                case 4: {// удаление
                    IO.show("Удаление в списке: ");
                    if (Members.count() <= 0) {
                        System.out.println(" В пустой базе нечего удалять.\n");
                        break;
                    }
                    int index = Menu.findMenu();
                    if (index == 0) break;

                    Object object = Enter.getValue(index);
                    List<Member> listForDelete = Members.find(index, object);
                    if (listForDelete.size() > 0) {
                        IO.showList(listForDelete);
                        if (Enter.chose())/// если ДА - Удаляем
                        {
                            Members.delete(listForDelete);
                            System.out.println(" Данные удалены.");
                        } else System.out.println(" Удаление отменено.");
                    } else {
                        System.out.println(" Нечего удалять.\n");
                    }
                    break;
                }
                case 5: {// сортировка
                    IO.show("Сортиовка списка: ");
                    if (Members.count() <= 0) {
                        System.out.println(" В пустой базе нечего сортировать.\n");
                        break;
                    }
                    int index = Menu.sortMenu();
                    if (index == 0) break;
                    Members.sort(index);
                    break;
                }
                case 6: {// изменение
                    IO.show("Изменение записи списка: ");
                    if (Members.count() <= 0) {
                        System.out.println(" В пустой базе нечего изменять.\n");
                        break;
                    }
                    int index = Menu.changeMenu();// выбор атрибута для смены
                    if (index == 0) break;
                    IO.showList(Members.getAll());

                    int selectId;
                    Member member;
                    do {
                        IO.show("Укажите существующий ID из списка");
                        selectId = IO.scannerInt();// выбор ID для смены
                        member =  Members.getById(selectId);
                    } while (member == null);

                    Member newMember =  Enter.changeMember(selectId, member, index);
                    Members.change(selectId, newMember);
                    break;
                }
            }
        } while (mode != 0);
    }
}