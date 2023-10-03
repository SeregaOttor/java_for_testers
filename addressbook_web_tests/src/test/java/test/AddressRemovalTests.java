package test;

import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class AddressRemovalTests extends TestBase{

    @Test
    public void canRemoveAddress() {
        if (!app.address().isAddressPresent()){
            if (!app.groups().isGroupPresenty()){
                app.groups().createGroup(new GroupData("groups name", "group header", "group footer"));
            }app.address().createAddress(new AddressData("First name", "Middle name", "Last name", "Nickname"));
        }
        app.address().removeAddress();
    }
}
