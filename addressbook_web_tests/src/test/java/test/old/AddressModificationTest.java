package test.old;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddressModificationTest extends TestBase {
    @Test
    void canAddressGroup() {
        if (app.contacts().getCount() == 0){
            if (!app.groups().isGroupPresenty()){
                app.groups().createGroup(new GroupData("", "groups name", "group header", "group footer"));
            }app.contacts().createAddress(new ContactData("","First name", "Middle name", "Last name", "Nickname","src/test/resources/images/bzz.jpg", "", "", "", "", "", "", "", ""));
        }
        var oldAddress = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        var testData = new ContactData().withLast("Modified name").withPhoto(randomFile("src/test/resources/images"));
        app.contacts().modifyAddress(oldAddress.get(index), testData);
        var newAddress = app.contacts().getList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.set(index, testData.withId(oldAddress.get(index).id()).withPhoto(""));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddress.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddress, expectedList);
    }
}
