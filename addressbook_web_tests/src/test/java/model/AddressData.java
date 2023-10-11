package model;

public record AddressData(String id,String first, String middle, String last, String nick) {
    public AddressData() {
        this("","","","","");
    }

    public AddressData withId(String id) {
        return new AddressData(id, this.first, this.middle, this.last, this.nick);
    }
    public AddressData withFirst(String first) {
        return new AddressData(this.id, first, this.middle, this.last, this.nick);
    }
    public AddressData withMiddle(String middle) {
        return new AddressData(this.id, this.first, middle, this.last, this.nick);
    }
    public AddressData withLast(String last) {
        return new AddressData(this.id, this.first, this.middle, last, this.nick);
    }
    public AddressData withNick(String nick) {
        return new AddressData(this.id, this.first, this.middle, this.last, nick);
    }
}
