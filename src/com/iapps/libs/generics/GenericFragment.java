package com.iapps.libs.generics;

import java.lang.reflect.Field;

import roboguice.fragment.RoboFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.iapps.common_library.R;
import com.iapps.external.betterpickers.numberpicker.NumberPickerBuilder;
import com.iapps.libs.helpers.BaseConstants;

public abstract class GenericFragment
	extends RoboFragment implements OnClickListener,
		OnItemClickListener,
		OnLongClickListener {
	private static final Field	sChildFragmentManagerField;

	// ================================================================================
	// Default functions
	// ================================================================================
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		int resLayout = setLayout();
		if (resLayout > 0) {
			View v = inflater.inflate(setLayout(), container, false);

			setHasOptionsMenu(true);

			return v;
		}

		return null;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setView(view, savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		int resLayout = setMenuLayout();
		if (resLayout > 0)
			inflater.inflate(setMenuLayout(), menu);

		if (isDebugging() && refreshPage())
			inflater.inflate(R.menu.refresh_white, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (setMenuLayout() == 0 && isDebugging() && item.getItemId() == R.id.menu_refresh) {
			onRefreshPage();
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean refreshPage() {
		return false;
	}

	public void onRefreshPage() {
	}

	public abstract boolean isDebugging();

	public abstract int setLayout();

	public abstract int setMenuLayout();

	public abstract void setView(View view, Bundle savedInstanceState);

	// ================================================================================
	// Utilities
	// ================================================================================

	// To prevent error in implementing nested fragment
	static {
		Field f = null;
		try {
			f = Fragment.class.getDeclaredField("mChildFragmentManager");
			f.setAccessible(true);
		}
		catch (NoSuchFieldException e) {
			// Error getting mChildFragmentManager field
			e.printStackTrace();
		}
		sChildFragmentManagerField = f;
	}

	protected boolean isThere() {
		if (!getUserVisibleHint() || !isVisible() || !isAdded()) {
			return false;
		}

		return true;
	}

	public GenericActivity getHome() {
		return (GenericActivity) getActivity();
	}

	// ================================================================================
	// Title
	// ================================================================================
	public void setTitle(int resTitle) {
		getActivity().setTitle(resTitle);
	}

	public void setTitle(String title) {
		getActivity().setTitle(title);
	}

	// ================================================================================
	// Fragment
	// ================================================================================
	public void clearFragment() {
		if (getHome() != null)
			getHome().clearFragment();
	}

	public void setFragment(Fragment frag) {
		if (getHome() != null)
			getHome().setFragment(frag);
	}

	public void setFragment(Fragment frag, int resParent) {
		if (getHome() != null)
			getHome().setFragment(resParent, frag);
	}

	// ================================================================================
	// Popup
	// ================================================================================
	public NumberPickerBuilder popupPicker(Fragment targetFragment) {
		NumberPickerBuilder picker = new NumberPickerBuilder()
				.setFragmentManager(getChildFragmentManager())
				.setStyleResId(R.style.BetterPickersDialogFragment)
				.setTargetFragment(targetFragment);

		return picker;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		if (sChildFragmentManagerField != null) {
			try {
				sChildFragmentManagerField.set(this, null);
			}
			catch (Exception e) {
				// Error setting mChildFragmentManager field
				e.printStackTrace();
			}
		}
	}

	// ================================================================================
	// Log
	// ================================================================================
	public void log(String text) {
		if (isDebugging())
			Log.d(BaseConstants.LOG, text);
	}

	// ================================================================================
	// Base Listener
	// ================================================================================
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
