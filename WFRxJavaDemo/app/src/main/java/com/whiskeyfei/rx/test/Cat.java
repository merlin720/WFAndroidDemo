package com.whiskeyfei.rx.test;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-7.
 */
public class Cat {

    String mName;
    List<String> mlist;

    public List<String> getlist() {
        return mlist;
    }

    public void setlist(List<String> mlist) {
        this.mlist = mlist;
    }

    public Cat(String name) {
        mName = name;
    }

    public String toCat() {
        return mName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "mName='" + mName + '\'' +
                ", mlist=" + mlist +
                '}';
    }
}
