package com.whiskeyfei.model;

import java.io.Serializable;

/**
 * Created by whiskeyfei on 15-7-9.
 */
public class DPItemModel implements Serializable {

	private static final long serialVersionUID = 1L;
	public int mItemResId;
	public String mItemTitle;
	public String mItemContent;
	public String mItemIconUrl;

	public int getItemResId() {
		return mItemResId;
	}

	public void setItemResId(int mItemResId) {
		this.mItemResId = mItemResId;
	}

	public String getItemTitle() {
		return mItemTitle;
	}

	public void setItemTitle(String mItemTitle) {
		this.mItemTitle = mItemTitle;
	}

	public String getItemContent() {
		return mItemContent;
	}

	public void setItemContent(String mItemContent) {
		this.mItemContent = mItemContent;
	}

	public String getItemIconUrl() {
		return mItemIconUrl;
	}

	public void setItemIconUrl(String mItemIconUrl) {
		this.mItemIconUrl = mItemIconUrl;
	}

	@Override
	public String toString() {
		return "DPItemModel [mItemResId=" + mItemResId + ", mItemTitle="
				+ mItemTitle + ", mItemContent=" + mItemContent
				+ ", mItemIconUrl=" + mItemIconUrl + "]";
	}
	
}
