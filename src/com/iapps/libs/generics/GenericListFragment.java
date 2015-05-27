package com.iapps.libs.generics;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iapps.common_library.R;
import com.iapps.external.actionbar4guice.activity.RoboActionBarActivity;

public class GenericListFragment extends RoboActionBarActivity {

	@InjectView(tag="listViewListGeneric") protected ListView lvList;
	 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_generic_list, container, false);
	}
}
