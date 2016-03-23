package Task01;

/**
 * Created by Scorpion on 23.03.2016.
 */

// класс телефона
public class Phone {
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
