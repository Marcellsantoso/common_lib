package com.iapps.libs.helpers;

import android.content.Context;

public class BaseApi {
	private static BaseApi	_api	= null;

	public static BaseApi getInstance(Context context) {
		if (_api == null) {
			_api = new BaseApi();
		}

		return _api;
	}

	public static final String	FB_GRAPH	= "https://graph.facebook.com/";

}
