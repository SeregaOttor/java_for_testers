package model;

public record AddressData(String id,String first, String middle, String last, String nick, String photo) {
    public AddressData() {
        this("","","","","","");
    }

    public AddressData withId(String id) {
        return new AddressData(id, this.first, this.middle, this.last, this.nick, this.photo);
    }
    public AddressData withFirst(String first) {
        return new AddressData(this.id, first, this.middle, this.last, this.nick, this.photo);
    }
    public AddressData withMiddle(String middle) {
        return new AddressData(this.id, this.first, middle, this.last, this.nick, this.photo);
    }
    public AddressData withLast(String last) {
        return new AddressData(this.id, this.first, this.middle, last, this.nick, this.photo);
    }
    public AddressData withNick(String nick) {
        return new AddressData(this.id, this.first, this.middle, this.last, nick, this.photo);
    }
    public AddressData withPhoto(String photo) {
        return new AddressData(this.id, this.first, this.middle, this.last, this.nick, photo);
    }
}
