package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {
    DeveloperMailUser user;

    @Test
    void canRegisterUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
        app.mantis().runRegisterUser(user.name(),email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(10));
        //var url = CommonFunctions.extractUrl(message);
        var text = message;
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            app.driver().get(url);
        }
        //app.driver().get(url);
        app.mantis().endRegisterUser();
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedId());
    }
    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }

}
