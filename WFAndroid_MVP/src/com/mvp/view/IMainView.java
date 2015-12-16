package com.mvp.view;

import com.mvp.base.IMVPBaseView;

/**
 * 定义MainActivity view 回调接口
 * 
 * @author whiskeyfei
 * 
 */
public interface IMainView extends IMVPBaseView {
	/**
	 * view 显示文字
	 * 
	 * @param text
	 */
	void showTextView(String text);
}
