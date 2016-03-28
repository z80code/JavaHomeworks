package Task01.model;

/**
 * Created by Scorpion on 23.03.2016.
 */

// TODO для классов описывающего модель обязательно переопределять hashCode и equals
// класс персоны
public class Member {
        private int id;         //      Id
        // TODO лучше не сокрощать, не приветствуется на java: lastName, firstName очевиднее
        private String firstname;   //      Имя
        private String lastname;   //      Фамилия
        Phone phoneNumber;      //      Номер телефона (тоже объект)
        private String relative;//      Список тегов (например: друг, коллега, семья и т.д)

        public Member() {
            // TODO не нужно забивать пустыми данными
//            this.id = 0;
//            this.firstname = "No name.";
//            this.lastname = "No name.";
//            this.phoneNumber = new Phone();
//            this.relative = "Not set.";
        }

        public Member(String fname, String lname, Phone phoneNumber, String relative) {
            this.firstname = fname;
            this.lastname = lname;
            this.phoneNumber = phoneNumber;
            this.relative = relative;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setFname(String fname) {
            this.firstname = fname;
        }

        public void setLname(String lname) {
            this.lastname = lname;
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
            return firstname;
        }

        public String getLname() {
            return lastname;
        }

        public Phone getPhoneNumber() {
            return phoneNumber;
        }

        public String getRelative() {
            return relative;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }


        public String toString() {
            int n = 28 - this.firstname.length() - this.lastname.length();
            String space = "";
            while (n-- != 0) {
                space = space.concat(" ");
            }
            return String.format("%2d %s %s %s %s %s", this.id, this.firstname, this.lastname, space, this.phoneNumber.getPhone(), this.relative);
        }

}
