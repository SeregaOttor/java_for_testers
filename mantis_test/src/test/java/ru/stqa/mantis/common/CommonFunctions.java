package ru.stqa.mantis.common;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randonNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randonNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }


    public static void extractUrl(String string) {
        List<String> list = new ArrayList<>();
        String regexString = "\\b(https://|www[.])[A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
        Pattern pattern = Pattern.compile(regexString,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            list.add(string.substring(matcher.start(0),matcher.end(0)));
        }
        if (list.size() ==0) {
            System.out.println("Empty list");
            return;
        }
        for(String str:list)
            System.out.println(str);
    }

    public static String canExtractUrl(String message) {
        var text = message;
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
        return null;
    }
}
