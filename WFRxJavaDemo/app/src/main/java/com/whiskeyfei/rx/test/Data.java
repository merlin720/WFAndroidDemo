package com.whiskeyfei.rx.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 16-1-14.
 */
public class Data {
    public static List<Cat> getCats() {
        List<Cat> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Cat cat = new Cat(i + "");
            List<String> strings = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                strings.add(j + "");
            }
            cat.setlist(strings);
            list.add(cat);
        }
        return list;
    }
}
