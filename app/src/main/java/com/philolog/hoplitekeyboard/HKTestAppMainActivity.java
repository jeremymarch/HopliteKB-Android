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
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.MotionEvent;
import android.view.WindowManager;

public class HKTestAppMainActivity extends AppCompatActivity {
    public TextView mTextView, mCodePointTextView, mModeView;
    public HopliteKeyboardView mKeyboardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hk_testing_activity);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/newathu5.ttf");
        mTextView = (TextView) findViewById(R.id.mTextView);
        mModeView = (TextView) findViewById(R.id.modeView);
        mCodePointTextView = (TextView) findViewById(R.id.mCodePointTextView);
        mTextView.setTypeface(type);


        Keyboard mKeyboard= new Keyboard(this,R.xml.hoplitekeyboard);
        //draw custom keys:
        // http://stackoverflow.com/questions/18224520/how-to-set-different-background-of-keys-for-android-custom-keyboard
        // Lookup the KeyboardView
        mKeyboardView = (HopliteKeyboardView)findViewById(R.id.keyboardview);
        // Attach the keyboard to the view
        mKeyboardView.setKeyboard( mKeyboard );
        // Do not show the preview balloons
        mKeyboardView.setPreviewEnabled(false);

        mKeyboardView.setOnKeyboardActionListener(new HKLocalOnKeyboardActionListener((EditText)mTextView, mKeyboardView, getBaseContext()));

        mTextView.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // your code here....
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                openKeyboard(view, null);
                return true;
            }
        });

        int unicodeMode = mKeyboardView.getUnicodeMode();
        String mode = "";
        switch (unicodeMode)
        {
            case 0:
                mode = "Precomposed";
                break;
            case 1:
                mode = "Precomposed with PUA";
                break;
            case 2:
                mode = "Combining Only";
                break;
            default:
                mode = "Unknown";
                break;
        }
        mModeView.setText("Unicode mode: " + mode);

        mTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                String str = mTextView.getText().toString();
                String res = "";

                final int length = str.length();
                for (int offset = 0; offset < length; ) {
                    final int codepoint = str.codePointAt(offset);
                    res = res + String.format("%04X", codepoint) + " - ";

                    offset += Character.charCount(codepoint);
                }
                if (res.endsWith(" - ")) {
                    res = res.substring(0, res.length() - 3);
                }

                mCodePointTextView.setText(res);
            }
        });
    }

    public void openKeyboard(View v, Runnable onComplete)
    {
        if (mKeyboardView.getVisibility() == View.GONE) {

            Animation animation = AnimationUtils
                    .loadAnimation(HKTestAppMainActivity.this,
                            R.anim.slide_in_bottom);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.RESTART);
            animation.setInterpolator(new LinearInterpolator());
            mKeyboardView.showWithAnimation(animation, onComplete);

            mKeyboardView.setVisibility(View.VISIBLE);
            mKeyboardView.bringToFront();
            mKeyboardView.setEnabled(true);
            if( v!=null) {
                ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/newathu5.ttf");
        mTextView = (TextView) findViewById(R.id.mTextView);
        mTextView.setTypeface(type);
        //Log.e("abc", "resumed");
    }


}
