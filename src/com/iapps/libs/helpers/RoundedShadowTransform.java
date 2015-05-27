package com.iapps.libs.helpers;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

public class RoundedShadowTransform implements Transformation {
	private final int	radius;
	private final int	margin;

	public RoundedShadowTransform(final int radius, final int margin) {
		this.radius = radius;
		this.margin = margin;
	}

	@Override
	public Bitmap transform(final Bitmap bitmap) {
		// TODO - fix this
		// Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
		// bitmap.getHeight(), Config.ARGB_8888);
		// Canvas canvas = new Canvas(output);
		//
		// final int color = 0xff424242;
		// final Paint paint = new Paint();
		// final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		// final RectF rectF = new RectF(rect);
		// // final float Px = Round;
		//
		// final Rect bottomRect = new Rect(0, bitmap.getHeight() / 2,
		// bitmap.getWidth(), bitmap.getHeight());
		//
		// paint.setAntiAlias(true);
		// canvas.drawARGB(0, 0, 0, 0);
		// paint.setColor(color);
		// canvas.drawRoundRect(rectF, 10000, 10000, paint);
		// // Fill in upper right corner
		// // canvas.drawRect(topRightRect, paint);
		// // Fill in bottom corners
		// canvas.drawRect(bottomRect, paint);
		//
		// paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		// canvas.drawBitmap(bitmap, rect, rect, paint);
		// if (bitmap != output) {
		// bitmap.recycle();
		// }
		// return output;
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);

		canvas.drawRoundRect(rectF, radius, radius, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		bitmap.recycle();

		return output;
	}

	@Override
	public String key() {
		return "rounded(radius=" + radius + ", margin=" + margin + ")";
	}
}
