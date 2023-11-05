package test.old;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class AddressRemovalTests extends TestBase {

    @Test
    public void canRemoveAddress() {
        if (app.contacts().getCount() == 0){
            if (!app.groups().isGroupPresenty()){
                app.groups().createGroup(new GroupData("", "groups name", "group header", "group footer"));
            }app.contacts().createAddress(new ContactData("","First name", "Middle name", "Last name", "Nickname","src/test/resources/images/bzz.jpg", "", "", "", "", "", "", "", ""));
        }
        var oldAddress = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        app.contacts().removeAddress(oldAddress.get(index));
        var newAddress = app.contacts().getList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress.size(), oldAddress.size()-1);
    }

    @Test
    public void canRemoveAllAddressAtOnce() {
        if (app.contacts().getCount() == 0){
            if (!app.groups().isGroupPresenty()){
                app.groups().createGroup(new GroupData("", "groups name", "group header", "group footer"));
            }app.contacts().createAddress(new ContactData("","First name", "Middle name", "Last name", "Nickname","src/test/resources/images/bzz.jpg", "", "", "", "", "", "", "", ""));
        }
        app.contacts().removeAllAddress();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
