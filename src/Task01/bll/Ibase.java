package Task01.bll;

import Task01.model.Member;
import Task01.model.Phone;

import java.util.List;
import java.util.Scanner;

 public interface Ibase {

     int getSelector();


     /*
     *   поиск по атрибуту (указывается индексом)
     *   заданному в поле ""
     */
     int count();

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
    *   Удаление персоны по атрибуту (указывается индексом),
    *   заданному в поле "newMember"
    */

     boolean delete(Member member);

     /*
     *   Сортировка базы по атрибуту (указывается индексом)
     */
     void sort(int index);

     /*
    *   Запись в строку
    */
     String toString(Member member);
     /*
     *   Все в массив строк
     */
     List<String> toStringAll();

 }