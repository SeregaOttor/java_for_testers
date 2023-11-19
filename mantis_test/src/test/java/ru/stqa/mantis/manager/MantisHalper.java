package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MantisHalper extends HelperBase{
    public MantisHalper(ApplicationManager manager) {
        super(manager);
    }



    public void runRegisterUser(String username, String email) {
        click(By.cssSelector("a[href=\'signup_page.php\']"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(By.cssSelector("a[href=\'login_page.php\']"));
    }
    public void endRegisterUser() {
        type(By.name("password"), "password");
        type(By.name("password_confirm"), "password");
        click(By.cssSelector("button[type='submit']"));
    }



}
