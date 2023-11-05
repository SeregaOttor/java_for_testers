package model;

public record ContactData(
        String id, String first, String middle, String last, String nick,
        String photo,
        String address,
        String home, String mobile, String work, String secondary,
        String email,String email2,String email3
    ) {
    public ContactData() {
        this("","","","","","", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.first, this.middle, this.last, this.nick, this.photo, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withFirst(String first) {
        return new ContactData(this.id, first, this.middle, this.last, this.nick, this.photo, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withMiddle(String middle) {
        return new ContactData(this.id, this.first, middle, this.last, this.nick, this.photo, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withLast(String last) {
        return new ContactData(this.id, this.first, this.middle, last, this.nick, this.photo, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withNick(String nick) {
        return new ContactData(this.id, this.first, this.middle, this.last, nick, this.photo, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, photo, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, this.photo, address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, this.photo, this.address, home, this.mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, this.photo, this.address, this.home, mobile, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, this.photo, this.address, this.home, this.mobile, work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, this.photo, this.address, this.home, this.mobile, this.work, secondary, this.email, this.email2, this.email3);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, this.photo, this.address, this.home, this.mobile, this.work, this.secondary, email, this.email2, this.email3);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, this.photo, this.address, this.home, this.mobile, this.work, this.secondary, this.email, email2, this.email3);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.first, this.middle, this.last, this.nick, this.photo, this.address, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, email3);
    }
}
