package Task01.bll;

import Task01.model.Member;
import Task01.model.Phone;

import java.util.List;
import java.util.Scanner;

 public interface Ibase {

     int getSelector();

     Member getById(int id);

     /*
     *   поиск по атрибуту (указывается индексом)
     *   заданному в поле ""
     */
     int count();

     /*
     *   Получает весь список
     */
     List<Member> getAll();



     /*
     *   поиск по атрибуту (указывается индексом)
     *   заданному в поле ""
     */
     List<Member> find(int index, Object value );

     /*
     *   Добавление новой персоны
     *   заданной в поле "newMember"
     */
     boolean add(Member member);

     /*
     *   Изменение персоны
     *   заданной в поле "newMember"
     */
     boolean change(int id, Member member);

    /*
    *   Удаление персоны по атрибуту (указывается индексом),
    *   заданному в поле "newMember"
    */

     void delete(List<Member> listForDelete);

     /*
     *   Сортировка базы по атрибуту (указывается индексом)
     */
     void sort(int index);

     /*
    *   Запись в строку
    */
     String toString(int index);
     /*
     *   Все в массив строк
     */
     List<String> toStringAll();

 }