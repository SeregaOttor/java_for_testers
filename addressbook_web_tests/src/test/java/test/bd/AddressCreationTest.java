package test.bd;// Generated by Selenium IDE

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.AddressData;
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
    var address = new AddressData()
            .withFirst(CommonFunctions.randomString(10))
            .withLast(CommonFunctions.randomString(10))
            .withPhoto(randomFile("src/test/resources/images"));
    app.address().createAddress(address);
  }

  public static List<AddressData> addressProvider() throws IOException {
    var result = new ArrayList<AddressData>(List.of());
    for (var first : List.of("", "First name")) {
      for (var middle : List.of("", "Middle name")) {
        for (var last : List.of("", "Last name")) {
          for (var nick : List.of("", "Nickname")) {
            for (var photo : List.of(randomFile("src/test/resources/images"))) {
              result.add(new AddressData().withFirst(first).withMiddle(middle).withLast(last).withNick(nick).withPhoto(photo));
            }
          }
        }
      }
    }
    var json = Files.readString(Paths.get("contacts.json"));
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(json,new TypeReference<List<AddressData>>() {});
    result.addAll(value);
    return result;
  }
  @ParameterizedTest
  @MethodSource("addressProvider")
  public void canCreateMultipleAddress(AddressData address) {
    if (app.hbm().getGroupCount() == 0){
      app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
    }
    var group = app.hbm().getGroupList().get(0);
    //var oldAddress = app.hbm().getContactList();
    var oldRelated = app.hbm().getContactInGroup(group);
    app.address().createAddress(address, group);
    //var newAddress = app.hbm().getContactList();
    var newRelated = app.hbm().getContactInGroup(group);
    Comparator<AddressData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newRelated.sort(compareById);
    var expectedList = new ArrayList<>(oldRelated);
    expectedList.add(address.withId(newRelated.get(newRelated.size() - 1).id()).withPhoto(""));
    expectedList.sort(compareById);
    Assertions.assertEquals(newRelated, expectedList);
  }

  public static List<AddressData> negativeAddressProvider() {
    var result = new ArrayList<AddressData>(List.of(
            new AddressData("","First name'", "", "","","src/test/resources/images/bzz.jpg")
    ));
    return result;
  }

  @ParameterizedTest
  @MethodSource("negativeAddressProvider")
  public void canNotCreateAddress(AddressData address) {
    int addressCount = app.address().getCount();
    app.address().createAddress(address);
    int newAddressCount = app.address().getCount();
    Assertions.assertEquals(addressCount, newAddressCount);
  }


    @Test
    void canCreateContactInGroup(){
      var address = new AddressData()
              .withFirst(CommonFunctions.randomString(10))
              .withLast(CommonFunctions.randomString(10))
              .withPhoto(randomFile("src/test/resources/images"));
      if (app.hbm().getGroupCount() == 0){
        app.hbm().createGroup(new GroupData("", "groups name", "group header", "group footer"));
      }
      var group = app.hbm().getGroupList().get(0);

      var oldRelated = app.hbm().getContactInGroup(group);
      app.address().createAddress(address, group);
      var newRelated = app.hbm().getContactInGroup(group);
      Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }





}
