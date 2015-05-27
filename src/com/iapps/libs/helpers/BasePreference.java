package com.iapps.libs.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class BasePreference {

	public Context	context;

	public BasePreference() {}

	// ================================================================================
	// Base Preference
	// ================================================================================
	private SharedPreferences getPreference() {
		return context.getSharedPreferences(setPrefName(), Context.MODE_PRIVATE);
	}

	private SharedPreferences.Editor editPreference() {
		return getPreference().edit();
	}

	public void clear() {
		editPreference().clear().commit();
	}

	public abstract String setPrefName();

	// ================================================================================
	// Getter setter
	// ================================================================================
	public void setString(String key, String text) {
		editPreference().putString(key, text).commit();
	}

	public String getString(String key) {
		return getPreference().getString(key, "");
	}

	public void setInt(String key, int value) {
		editPreference().putInt(key, value).commit();
	}

	public int getInt(String key) {
		return getPreference().getInt(key, 0);
	}

	public void setBoolean(String key, boolean bool) {
		editPreference().putBoolean(key, bool).commit();
	}

	public boolean getBoolean(String key) {
		return getPreference().getBoolean(key, false);
	}
}
