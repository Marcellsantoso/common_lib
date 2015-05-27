package com.iapps.libs.helpers;

import java.net.HttpURLConnection;

public class BaseConstants {
	public static final String	GET						= "get";
	public static final String	POST					= "post";
	public static final int		TIMEOUT					= 15000;

	public static final int		STATUS_SUCCESS			= 200;
	public static final int		STATUS_BAD_REQUEST		= 400;
	public static final int		STATUS_NOT_FOUND		= 404;
	public static final int		STATUS_TIMEOUT			= HttpURLConnection.HTTP_CLIENT_TIMEOUT;
	public static final int		STATUS_NO_DATA			= HttpURLConnection.HTTP_NO_CONTENT;
	public static final int		STATUS_NO_CONNECTION	= HttpURLConnection.HTTP_NOT_ACCEPTABLE;

	public static final int		MAX_IMAGE_SIZE			= 720;
	public static final int		THUMBNAIL_SIZE			= 400;

	public static final String	DEVICE_TYPE				= "Android";

	public static final String	MIME_JPEG				= "image/JPEG";
	public static final String	MIME_PNG				= "image/PNG";
	public static final String	MIME_CSV				= "text/csv";
	public static final String	TEMP_PHOTO_FILE			= "tmp.JPEG";
	public static final String	DATE_JSON				= "yyyy-MM-dd";
	public static final String	DATE_YMD				= "yyyy-MM-dd";
	public static final String	DATE_MD					= "dd MMM";
	public static final String	DATE_MDY				= DATE_MD + ", yyyy";
	public static final String	DATE_EDMY				= "EE, dd MMMM yy";
	public static final String	DATE_EDMYHMS			= "EE, dd MMMM yyyy HH:mm:ss";
	public static final String	DATE_HA					= "h a";
	public static final String	DATE_HMA				= "h mm a";
	public static final String	DATE_HM					= "HH:mm";
	public static final String	DATE_YMDHIS				= "yyyy-MM-dd HH:mm:ss";
	public static final String	DATE_YMDHIS_GMT			= "yyyy-MM-dd HH:mm:ss";
	public static final String	DATE_FACEBOOK			= "MM/dd/yyyy";
	public static final String	YES						= "Y";
	public static final String	NO						= "N";
	public static final String	NOT_APPLICABLE			= "NA";
	public static final int		DEFAULT_PAGE			= 1;
	public static final int		DEFAULT_LIMIT			= 10;

	public static final String	MALE					= "M";
	public static final String	FEMALE					= "F";

	// in dp
	public static final float	PREVIEW_SIZE			= 250;
	public static final String	PLAY_STORE_LINK			= "https://play.google.com/store/apps/details?id=";

	// Debug state
	public static final String	LOG						= "log";
	public static final boolean	IS_DEBUGGING			= true;

	// ================================================================================
	// DatePicker
	// ================================================================================
	public static final String	KEY_DATETIMEPICKER		= "dtpicker";

}
