package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void ptype(By locator, By xpath) {
        click(locator);
        manager.driver.findElement(xpath).click();//By.xpath("//option[@value=\'April\']")
    }

    //Оставил как вариант на подумать над его работой
    /*protected void wtype(By locator, By xpath) {
        click(locator);
        WebElement dropdown = manager.driver.findElement(locator);
        dropdown.findElement(xpath).click();//By.xpath("//option[. = 'April']")
    }*/

}
