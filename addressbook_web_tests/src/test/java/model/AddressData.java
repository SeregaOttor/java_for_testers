package model;

public record AddressData(String first, String middle, String last, String nick) {
    public AddressData() {
        this("","","","");
    }
}
