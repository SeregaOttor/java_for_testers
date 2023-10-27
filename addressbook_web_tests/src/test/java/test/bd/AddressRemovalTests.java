package test.bd;

import common.CommonFunctions;
import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class AddressRemovalTests extends TestBase {

    @Test
    public void canRemoveAddress() {
        if (app.hbm().getContactCount() == 0){
            if (app.hbm().getGroupCount() == 0){
                app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
            }app.address().createAddress(new AddressData("","First name", "Middle name", "Last name", "Nickname","src/test/resources/images/bzz.jpg"));
        }
        var oldAddress = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        app.address().removeAddress(oldAddress.get(index));
        var newAddress = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress.size(), oldAddress.size()-1);
    }

    @Test
    public void canRemoveAddressFromGroup() {
            if (app.hbm().getContactCount() == 0) {
                if (app.hbm().getGroupCount() == 0) {
                    app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
                }
                var group = app.hbm().getGroupList().get(0);
                app.address().createAddressNotGroup(new AddressData("", "First name", "Middle name", "Last name", "Nickname", "src/test/resources/images/bzz.jpg"), group);
            }

        var group = app.hbm().getGroupList().get(0);
        app.address().selectAddress(app.hbm().getContactList().get(0));
        app.address().addToGroup(group);
        var oldAddress = app.hbm().getContactInGroup(group);
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        app.address().removeAddressFromGroup(oldAddress.get(index),group);
        var newAddress = app.hbm().getContactInGroup(group);
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress.size(), oldAddress.size()-1);
    }

    @Test
    public void canRemoveAllAddressAtOnce() {
        if (app.hbm().getContactCount() == 0){
            if (app.hbm().getGroupCount() == 0){
                app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
            }app.address().createAddress(new AddressData("","First name", "Middle name", "Last name", "Nickname","src/test/resources/images/bzz.jpg"));
        }
        app.address().removeAllAddress();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

}
