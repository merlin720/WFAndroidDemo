package com.whiskeyfei.inter;

/**
 * Created by whiskeyfei on 15-7-24.
 */

public interface IImageChangeEvent {
    void updateImage(int id,int position);
    void onDestory();
    boolean updateState(boolean state);
}
