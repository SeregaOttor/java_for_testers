package manager;

import model.AddressData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AddressHelper extends HelperBase{
    public AddressHelper(ApplicationManager manager){
        super(manager);
    }
    public void createAddress(AddressData address) {
        initAddressCreation();
        nameForm(address);
        //photoForm(); //не работает без подготовленного файла
        titleForm();
        telephoneForm();
        eMailForm();
        birthdayForm();
        groupForm();
        secondaryForm();
        submitAddressCreation();
        returnToAddressPage();
        //manager.driver.findElement(By.linkText("Logout")).click();
    }
    public void createAddress(AddressData address,GroupData group) {
        initAddressCreation();
        nameForm(address);
        //photoForm(); //не работает без подготовленного файла
        titleForm();
        telephoneForm();
        eMailForm();
        birthdayForm();
        groupForm();
        selectGroup(group);
        secondaryForm();
        submitAddressCreation();
        returnToAddressPage();
        //manager.driver.findElement(By.linkText("Logout")).click();
    }
    public void createAddressNotGroup(AddressData address) {
        initAddressCreation();
        nameForm(address);
        submitAddressCreation();
        returnToAddressPage();
    }
    public void addAddressInGroup(AddressData address,GroupData group) {
        openAddressPage();
        selectAddress(address);
        addToGroup(group);
        openAddressPage();
    }

    public void removeAddressFromGroup(AddressData address,GroupData group) {
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

    public void removeAddress(AddressData address) {
        openAddressPage();
        selectAddress(address);
        removeSelectedAddress();
    }

    public void openAddressPage(){
        manager.driver.findElement(By.id("logo")).click();
        //click(By.linkText("home"));
    }
    public void selectAddress(AddressData address) {
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
    private void nameForm(AddressData address) {
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
    private void titleForm() {
        type(By.name("title"), "Title");
        type(By.name("company"), "Company");
        type(By.name("address"), "Address");
    }
    private void telephoneForm() {
        type(By.name("home"), "Home");
        type(By.name("mobile"), "Mobile");
        type(By.name("work"), "Work");
        type(By.name("fax"), "Fax");
    }

    private void eMailForm() {
        type(By.name("email"), "E-mail");
        type(By.name("email2"), "E-mail2");
        type(By.name("email3"), "E-mail3");
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
    private void secondaryForm() {
        type(By.name("address2"),"Address");
        type(By.name("phone2"),"Home");
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

    public List<AddressData> getList() {
        openAddressPage();
        var address = new ArrayList<AddressData>();
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
            address.add(new AddressData().withId(id).withLast(last).withFirst(first));
        }
        return address;
    }
    public void modifyAddress(AddressData address,AddressData modifiedAddress) {
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
    private void initAddressModification(AddressData address) {
        click(By.cssSelector(String.format("a[href=\'edit.php?id=%s\']", address.id())));
        //click(By.xpath("//img[@title=\'Edit\']"));
    }
}

