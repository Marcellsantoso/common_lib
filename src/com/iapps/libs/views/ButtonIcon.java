package com.iapps.libs.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iapps.common_library.R;

public class ButtonIcon extends LinearLayout {

	private ImageView	iconLeft, iconRight;
	private TextView	text;

	public ButtonIcon(Context context) {
		super(context, null);
	}

	public ButtonIcon(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.button_icon, this, true);

		iconLeft = (ImageView) this.findViewById(R.id.imgLeft);
		iconRight = (ImageView) this.findViewById(R.id.imgRight);
		text = (TextView) this.findViewById(R.id.tv);

		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs,
					R.styleable.buttonIcon);

			Drawable l = a.getDrawable(R.styleable.buttonIcon_iconLeftSrc);
			Drawable r = a.getDrawable(R.styleable.buttonIcon_iconRightSrc);
			String t = a.getString(R.styleable.buttonIcon_text);
			int tColor = a.getColor(
					R.styleable.buttonIcon_textColor,
					context.getResources().getColor(R.color.Gray));

			if (l != null)
				iconLeft.setImageDrawable(l);

			if (r != null)
				iconRight.setImageDrawable(r);

			if (t != null)
				text.setText(t);

			if (tColor != 0)
				text.setTextColor(tColor);

			a.recycle();
		}
	}

	public ImageView getIconLeft() {
		return iconLeft;
	}

	public ImageView getIconRight() {
		return iconRight;
	}

	public TextView getText() {
		return text;
	}

}
