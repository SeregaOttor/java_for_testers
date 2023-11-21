package test.actual;

import io.qameta.allure.Allure;
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
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0){
                if (app.hbm().getGroupCount() == 0){
                    app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
                }app.contacts().createAddress(new ContactData("","First name", "Middle name", "Last name", "Nickname","src/test/resources/images/bzz.jpg", "", "", "", "", "", "", "", ""));
            }
        });
        var oldAddress = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        var testData = new ContactData().withLast("Modified name").withPhoto(randomFile("src/test/resources/images"));
        app.contacts().modifyAddress(oldAddress.get(index), testData);
        var newAddress = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.set(index, testData.withId(oldAddress.get(index).id()).withPhoto("").withAddress("").withHome("").withMobile("").withWork("").withSecondary("").withEmail("").withEmail2("").withEmail3(""));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddress.sort(compareById);
        expectedList.sort(compareById);
        Allure.step("Checking precondition", step -> {
            Assertions.assertEquals(newAddress, expectedList);
        });
    }
}
