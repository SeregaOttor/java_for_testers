package manager;

import model.AddressData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public void removeAddress() {
        openAddressPage();
        selectAddress();
        removeSelectedAddress();
    }

    private void openAddressPage(){
        click(By.linkText("home"));
    }
    private void selectAddress() {
        //click(By.name("selected[]"));
        manager.driver.findElement(By.xpath("//input[@title=\'Select (First name Last name)\']")).click();
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


}
