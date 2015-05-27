package com.iapps.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iapps.libs.generics.Config;
import com.iapps.libs.objects.SimpleBean;

@SuppressWarnings("rawtypes")
public abstract class BaseAdapterList
	extends ArrayAdapter {
	// private ViewHolder viewHolder;
	private int	resLayout;

	@SuppressWarnings("unchecked")
	public BaseAdapterList(Context context, ArrayList<? extends SimpleBean> objects, int resLayout) {
		super(context, resLayout, objects);
		this.resLayout = resLayout;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object holder;

		if (convertView == null) {
			holder = setViewHolder();
			convertView = inflateView(holder, parent, position);
		}
		else {
			holder = (Object) convertView.getTag();
		}

		setView(holder, position);

		return convertView;
	}

	private View inflateView(Object holder, ViewGroup parent, int position) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);

		final Context contextThemeWrapper = new ContextThemeWrapper(getContext(),
				this.setAppTheme());

		// clone the inflater using the ContextThemeWrapper
		LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

		View convertView = null;
		convertView = initView(localInflater.inflate(this.resLayout, parent, false), holder);
		convertView.setTag(holder);

		return convertView;
	}

	public int setAppTheme() {
		return Config.appTheme();
	}

	/**
	 * State your object holder. Declare a class first in your adapter, and return that class here
	 * @return
	 */
	public abstract Object setViewHolder();

	/**
	 * Map Views from the ViewHolder with their ids
	 * @param view
	 * @param objectHolder
	 * @return
	 */
	public abstract View initView(View view, Object objectHolder);

	/**
	 * Set values for the views
	 * @param objectHolder
	 * @param position
	 */
	public abstract void setView(Object objectHolder, int position);
}
