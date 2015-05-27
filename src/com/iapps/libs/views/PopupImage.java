package com.iapps.libs.views;

import roboguice.fragment.RoboDialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.iapps.common_library.R;
import com.iapps.libs.helpers.BaseHelper;

public class PopupImage extends RoboDialogFragment {
	private ImageViewLoader	img;
	private String			url;
	private int				resImg;

	public PopupImage(String url) {
		this.url = url;
	}

	public PopupImage(int resImg) {
		this.resImg = resImg;
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// shouldRedirect = true;

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.popup_image, null);

		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(view);
		builder.setCancelable(true);

		img = ((ImageViewLoader) view.findViewById(R.id.image));

		// Load image
		if (resImg > 0)
			img.loadImage(resImg);
		else if (!BaseHelper.isEmpty(url))
			img.loadImage(url);

		return builder.create();
	}
}
