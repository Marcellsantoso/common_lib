package com.iapps.libs.helpers;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.iapps.common_library.R;
import com.iapps.libs.objects.Response;
import com.iapps.libs.views.LoadingCompound;

public abstract class HTTPAsyncImb
	extends HTTPAsyncTask {
	private Fragment fragment;
	private LoadingCompound ld;
	private boolean displayProgress;
	private ProgressDialog mDialog;

	public HTTPAsyncImb(Fragment frag, LoadingCompound ld) {
		this.fragment = frag;
		this.ld = ld;
		setDefaultValue();
	}

	public HTTPAsyncImb(Fragment frag, boolean displayProgress) {
		this.fragment = frag;
		this.displayProgress = displayProgress;
		setDefaultValue();
	}

	public void setDefaultValue() {
		if (!BaseHelper.isEmpty(url()))
			setUrl(url());

		if (!BaseHelper.isEmpty(search()))
			setGetParams(BaseKeys.SEARCH, search());

		if (page() > 0)
			setGetParams(BaseKeys.PAGE, page());

		if (limit() >= 0)
			setGetParams(BaseKeys.LIMIT, limit());
	}

	@Override
	protected void onPreExecute() {
		if (displayProgress)
			mDialog = ProgressDialog.show(fragment.getActivity(), "", fragment.getActivity()
					.getString(R.string.iapps__loading));
	}

	@Override
	protected void onPostExecute(Response response) {
		if (!BaseHelper.isValidResponse(response, fragment))
			return;

		if (ld != null)
			ld.hide();

		if (mDialog != null)
			mDialog.dismiss();

		JSONObject json = null;
		if (ld != null)
			json = BaseHelper.handleResponse(response, ld);
		else
			json = BaseHelper.handleResponse(response, true, fragment.getActivity());

		if (json != null) {
			try {
				if (json.getInt(BaseKeys.STATUS_CODE) == 1) {
					onSuccess(json);
				}
				else {
					onFail(json);
				}

			}
			catch (JSONException e) {
				onFail(e.getMessage());
				e.printStackTrace();
			}
		}
		else {
			// Failed to parse JSON
			onFail(fragment.getString(R.string.iapps__unknown_response));
		}
	}

	public abstract void onSuccess(JSONObject j);

	public void onFail(JSONObject j) {
		try {
			onFail(j.getString(BaseKeys.STATUS_MESSAGE));
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onFail(String message) {
		if (ld != null)
			ld.showError("", message);
		else
			BaseHelper.showAlert(fragment.getActivity(), message);
	};

	public int limit() {
		return BaseConstants.DEFAULT_LIMIT;
	}

	public int page() {
		return BaseConstants.DEFAULT_PAGE;
	}

	public abstract String url();

	public String search() {
		return "";
	}
}
