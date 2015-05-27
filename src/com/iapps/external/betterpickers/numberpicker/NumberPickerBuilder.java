package com.iapps.external.betterpickers.numberpicker;

import java.util.Vector;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.iapps.external.betterpickers.numberpicker.NumberPickerDialogFragment.NumberPickerDialogHandler;

/**
 * User: derek Date: 5/2/13 Time: 7:55 PM
 */
public class NumberPickerBuilder {

	private FragmentManager						manager;																// Required
	private Integer								styleResId;															// Required
	private Fragment							targetFragment;
	private Integer								minNumber;
	private Integer								maxNumber;
	private int									maxLength;
	private int									minLength;
	private Integer								plusMinusVisibility;
	private Integer								decimalVisibility;
	private boolean								isHideNumber;
	private boolean								isAllowZero;
	private boolean								isNormalInput;
	private String								labelText;
	private int									mReference;
	private Vector<NumberPickerDialogHandler>	mNumberPickerDialogHandlers	= new Vector<NumberPickerDialogHandler>();

	/**
	 * Attach a FragmentManager. This is required for creation of the Fragment.
	 * 
	 * @param manager the FragmentManager that handles the transaction
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setFragmentManager(FragmentManager manager) {
		this.manager = manager;
		return this;
	}

	/**
	 * Attach a style resource ID for theming. This is required for creation of the Fragment. Two
	 * stock styles are provided using R.style.BetterPickersDialogFragment and
	 * R.style.BetterPickersDialogFragment.Light
	 * 
	 * @param styleResId the style resource ID to use for theming
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setStyleResId(int styleResId) {
		this.styleResId = styleResId;
		return this;
	}

	/**
	 * Attach a target Fragment. This is optional and useful if creating a Picker within a Fragment.
	 * 
	 * @param targetFragment the Fragment to attach to
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setTargetFragment(Fragment targetFragment) {
		this.targetFragment = targetFragment;
		return this;
	}

	/**
	 * Attach a reference to this Picker instance. This is used to track multiple pickers, if the
	 * user wishes.
	 * 
	 * @param reference a user-defined int intended for Picker tracking
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setReference(int reference) {
		this.mReference = reference;
		return this;
	}

	/**
	 * Set a minimum number required
	 * 
	 * @param minNumber the minimum required number
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setMinNumber(int minNumber) {
		this.minNumber = minNumber;
		return this;
	}

	/**
	 * Set a maximum number required
	 * 
	 * @param maxNumber the maximum required number
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
		return this;
	}

	public NumberPickerBuilder setMaxLength(int maxLength) {
		this.maxLength = maxLength;
		return this;
	}

	public NumberPickerBuilder setMinLength(int minLength) {
		this.minLength = minLength;
		return this;
	}

	public NumberPickerBuilder setDisplayAsPassword(boolean isPassword) {
		if (isPassword) {
			// If password is true, hide decimal and +- button
			setDecimalVisibility(View.GONE);
			setPlusMinusVisibility(View.GONE);

			// Replace input with * by default
			isHideNumber = true;

			// For calculator, it doesn't allow zero in front
			isAllowZero = true;

			// Normal input, unlike calculator
			isNormalInput = true;
		}
		return this;
	}

	public NumberPickerBuilder setAllowZero(boolean isAllowZero) {
		this.isAllowZero = isAllowZero;
		return this;
	}

	public NumberPickerBuilder setHideNumberInput(boolean isHideInput) {
		this.isHideNumber = isHideInput;
		return this;
	}

	public NumberPickerBuilder setNormalInput(boolean isNormalInput) {
		this.isNormalInput = isNormalInput;
		return this;
	}

	/**
	 * Set the visibility of the +/- button. This takes an int corresponding to Android's
	 * View.VISIBLE, View.INVISIBLE, or View.GONE. When using View.INVISIBLE, the +/- button will
	 * still be present in the layout but be non-clickable. When set to View.GONE, the +/- button
	 * will disappear entirely, and the "0" button will occupy its space.
	 * 
	 * @param plusMinusVisibility an int corresponding to View.VISIBLE, View.INVISIBLE, or View.GONE
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setPlusMinusVisibility(int plusMinusVisibility) {
		this.plusMinusVisibility = plusMinusVisibility;
		return this;
	}

	/**
	 * Set the visibility of the decimal button. This takes an int corresponding to Android's
	 * View.VISIBLE, View.INVISIBLE, or View.GONE. When using View.INVISIBLE, the decimal button
	 * will still be present in the layout but be non-clickable. When set to View.GONE, the decimal
	 * button will disappear entirely, and the "0" button will occupy its space.
	 * 
	 * @param decimalVisibility an int corresponding to View.VISIBLE, View.INVISIBLE, or View.GONE
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setDecimalVisibility(int decimalVisibility) {
		this.decimalVisibility = decimalVisibility;
		return this;
	}

	/**
	 * Set the (optional) text shown as a label. This is useful if wanting to identify data with the
	 * number being selected.
	 * 
	 * @param labelText the String text to be shown
	 * @return the current Builder object
	 */
	public NumberPickerBuilder setLabelText(String labelText) {
		this.labelText = labelText;
		return this;
	}

	/**
	 * Attach universal objects as additional handlers for notification when the Picker is set. For
	 * most use cases, this method is not necessary as attachment to an Activity or Fragment is done
	 * automatically. If, however, you would like additional objects to subscribe to this Picker
	 * being set, attach Handlers here.
	 * 
	 * @param handler an Object implementing the appropriate Picker Handler
	 * @return the current Builder object
	 */
	public NumberPickerBuilder addNumberPickerDialogHandler(
			NumberPickerDialogHandler handler) {
		this.mNumberPickerDialogHandlers.add(handler);
		return this;
	}

	/**
	 * Remove objects previously added as handlers.
	 * 
	 * @param handler the Object to remove
	 * @return the current Builder object
	 */
	public NumberPickerBuilder removeNumberPickerDialogHandler(
			NumberPickerDialogHandler handler) {
		this.mNumberPickerDialogHandlers.remove(handler);
		return this;
	}

	/**
	 * Instantiate and show the Picker
	 */
	public void show() {
		if (manager == null || styleResId == null) {
			Log.e("NumberPickerBuilder",
					"setFragmentManager() and setStyleResId() must be called.");
			return;
		}
		final FragmentTransaction ft = manager.beginTransaction();
		final Fragment prev = manager.findFragmentByTag("number_dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);

		final NumberPickerDialogFragment fragment = NumberPickerDialogFragment
				.newInstance(mReference, styleResId, minNumber, maxNumber,
						plusMinusVisibility, decimalVisibility, labelText);
		fragment.setMaxLength(maxLength);
		fragment.setMinLength(minLength);
		fragment.setHideNumber(isHideNumber);
		fragment.setAllowZero(isAllowZero);
		fragment.setNormalInput(isNormalInput);

		if (targetFragment != null) {
			fragment.setTargetFragment(targetFragment, 0);
		}
		fragment.setNumberPickerDialogHandlers(mNumberPickerDialogHandlers);
		fragment.show(ft, "number_dialog");
	}
}
