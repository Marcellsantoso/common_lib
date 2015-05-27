package com.iapps.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.iapps.common_library.R;
import com.iapps.libs.objects.BeanDrawer;
import com.iapps.libs.objects.SimpleBean;
import com.iapps.libs.views.ImageViewLoader;

public class AdapterDrawer
	extends BaseAdapterList {

	public AdapterDrawer(Context context, ArrayList<? extends SimpleBean> objects, int resLayout) {
		super(context, objects, resLayout);
	}

	@Override
	public Object setViewHolder() {
		// TODO Auto-generated method stub
		return new ViewHolder();
	}

	@Override
	public View initView(View view, Object objectHolder) {
		ViewHolder holder = (ViewHolder) objectHolder;
		holder.tv = (TextView) view.findViewById(R.id.tv);
		holder.img = (ImageViewLoader) view.findViewById(R.id.img);

		return view;
	}

	@Override
	public void setView(Object objectHolder, int position) {
		ViewHolder holder = (ViewHolder) objectHolder;
		BeanDrawer bean = (BeanDrawer) getItem(position);
		holder.tv.setText(bean.getName());
		if (bean.getResImg() > 0)
			holder.img.loadImage(bean.getResImg());
		else
			holder.img.setVisibility(View.GONE);
	}

	public class ViewHolder {
		TextView		tv;
		ImageViewLoader	img;
	}

}
