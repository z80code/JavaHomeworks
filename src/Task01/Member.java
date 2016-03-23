package Task01;

/**
 * Created by Scorpion on 23.03.2016.
 */

// класс персоны
public class Member {
        private int id;         //      Id
        private String fname;   //      Имя
        private String lname;   //      Фамилия
        Phone phoneNumber;      //      Номер телефона (тоже объект)
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

        public String toString() {
            int n = 28 - this.fname.length() - this.lname.length();
            String space = "";
            while (n-- != 0) {
                space = space.concat(" ");
            }
            return String.format("%2d %s %s %s %s %s", this.id, this.fname, this.lname, space, this.phoneNumber.getPhone(), this.relative);
        }

}
