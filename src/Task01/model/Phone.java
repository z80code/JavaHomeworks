package Task01.model;

/**
 * Created by Scorpion on 23.03.2016.
 */

// класс телефона
public class Phone {
    // TODO переменные скрывать (private)
    // TODO лучше не сокращать имена
    private String num;//           Номер телефона
    // TODO если хотите хранить тип по номеру, то нужно использовать Enum
    private int type;//             Тип (например домашний, рабочий и т.д.)

    public Phone() {
        // TODO не занулять
//        this.num = "No set...";
//        this.type = 0;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    //TODO аналогично добавить equals и hashCode
}
