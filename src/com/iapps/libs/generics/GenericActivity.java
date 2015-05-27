package com.iapps.libs.generics;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.iapps.adapters.AdapterDrawer;
import com.iapps.common_library.R;
import com.iapps.external.actionbar4guice.activity.RoboActionBarActivity;
import com.iapps.libs.helpers.BaseHelper;
import com.iapps.libs.helpers.BaseUIHelper;
import com.iapps.libs.objects.BeanDrawer;
import com.iapps.libs.views.ImageViewLoader;

public abstract class GenericActivity
	extends RoboActionBarActivity {
	private ListView				lv;
	private Toolbar					toolbar;
	private DrawerLayout			drawer;
	private ImageViewLoader			imgBg;
	private View					toolbarBg;
	private ActionBarDrawerToggle	drawerToggle;
	private AdapterDrawer			adapter;
	private ArrayList<BeanDrawer>	alDrawer		= new ArrayList<BeanDrawer>();
	private int						containerId	= 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
				.setDefaultFontPath(getString(fontPath()))
				.setFontAttrId(R.attr.fontPath)
				.build());

		setContentView(resLayout());
		lv = (ListView) findViewById(R.id.lvDrawer);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		imgBg = (ImageViewLoader) findViewById(R.id.imgbg);
		toolbarBg = (View) findViewById(R.id.toolbarBg);

		this.containerId = containerId();

		setActionBar();
		setFragment(defaultFragment());

		if (showDrawer()) {}
		else {
			drawer.setEnabled(false);
			gone(drawer);
		}
	}

	public int resLayout() {
		return R.layout.activity_base;
	}

	public int fontPath() {
		return R.string.font_normal;
	}

	public int appName() {
		return R.string.app_name;
	}

	// ================================================================================
	// ActionBar
	// ================================================================================
	private void setActionBar() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(appName());
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, appName(),
				appName());
		drawer.setDrawerListener(drawerToggle);
	}

	public Toolbar getToolbar() {
		return this.toolbar;
	}

	// ================================================================================
	// Drawer
	// ================================================================================
	public abstract boolean showDrawer();

	// ================================================================================
	// Fragment Functions
	// ================================================================================
	public int containerId() {
		return R.id.flFragment;
	}

	public abstract Fragment defaultFragment();

	public void setFragment(Fragment frag) {
		setFragment(this.containerId, frag);
	}

	/**
	 * Change to new fragment
	 * 
	 * @param frag
	 */
	public void setFragment(int containerId, Fragment frag) {
		if (containerId > 0) {
			getSupportFragmentManager().beginTransaction()
					.replace(containerId, frag).addToBackStack(null)
					.commit();

			if (getSupportActionBar() != null)
				getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			// setSupportProgressBarIndeterminateVisibility(false);

			// Hide keyboard by default when changing fragment
			BaseUIHelper.hideKeyboard(this);
		}
	}

	/**
	 * Clear all fragments
	 */
	public void clearFragment() {
		getSupportFragmentManager().popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}

	/**
	 * Remove top of fragments
	 */
	public void popBackstack() {
		getSupportFragmentManager().popBackStack();
	}

	// ================================================================================
	// Behavior Controller
	// ================================================================================
	/**
	 * Controls behavior of the back button
	 */
	@Override
	public void onBackPressed() {
		// Only close apps when there's no backstack
		if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
			finish();
		}
		else {
			super.onBackPressed();
		}
	}

	// ================================================================================
	// Generic functions
	// ================================================================================
	public void gone(View v) {
		BaseHelper.goneView(v);
	}

	public void hide(View v) {
		BaseHelper.invisibleView(v);
	}

	public void show(View v) {
		BaseHelper.visibleView(v);
	}

}
