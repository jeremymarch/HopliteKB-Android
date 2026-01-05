/*
 * Created by Jeremy March on 5/15/17.
 * Copyright © 2017 Jeremy March. All rights reserved.
 *
 *
 *        This file is part of HoplitePolytonicKeyboardAndroid.
 *
 *        HoplitePolytonicKeyboardAndroid is free software: you can redistribute it and/or modify
 *        it under the terms of the GNU General Public License as published by
 *        the Free Software Foundation, either version 3 of the License, or
 *        (at your option) any later version.
 *
 *        HoplitePolytonicKeyboardAndroid is distributed in the hope that it will be useful,
 *        but WITHOUT ANY WARRANTY; without even the implied warranty of
 *        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *        GNU General Public License for more details.
 *
 *        You should have received a copy of the GNU General Public License
 *        along with HoplitePolytonicKeyboardAndroid.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.philolog.hoplitekeyboard;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import androidx.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;

import androidx.core.content.ContextCompat;

import java.util.List;

/*
 * http://stackoverflow.com/questions/18224520/how-to-set-different-background-of-keys-for-android-custom-keyboard
 */
public class HopliteKeyboardView extends KeyboardView {

    public boolean mMFPressed = false;
    public boolean caps = false;
    public boolean soundOn = false;
    public boolean vibrateOn = false;
    public int unicodeMode = 0;

    int keyTextColor = 0;
    int keyTextColorDown = 0;
    int diacriticTextColor = 0;
    int diacriticTextColorDown = 0;
    int punctuationTextColor = 0;
    int punctuationTextColorDown = 0;
    int enterTextColor = 0;
    int enterTextColorDown = 0;
    int specialTextColor = 0;
    int specialTextColorDown = 0;
    int spaceTextColor = 0;
    int keyboardBGColor = 0;

    private final Paint mPaint;
    private final Typeface mKeyTypeface;

    @SuppressWarnings("deprecation")
    public HopliteKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mKeyTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/newathu5.ttf");

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.keyTextColor, typedValue, true);
        keyTextColor = typedValue.data;
        theme.resolveAttribute(R.attr.keyTextColorDown, typedValue, true);
        keyTextColorDown = typedValue.data;
        theme.resolveAttribute(R.attr.diacriticTextColor, typedValue, true);
        diacriticTextColor = typedValue.data;
        theme.resolveAttribute(R.attr.diacriticTextColorDown, typedValue, true);
        diacriticTextColorDown = typedValue.data;
        theme.resolveAttribute(R.attr.punctuationTextColor, typedValue, true);
        punctuationTextColor = typedValue.data;
        theme.resolveAttribute(R.attr.punctuationTextColorDown, typedValue, true);
        punctuationTextColorDown = typedValue.data;
        theme.resolveAttribute(R.attr.enterTextColor, typedValue, true);
        enterTextColor = typedValue.data;
        theme.resolveAttribute(R.attr.enterTextColorDown, typedValue, true);
        enterTextColorDown = typedValue.data;
        theme.resolveAttribute(R.attr.specialTextColor, typedValue, true);
        specialTextColor = typedValue.data;
        theme.resolveAttribute(R.attr.specialTextColorDown, typedValue, true);
        specialTextColorDown = typedValue.data;
        theme.resolveAttribute(R.attr.spaceTextColor, typedValue, true);
        spaceTextColor = typedValue.data;
        theme.resolveAttribute(R.attr.keyboardBgColor, typedValue, true);
        keyboardBGColor = typedValue.data;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Context context = getContext();

        Paint paint = mPaint;
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        Typeface tf = mKeyTypeface;

        canvas.drawColor(keyboardBGColor);
        for (Keyboard.Key key : keys) {
            if (key.codes[0] == HKHandleKeys.HKEnterKey) {
                Drawable dr;
                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.enterbuttondown);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    paint.setColor(enterTextColorDown);
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.enterbutton);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    paint.setColor(enterTextColor);
                }
                dr.draw(canvas);
            }
            else if ( HKHandleKeys.isDiacriticKey(key.codes[0]) ) {
                Drawable dr;
                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.accentbuttondown);
                    paint.setColor(diacriticTextColorDown);
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.accentbutton);
                    paint.setColor(diacriticTextColor);
                }
                dr.setBounds(key.x, key.y + 6, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
            }
            else if ( HKHandleKeys.isPunctuationKey( key.codes[0] )) {
                Drawable dr;
                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.puncbuttondown);
                    paint.setColor(punctuationTextColorDown);
                }
                else
                {
                    dr = ContextCompat.getDrawable(context, R.drawable.puncbutton);
                    paint.setColor(punctuationTextColor);
                }

                //make top row punctuation key height shorter just like the diacritics
                if (key.codes[0] == HKHandleKeys.HKCommaKey || key.codes[0] == HKHandleKeys.HKMiddleDotKey || key.codes[0] == HKHandleKeys.HKApostropheKey || key.codes[0] == HKHandleKeys.HKEMDashKey) {
                    dr.setBounds(key.x, key.y + 6, key.x + key.width, key.y + key.height);
                }
                else {
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                }

                dr.draw(canvas);
            }
            else if (key.codes[0] == HKHandleKeys.HKCapsKey){
                Drawable dr;
                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.normalbuttondown);
                    paint.setColor(specialTextColorDown);
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.greybutton);
                    paint.setColor(specialTextColor);
                }
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);

                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.capslockicond);
                    paint.setColor(specialTextColorDown); //?
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.capslockicon);
                    paint.setColor(specialTextColorDown); //?
                }
                //Log.e("abc", key.width + " " + key.height);
                double a = ((key.width < key.height) ? key.width : key.height) * 0.75;
                if (key.width < key.height) {
                    double y = key.y + ((key.height - a) / 2);
                    double x = key.x + ((key.width - a) / 2);
                    dr.setBounds((int)x, (int) y, (int)(x + a), (int) (y + a));
                }
                else {
                    double y = key.y + ((key.height - a) / 2);
                    double x = key.x + ((key.width - a) / 2);

                    dr.setBounds((int)x, (int)y, (int)(x + a), (int)(y + a));
                }

                dr.draw(canvas);
            }
            else if (key.codes[0] == HKHandleKeys.HKGlobeKey) {
                Drawable dr;
                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.normalbuttondown);
                    paint.setColor(specialTextColorDown);
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.greybutton);
                    paint.setColor(specialTextColor);
                }
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);

                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.globeicond);
                    paint.setColor(specialTextColorDown); //?
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.globeicon);
                    paint.setColor(specialTextColorDown); //?
                }
                //Log.e("abc", key.width + " " + key.height);
                double a = ((key.width < key.height) ? key.width : key.height) * 0.55;
                if (key.width < key.height) {
                    double y = key.y + ((key.height - a) / 2);
                    double x = key.x + ((key.width - a) / 2);
                    dr.setBounds((int)x, (int) y, (int)(x + a), (int) (y + a));
                }
                else {
                    double y = key.y + ((key.height - a) / 2);
                    double x = key.x + ((key.width - a) / 2);

                    dr.setBounds((int)x, (int)y, (int)(x + a), (int)(y + a));
                }

                dr.draw(canvas);
            }
            else if (key.codes[0] == HKHandleKeys.HKDeleteKey) {
                Drawable dr;
                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.normalbuttondown);
                    paint.setColor(specialTextColorDown);
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.greybutton);
                    paint.setColor(specialTextColor);
                }
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);

                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.deleteicond);
                    paint.setColor(specialTextColorDown); //?
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.deleteicon);
                    paint.setColor(specialTextColorDown); //?
                }
                //Log.e("abc", key.width + " " + key.height);
                double a = ((key.width < key.height) ? key.width : key.height) * 0.66;
                if (key.width < key.height)
                {
                    double y = key.y + ((key.height - a) / 2);
                    double x = key.x + ((key.width - a) / 2);
                    dr.setBounds((int)x, (int) y, (int)(x + a), (int) (y + a));
                }
                else {
                    double y = key.y + ((key.height - a) / 2);
                    double x = key.x + ((key.width - a) / 2);

                    dr.setBounds((int)x, (int)y, (int)(x + a), (int)(y + a));
                }

                dr.draw(canvas);
            }
            else if (key.codes[0] == HKHandleKeys.HKExtraKey) {
                Drawable dr;
                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.normalbuttondown);
                    paint.setColor(specialTextColorDown);
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.greybutton);
                    paint.setColor(specialTextColor);
                }
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
            }
            else {
                Drawable dr;
                if (key.pressed) {
                    dr = ContextCompat.getDrawable(context, R.drawable.normalbuttondown);
                    paint.setColor(keyTextColorDown);
                }
                else {
                    dr = ContextCompat.getDrawable(context, R.drawable.normalbutton);
                    paint.setColor(keyTextColor);
                }
                //make top row punctuation key height shorter just like the diacritics
                if ((key.codes[0] > 194 && key.codes[0] < 205) || (key.codes[0] > 159 && key.codes[0] < 170) ) {
                    dr.setBounds(key.x, key.y + 6, key.x + key.width, key.y + key.height);
                }
                else {
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                }
                dr.draw(canvas);
            }

            paint.setTextAlign(Paint.Align.CENTER);
            float FONT_SIZE;
            // Convert the dips to pixels
            if ( key.codes[0] == HKHandleKeys.HKRoughBreathingKey || key.codes[0] == HKHandleKeys.HKSmoothBreathingKey) {
                FONT_SIZE = 38.0f; //or 26.0?
                paint.setTypeface(tf);
            }
            else if (key.codes[0] == HKHandleKeys.HKAcuteKey || key.codes[0] == HKHandleKeys.HKGraveKey) {
                FONT_SIZE = 44.0f; //or 26.0?
                paint.setTypeface(tf);
            }
            else if (key.codes[0] == HKHandleKeys.HKIotaSubscriptKey) {
                FONT_SIZE = 23.0f; //or 26.0?
                paint.setTypeface(Typeface.DEFAULT);
                //paint.setTypeface(tf);
            }
            else if (key.codes[0] == HKHandleKeys.HKMultipleFormsKey && mMFPressed) {
                FONT_SIZE = 32.0f; //or 26.0?
                paint.setTypeface(tf);
            }
            else if (key.codes[0] == HKHandleKeys.HKEnterKey) {
                FONT_SIZE = 23.0f; //or 26.0?
            }
            else if (key.codes[0] == HKHandleKeys.HKDiaeresisKey || key.codes[0] == HKHandleKeys.HKBreveKey) {
                FONT_SIZE = 32.0f; //or 26.0?
            }
            else {
                FONT_SIZE = 23.0f; //or 26.0?
                paint.setTypeface(Typeface.DEFAULT);
            }

            if (key.codes[0] == HKHandleKeys.HKSpaceKey) {
                paint.setColor(spaceTextColor);
            }

            final float scale = context.getResources().getDisplayMetrics().density;
            final int fontSizeInPx = (int) (FONT_SIZE * scale + 0.5f);
            paint.setTextSize(fontSizeInPx);//was 72px

            paint.setAntiAlias(true);
            paint.setFakeBoldText(true);
            paint.setStyle(Paint.Style.FILL);

            String s;
            int offset;
            if (key.label != null) {
                if (key.codes[0] == HKHandleKeys.HKRoughBreathingKey) {
                    s = "῾";
                    offset = 20;
                }
                else if (key.codes[0] == HKHandleKeys.HKSmoothBreathingKey) {
                    s = "᾿";
                    offset = 20;
                }
                else if (key.codes[0] == HKHandleKeys.HKAcuteKey) {
                    s = "´";//"´";
                    offset = 19;
                }
                else if (key.codes[0] == HKHandleKeys.HKGraveKey) {
                    s = "`";//"´";
                    offset = 21;
                }
                else if (key.codes[0] == HKHandleKeys.HKCircumflexKey) {
                    s = key.label.toString();
                    offset = 2;
                }
                else if (key.codes[0] == HKHandleKeys.HKMacronKey) {
                    s = "—";//key.label.toString();
                    offset = 4;
                }
                else if (key.codes[0] == HKHandleKeys.HKIotaSubscriptKey) {
                    s = "ι";//"ι";//"ͺ";
                    offset = 13;
                }
                else if (key.codes[0] == HKHandleKeys.HKMultipleFormsKey && mMFPressed) {
                    s = ",";
                    offset = 5;
                }
                else if (key.codes[0] == HKHandleKeys.HKDiaeresisKey) {
                    s = "¨";
                    offset = 14;
                }
                else if (key.codes[0] == HKHandleKeys.HKBreveKey) {
                    s = "˘";
                    offset = 18;
                }
                else {
                    s = key.label.toString();
                    offset = 9;
                }
                offset = (int) (offset * scale + 0.5f); //convert dp to px
                canvas.drawText(s, key.x + ((float) key.width / 2),
                        key.y + ((float) key.height / 2) + offset, paint);
            } else {
                key.icon.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                key.icon.draw(canvas);
            }
        }
    }

    public int getUnicodeMode() {
        int uMode = 0;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String tempUMode = sharedPref.getString("HKUnicodeMode", "0");
        uMode = Integer.parseInt(tempUMode);

        return uMode;
    }

    public void showWithAnimation(Animation animation, final Runnable onComplete) {
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //setVisibility(View.VISIBLE);
                postDelayed(onComplete, 200);
            }
        });

        setAnimation(animation);
    }
    public void hideKBWithAnimation(Animation animation, final Runnable onComplete) {

    }

    public void openKeyboard(View v, Runnable onComplete) {
        if (this.getVisibility() == View.GONE) {

            Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_bottom);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.RESTART);
            animation.setInterpolator(new LinearInterpolator());
            this.showWithAnimation(animation, onComplete);

            this.setVisibility(View.VISIBLE);

            this.setEnabled(true);
            ((InputMethodManager) v.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);

            this.bringToFront();
        }
    }

    public void hideKeyboard() {
        if (this.getVisibility() != View.GONE) {
            this.setVisibility(View.GONE);
        }
    }
}
