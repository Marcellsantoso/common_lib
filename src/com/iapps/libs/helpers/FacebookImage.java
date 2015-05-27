package com.iapps.libs.helpers;

import android.graphics.BitmapFactory;

import com.iapps.libs.views.ImageViewLoader;

public abstract class FacebookImage {
	private ImageViewLoader	img;
	private String			id;
	private byte[]			bAvatar;

	public FacebookImage(ImageViewLoader img, String id) {
		this.img = img;
		this.id = id;
	}

	public void execute() {
		FetchFBData get = new FetchFBData(img);
		get.setUrl(BaseApi.FB_GRAPH + id + "/" + BaseKeys.FACEBOOK_PICTURE + "?"
				+ BaseKeys.TYPE + "=" + BaseKeys.LARGE);
		get.execute();
	}

	public class FetchFBData
			extends DownloadByteAsync {

		private ImageViewLoader	img;

		public FetchFBData(ImageViewLoader img) {
			this.img = img;
		}

		@Override
		protected void onPreExecute() {
			img.showProgress();
		}

		@Override
		protected void onPostExecute(byte[] result) {
			img.hideProgress();

			if (result != null) {
				bAvatar = result;
				img.getImage().setImageBitmap(
						BitmapFactory.decodeByteArray(bAvatar, 0, bAvatar.length));
				img.hideProgress();
				onSuccess(bAvatar);
			}
			else {
				BaseHelper.showUnknownResponseError(img.getContext());
			}
		}
	}

	public abstract void onSuccess(byte[] avatar);
}
