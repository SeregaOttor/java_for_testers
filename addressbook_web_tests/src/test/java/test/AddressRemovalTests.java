package test;

import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressRemovalTests extends TestBase{

    @Test
    public void canRemoveAddress() {
        if (app.address().getCount() == 0){
            if (!app.groups().isGroupPresenty()){
                app.groups().createGroup(new GroupData("groups name", "group header", "group footer"));
            }app.address().createAddress(new AddressData("First name", "Middle name", "Last name", "Nickname"));
        }
        int addressCount = app.address().getCount();
        app.address().removeAddress();
        int newAddressCount = app.address().getCount();
        Assertions.assertEquals(addressCount -1, newAddressCount);
    }

    @Test
    public void canRemoveAllAddressAtOnce() {
        if (app.address().getCount() == 0){
            if (!app.groups().isGroupPresenty()){
                app.groups().createGroup(new GroupData("groups name", "group header", "group footer"));
            }app.address().createAddress(new AddressData("First name", "Middle name", "Last name", "Nickname"));
        }
        app.address().removeAllAddress();
        Assertions.assertEquals(0, app.address().getCount());
    }

}
