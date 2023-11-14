package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var username = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUsers(email, "password");
        app.mantis().runRegisterUser(username,email);
        var messeges = app.mail().receive(email,"password", Duration.ofSeconds(10));
        var text = messeges.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            app.driver().get(url);
        }
        app.mantis().endRegisterUser();
        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedId());
    }



}
