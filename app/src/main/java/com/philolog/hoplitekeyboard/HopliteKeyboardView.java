package com.philolog.hoplitekeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.animation.Animation;

import java.util.List;

import android.inputmethodservice.KeyboardView;

import android.graphics.Canvas;
import android.inputmethodservice.Keyboard.Key;
import java.util.List;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
/**
 * Created by jeremy on 3/27/16.
 * http://stackoverflow.com/questions/18224520/how-to-set-different-background-of-keys-for-android-custom-keyboard
 */
public class HopliteKeyboardView extends KeyboardView {

    public boolean mMFPressed = false;
    public boolean caps = false;

    public HopliteKeyboardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    //http://stackoverflow.com/questions/3972445/how-to-put-text-in-a-drawable
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        //background color:
        int width = this.getWidth();
        int height = this.getHeight();
        paint.setColor(Color.rgb(200, 200, 200));
        paint.setStyle(Paint.Style.FILL); //fill the background with blue color
        canvas.drawRect(0, 0, width, height, paint);

        Context context = getContext();
        List<Keyboard.Key> keys = getKeyboard().getKeys();

        Typeface tf =  Typeface.createFromAsset(context.getAssets(),"fonts/newathu5.ttf");

        for (Keyboard.Key key : keys) {
            if (key.codes[0] == 35){
                Drawable dr;
                if (key.pressed)
                {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.enterbuttondown);
                    dr.setBounds(key.x, key.y + 6, key.x + key.width, key.y + key.height);
                    paint.setColor(context.getResources().getColor(R.color.ButtonBlue));
                }
                else {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.enterbutton);
                    dr.setBounds(key.x, key.y + 6, key.x + key.width, key.y + key.height - 4);
                    paint.setColor(Color.WHITE);
                }
                dr.draw(canvas);
            }
            else if (key.codes[0] > 26 && key.codes[0] < 35){
                Drawable dr;
                if (key.pressed) {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.accentbuttondown);
                    paint.setColor(Color.WHITE);
                }
                else
                {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.accentbutton);
                    paint.setColor(Color.BLACK);
                }
                dr.setBounds(key.x, key.y + 6, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
            }
            else if (key.codes[0] == 36 || key.codes[0] == 37 || key.codes[0] == 40 || key.codes[0] == 41){
                Drawable dr;
                if (key.pressed) {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.puncbuttondown);
                    paint.setColor(Color.WHITE);
                }
                else
                {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.puncbutton);
                    paint.setColor(Color.WHITE);
                }
                dr.setBounds(key.x, key.y + 6, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
            }
            else if (key.codes[0] == 38 || key.codes[0] == 42){
                Drawable dr;
                if (key.pressed) {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.otherbuttondown);
                    paint.setColor(Color.WHITE);
                }
                else
                {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.otherbutton);
                    paint.setColor(Color.WHITE);
                }
                dr.setBounds(key.x, key.y + 6, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
            }
            else {
                Drawable dr;
                if (key.pressed) {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.normalbuttondown);
                    paint.setColor(Color.WHITE);
                }
                else {
                    dr = (Drawable) context.getResources().getDrawable(R.drawable.normalbutton);
                    paint.setColor(Color.BLACK);
                }
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
            }
            paint.setTextAlign(Paint.Align.CENTER);
            float FONT_SIZE;
            // Convert the dips to pixels
            if ( key.codes[0] == 28 || key.codes[0] == 27) {
                FONT_SIZE = 38.0f; //or 26.0?
                paint.setTypeface(tf);
            }
            else if (key.codes[0] == 29 || key.codes[0] == 34)
            {
                FONT_SIZE = 44.0f; //or 26.0?
                paint.setTypeface(tf);
            }
            else if (key.codes[0] == 32)
            {
                FONT_SIZE = 32.0f; //or 26.0?
                paint.setTypeface(tf);
            }
            else if (key.codes[0] == 33 && mMFPressed)
            {
                FONT_SIZE = 32.0f; //or 26.0?
                paint.setTypeface(tf);
            }
            else if (key.codes[0] == 35)
            {
                FONT_SIZE = 23.0f; //or 26.0?
            }
            else
            {
                FONT_SIZE = 23.0f; //or 26.0?
                paint.setTypeface(Typeface.DEFAULT);
            }

            final float scale = context.getResources().getDisplayMetrics().density;
            final int fontSizeInPx = (int) (FONT_SIZE * scale + 0.5f);
            paint.setTextSize(fontSizeInPx);//was 72px

            paint.setAntiAlias(true);
            paint.setFakeBoldText(true);
            paint.setStyle(Paint.Style.FILL);

            String s;
            int offset = 0;
            if (key.label != null) {
                if (key.codes[0] == 27) {
                    s = "῾";
                    offset = 20;
                }
                else if (key.codes[0] == 28) {
                    s = "᾿";
                    offset = 20;
                }
                else if (key.codes[0] == 29) {
                    s = "´";//"´";
                    offset = 19;
                }
                else if (key.codes[0] == 34) {
                    s = "`";//"´";
                    offset = 21;
                }
                else if (key.codes[0] == 30) {
                    s = key.label.toString();
                    offset = 2;
                }
                else if (key.codes[0] == 31) {
                    s = "—";//key.label.toString();
                    offset = 2;
                }
                else if (key.codes[0] == 32) {
                    s = "ι";//"ι";//"ͺ";
                    offset = 16;
                }
                else if (key.codes[0] == 33 && mMFPressed) {
                    s = ",";
                    offset = 5;
                }
                else {
                    s = key.label.toString();
                    if (caps == true && key.codes[0] > 0 && key.codes[0] < 25)
                    {
                        s = s.toUpperCase();
                    }
                    offset = 9;
                }
                offset = (int) (offset * scale + 0.5f); //convert dp to px
                canvas.drawText(s, key.x + (key.width / 2),
                        key.y + (key.height / 2) + offset, paint);
            } else {
                key.icon.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                key.icon.draw(canvas);
            }
        }
    }

    public int getUnicodeMode()
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        return Integer.parseInt(sharedPref.getString("UnicodeMode", "0"));
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
}
