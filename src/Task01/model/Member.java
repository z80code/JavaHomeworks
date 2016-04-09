package Task01.model;

import java.io.Serializable;

// класс персоны
public class Member implements Serializable {
        private int id;             //      Id
        private String firstname;   //      Имя
        private String lastname;    //      Фамилия
        Phone phone = new Phone();  //      Номер телефона (тоже объект)
        private String relative;    //      Список тегов (например: друг, коллега, семья и т.д)

    public Member(){};

    public Member(String fname, String lname, Phone phone, String relative) {
        this.firstname = fname;
        this.lastname = lname;
        this.relative = relative;
        this.phone = phone;
    }

    public Member(int id, String fname, String lname, String phoneNumber, int phoneType, String relative) {
        this(fname, lname, new Phone(phoneNumber, phoneType), relative);
        this.id = id;
    }

    public Member(int id, String fname, String lname, Phone phone, String relative){
        this(fname, lname, phone, relative);
        this.id = id;
    }

    public Member(String fname, String lname, String phoneNumber, int phoneType, String relative) {
        this(0, fname, lname, new Phone(phoneNumber, phoneType), relative);
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

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phone.setNum(phoneNumber);
        }

        public void setPhoneType(int type) {
            this.phone.setType(type);
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

        public Phone getPhone() {
            return phone;
        }

        public String getPhoneNumber() {
            return this.phone.getNum();
        }

        public int getPhoneType() {
            return this.phone.getType();
        }

        public String getRelative() {
            return relative;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        if (id != member.id) return false;
        if (!firstname.equals(member.firstname)) return false;
        if (!lastname.equals(member.lastname)) return false;
        if (!phone.equals(member.phone)) return false;
        return relative.equals(member.relative);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + relative.hashCode();
        return result;
    }

    public String toString() {
            int n = 28 - this.firstname.length() - this.lastname.length();
            String space = "";
            while (n-- != 0) {
                space = space.concat(" ");
            }
            return String.format("%2d %s %s %s %s %s", this.id, this.firstname, this.lastname, space, this.phone.getPhone(), this.relative);
        }

}
