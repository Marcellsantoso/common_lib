package com.iapps.libs.objects;

import android.support.v4.app.Fragment;

import com.iapps.libs.objects.SimpleBean;

public class BeanDrawer
		extends SimpleBean {

	private int			resImg, resImgSelect;
	private Fragment	fragment;
	private boolean		selected, publicAccess;

	public BeanDrawer(int id, String name) {
		super(id, name);
	}

	public int getResImg() {
		return resImg;
	}

	public BeanDrawer setResImg(int resImg) {
		this.resImg = resImg;

		return this;
	}

	public BeanDrawer setFragment(Fragment fragment) {
		this.fragment = fragment;

		return this;
	}

	public Fragment getFragment() {
		return this.fragment;
	}

	public int getResImgSelect() {
		return resImgSelect;
	}

	public BeanDrawer setResImgSelect(int resImgSelect) {
		this.resImgSelect = resImgSelect;

		return this;
	}

	public boolean isSelected() {
		return selected;
	}

	public BeanDrawer setSelected(boolean selected) {
		this.selected = selected;

		return this;
	}

	public boolean isPublicAccess() {
		return publicAccess;
	}

	public BeanDrawer setPublicAccess(boolean restrictGuest) {
		this.publicAccess = restrictGuest;

		return this;
	}
}
