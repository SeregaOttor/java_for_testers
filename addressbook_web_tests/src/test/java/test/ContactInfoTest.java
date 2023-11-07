package test;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTest extends TestBase {

    @Test
    void testPhone() {//для одного контакта
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testPhoneAll() {//для всех контактов
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
        }


    @Test
    void testPhoneAddressEmail() {//для одного контакта проверки телефонов, почтового адреса и адресов электронной почты
        if (app.hbm().getContactCount() == 0){
            if (app.hbm().getGroupCount() == 0){
                app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
            }app.contacts().createAddress(new ContactData("","First name", "Middle name", "Last name", "Nickname","src/test/resources/images/bzz.jpg", "", "", "", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var ape = app.contacts().getAddressPhoneEmail(contact);
       //var address = app.contacts().getAddress(contact);
       //var phones = app.contacts().getPhones(contact);
       //var email = app.contacts().getEmail(contact);
       //var ape = address +"\n"+ phones +"\n"+ email;
       var expected = Stream.of(contact.address(), contact.email(), contact.email2(), contact.email3(), contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, ape);
    }

    @Test
    void testPhoneAddressEmail2() {//Версия с раздеельными проверками 
        if (app.hbm().getContactCount() == 0){
            if (app.hbm().getGroupCount() == 0){
                app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
            }app.contacts().createAddress(new ContactData("","First name", "Middle name", "Last name", "Nickname","src/test/resources/images/bzz.jpg", "", "", "", "", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        //var ape = app.contacts().getAddressPhoneEmail(contact);
        var address = app.contacts().getAddress(contact);
        var phones = app.contacts().getPhones(contact);
        var email = app.contacts().getEmail(contact);
        //var ape = address +"\n"+ phones +"\n"+ email;
        var expectedAddress = Stream.of(contact.address())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        var expectedPhones = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        var expectedEmail = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        //Assertions.assertEquals(expected, ape);
        Assertions.assertEquals(expectedAddress, address);
        Assertions.assertEquals(expectedPhones, phones);
        Assertions.assertEquals(expectedEmail, email);

    }
}
