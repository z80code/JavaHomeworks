package Task01.model;

/**
 * Created by Scorpion on 23.03.2016.
 */

// класс телефона
public class Phone {

    private String num;//           Номер телефона
    // TODO если хотите хранить тип по номеру, то нужно использовать Enum
    private int type;//             Тип (например домашний, рабочий и т.д.)

    public Phone() {}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (type != phone.type) return false;
        return num.equals(phone.num);

    }

    @Override
    public int hashCode() {
        int result = num.hashCode();
        result = 31 * result + type;
        return result;
    }
}
