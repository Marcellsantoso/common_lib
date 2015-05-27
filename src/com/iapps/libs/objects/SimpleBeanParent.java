package com.iapps.libs.objects;

import java.util.ArrayList;

public class SimpleBeanParent
	extends SimpleBean {

	ArrayList<? extends SimpleBean>	childList	= new ArrayList<SimpleBean>();

	public SimpleBeanParent(int id, String name) {
		super(id, name);
	}

	public SimpleBeanParent() {
		super(0, "");
	}

	public ArrayList<? extends SimpleBean> getChildList() {
		return childList;
	}

	public SimpleBeanParent setChildList(ArrayList<? extends SimpleBean> childList) {
		this.childList = childList;

		return this;
	}
}
