package com.example.wpeventbusdemo;

import java.util.List;

public class ItemListEvent {
	private List<Item> items;

	public ItemListEvent(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}
}
