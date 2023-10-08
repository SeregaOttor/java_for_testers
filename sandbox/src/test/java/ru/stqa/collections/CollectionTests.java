package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {
    @Test
    void arrayTests() {//Массив имеет фиксированную дилину и его элементы можно только изменять
        //var array = new String[]{"a","b","c"};
        var array = new String[3];
        Assertions.assertEquals(3, array.length);
        array[0] = "a";
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTests() {//Список имеет переменную длину, элементы в него можно добавлять/удалять/заменять
        //var list = List.of("a","b","c");// является НЕмодифицированным но с заранее проинициализированными элементами
        var list = new ArrayList<>(List.of("a", "b", "c"));
        /*Assertions.assertEquals(0, list.size());

        list.add("a");
        list.add("b");
        list.add("c");*/
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0,"d");
        Assertions.assertEquals("d", list.get(0));

    }
}
