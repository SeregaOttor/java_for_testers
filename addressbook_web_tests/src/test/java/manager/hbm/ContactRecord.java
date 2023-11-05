package manager.hbm;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    public String first;
    @Column(name = "middlename")
    public String middle;
    @Column(name = "lastname")
    public String last;
    @Column(name = "nickname")
    public String nick;

    public String address;
    public String home;
    public String mobile;
    public String work;
    @Column(name = "phone2")
    public String secondary;

    public String email;
    public String email2;
    public String email3;


    //public Date deprecated = new Date();

    public ContactRecord() {

    }
    public ContactRecord(int id, String first, String middle, String last, String nick) {
        this.id = id;
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.nick = nick;
    }
}
