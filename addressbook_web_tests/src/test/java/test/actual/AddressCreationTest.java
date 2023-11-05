package test.actual;// Generated by Selenium IDE

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import test.TestBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AddressCreationTest extends TestBase {
  @Test
  void canCreateContact(){
    if (app.hbm().getGroupCount() == 0){
      app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
    }
    var address = new ContactData()
            .withFirst(CommonFunctions.randomString(10))
            .withLast(CommonFunctions.randomString(10))
            .withPhoto(randomFile("src/test/resources/images"));
    app.contacts().createAddress(address);
  }

  public static List<ContactData> addressProvider() throws IOException {
    var result = new ArrayList<ContactData>(List.of());
    for (var first : List.of("", "First name")) {
      for (var middle : List.of("", "Middle name")) {
        for (var last : List.of("", "Last name")) {
          for (var nick : List.of("", "Nickname")) {
            for (var photo : List.of(randomFile("src/test/resources/images"))) {
              result.add(new ContactData().withFirst(first).withMiddle(middle).withLast(last).withNick(nick).withPhoto(photo));
            }
          }
        }
      }
    }
    var json = Files.readString(Paths.get("contacts.json"));
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(json,new TypeReference<List<ContactData>>() {});
    result.addAll(value);
    return result;
  }
  @ParameterizedTest
  @MethodSource("addressProvider")
  public void canCreateMultipleAddress(ContactData address) {
    if (app.hbm().getGroupCount() == 0){
      app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
    }
    var group = app.hbm().getGroupList().get(0);
    var oldAddress = app.hbm().getContactInGroup(group);
    app.contacts().createAddress(address, group);
    var newAddress = app.hbm().getContactInGroup(group);
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newAddress.sort(compareById);
    var expectedList = new ArrayList<>(oldAddress);
    expectedList.add(address.withId(newAddress.get(newAddress.size() - 1).id()).withPhoto(""));
    expectedList.sort(compareById);
    Assertions.assertEquals(newAddress, expectedList);
  }

  public static List<ContactData> negativeAddressProvider() {
    var result = new ArrayList<ContactData>(List.of(
            new ContactData("","First name'", "", "","","src/test/resources/images/bzz.jpg", "", "", "", "", "", "", "", "")
    ));
    return result;
  }

  @ParameterizedTest
  @MethodSource("negativeAddressProvider")
  public void canNotCreateAddress(ContactData address) {
    int addressCount = app.contacts().getCount();
    app.contacts().createAddress(address);
    int newAddressCount = app.contacts().getCount();
    Assertions.assertEquals(addressCount, newAddressCount);
  }

  @Test
  void canCreateContactInGroup2(){
    var address = new ContactData()
            .withFirst(CommonFunctions.randomString(10))
            .withLast(CommonFunctions.randomString(10))
            .withPhoto(randomFile("src/test/resources/images"));
    if (app.hbm().getContactNotInGroupCount() == 0) {
      if (app.hbm().getGroupCount() == 0) {
        app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
      }
      app.contacts().createAddressNotGroup(address);
    }
    var group = app.hbm().getGroupList().get(0);
    var oldRelated = app.hbm().getContactInGroup(group);
    var minid =app.hbm().getContactNotInGroup().get(0);
    app.contacts().addAddressInGroup(minid,group);
    var newRelated = app.hbm().getContactInGroup(group);
    Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
  }

}
