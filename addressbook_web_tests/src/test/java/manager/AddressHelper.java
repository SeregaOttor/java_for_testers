package manager;

import io.qameta.allure.Step;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressHelper extends HelperBase{
    public AddressHelper(ApplicationManager manager){
        super(manager);
    }
    @Step
    public void createAddress(ContactData address) {
        initAddressCreation();
        nameForm(address);
        //photoForm(); //не работает без подготовленного файла
        titleForm(address);
        telephoneForm(address);
        eMailForm(address);
        birthdayForm();
        groupForm();
        secondaryForm(address);
        submitAddressCreation();
        returnToAddressPage();
        //manager.driver.findElement(By.linkText("Logout")).click();
    }
    @Step
    public void createAddress(ContactData address, GroupData group) {
        initAddressCreation();
        nameForm(address);
        //photoForm(); //не работает без подготовленного файла
        titleForm(address);
        telephoneForm(address);
        eMailForm(address);
        birthdayForm();
        groupForm();
        selectGroup(group);
        secondaryForm(address);
        submitAddressCreation();
        returnToAddressPage();
        //manager.driver.findElement(By.linkText("Logout")).click();
    }
    public void createAddressNotGroup(ContactData address) {
        initAddressCreation();
        nameForm(address);
        submitAddressCreation();
        returnToAddressPage();
    }
    @Step
    public void addAddressInGroup(ContactData address, GroupData group) {
        openAddressPage();
        selectAddress(address);
        addToGroup(group);
        openAddressPage();
    }

    @Step
    public void removeAddressFromGroup(ContactData address, GroupData group) {
        openAddressPage();
        chooseGroup(group);
        selectAddress(address);
        removeFromGroup();

    }

    private void removeFromGroup() {
        manager.driver.findElement(By.name("remove")).click();
        manager.driver.findElement(By.id("logo")).click();
    }

    private void chooseGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
        //ptype(By.name("group"),By.xpath("//option[. = 'groups name']"));
    }

    public void addToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
        //ptype(By.name("to_group"),By.xpath("//option[. = 'groups name']"));
        manager.driver.findElement(By.name("add")).click();
        openAddressPage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    @Step
    public void removeAddress(ContactData address) {
        openAddressPage();
        selectAddress(address);
        removeSelectedAddress();
    }

    public void openAddressPage(){
        manager.driver.findElement(By.id("logo")).click();
        //click(By.linkText("home"));
    }
    public void selectAddress(ContactData address) {
        //click(By.name("selected[]"));
        //manager.driver.findElement(By.xpath("//input[@title=\'Select (First name Last name)\']")).click();
        click(By.cssSelector(String.format("input[value='%s']", address.id())));
    }
    private void removeSelectedAddress() {
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
        manager.driver.switchTo().alert().accept();
        openAddressPage();
    }
    public boolean isAddressPresent() {
        openAddressPage();
        //return manager.isElementPresent(By.name("selected[]"));
        return manager.isElementPresent(By.xpath("//input[@title=\'Select (First name Last name)\']"));
    }
    private void initAddressCreation() {
        click(By.linkText("add new"));
    }
    private void nameForm(ContactData address) {
        type(By.name("firstname"), address.first());
        type(By.name("middlename"), address.middle());
        type(By.name("lastname"), address.last());
        type(By.name("nickname"), address.nick());
        attach(By.name("photo"), address.photo());
    }
    private void photoForm() {
        {
            WebElement addFile = manager.driver.findElement(By.xpath(".//input[@type='file']"));
            addFile.sendKeys("C:\\good.jpg");
        }
    }
    private void titleForm(ContactData address) {
        type(By.name("title"), "Title");
        type(By.name("company"), "Company");
        type(By.name("address"), address.address());
    }
    private void telephoneForm(ContactData address) {
        type(By.name("home"), address.home());
        type(By.name("mobile"), address.mobile());
        type(By.name("work"), address.work());
        type(By.name("fax"), "Fax");
    }

    private void eMailForm(ContactData address) {
        type(By.name("email"), address.email());
        type(By.name("email2"), address.email2());
        type(By.name("email3"), address.email3());
        type(By.name("homepage"), "Homepage");
    }

    private void birthdayForm() {
        ptype(By.name("bday"),By.xpath("//option[@value=\'1\']"));
        ptype(By.name("bmonth"),By.xpath("//option[@value=\'April\']"));
        type(By.name("byear"),"1999");
        ptype(By.name("aday"),By.xpath("(//option[@value=\'1\'])[2]"));
        ptype(By.name("amonth"),By.xpath("(//option[@value=\'April\'])[2]"));
        type(By.name("ayear"),"1999");
    }
    private void groupForm() {
        //ptype(By.name("new_group"),By.xpath("//option[@value=\'[none]\']"));
        ptype(By.name("new_group"),By.xpath("//option[. = 'groups name']"));
        //driver.findElement(By.xpath("//option[contains(.,\'groups name\')]")).click();
    }
    private void secondaryForm(ContactData address) {
        type(By.name("address2"),"Address");
        type(By.name("phone2"), address.secondary());
        type(By.name("notes"),"Notes");
    }
    private void submitAddressCreation() {
        click(By.name("submit"));
    }
    private void returnToAddressPage() {
        click(By.linkText("home page"));
    }


    public int getCount() {
        openAddressPage();
        return manager.driver.findElements(By.name("selected[]")).size();
        //return manager.driver.findElement(By.xpath("//span[@id="search_count"]"));

    }
    @Step
    public void removeAllAddress() {
        openAddressPage();
        massSelectAddress();
        removeSelectedAddress();
    }

    private void selectAllAddress() {//на случай если надо несколько
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
    private void massSelectAddress() {
        click(By.xpath("//input[@id=\'MassCB\']"));
    }

    public List<ContactData> getList() {
        openAddressPage();
        var address = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));
        for (var td : trs) {
            //var last = td.getText();
            //var first = td.getText();
            //var last = td.findElement(By.cssSelector("tr.td[2]")).getText();
            //var first = td.findElement(By.cssSelector("tr.td[3]")).getText();
            var chackbox = td.findElement(By.name("selected[]"));
            var id = chackbox.getAttribute("value");
            var last = td.findElement(By.xpath("td[2]")).getText();
            var first = td.findElement(By.xpath("td[3]")).getText();
            address.add(new ContactData().withId(id).withLast(last).withFirst(first));
        }
        return address;
    }
    @Step
    public void modifyAddress(ContactData address, ContactData modifiedAddress) {
        openAddressPage();
        //selectAddress(address); не требуется так как едит не зависит от выбраной галки
        initAddressModification(address);
        nameForm(modifiedAddress);
        submitAddressModification();
        returnToAddressPage();
    }

    private void submitAddressModification() {
        click(By.name("update"));
    }
    private void initAddressModification(ContactData address) {
        click(By.cssSelector(String.format("a[href=\'edit.php?id=%s\']", address.id())));
        //click(By.xpath("//img[@title=\'Edit\']"));
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id())
                )).getText();
    }

    public Map<String,String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row: rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id,phones);
        }
        return result;
    }
    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id())
        )).getText();
    }
    public String getEmail(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id())
        )).getText();
    }
    public String getAddressPhoneEmail(ContactData contact) {
        var addr = manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
        var email = manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
        var phone = manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
        var expected = Stream.of(addr,email,phone)
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        return expected;
    }
}

