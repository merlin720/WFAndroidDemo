package com.whiskeyfei.rx.test;

import java.io.File;
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

    public static List<String> getFileList(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i+".txt");
        }
        return list;
    }

    public static List<String> changeList(File[] files) {
        List<String> list = new ArrayList<>();
        int len = files.length;
        for (int i = 0; i < len; i++) {
            list.add(files[i].getName());
        }
        return list;
    }
}
