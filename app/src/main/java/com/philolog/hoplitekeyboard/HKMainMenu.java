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
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class HKMainMenu extends AppCompatActivity {
    public TextView mTextView;
    public View menuView;
    public HopliteKeyboardView mKeyboardView;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    public void localSetTheme()
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String themeName = sharedPref.getString("HKTheme", "HKDayNight");
        if (themeName == null)
        {
            themeName = "HKDayNight";
        }

        switch(themeName)
        {
            case "HKDark":
                setTheme(R.style.HKDark);
                break;
            case "HKLight":
                setTheme(R.style.HKLight);
                break;
            default:
                setTheme(R.style.HKDayNight);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        localSetTheme();
        super.onCreate(savedInstanceState);

        //Remove title bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/newathu5.ttf");
        mTextView = (TextView) findViewById(R.id.mTextView);
        menuView = findViewById(R.id.HKMenu);
        mTextView.setTypeface(type);

        Keyboard mKeyboard= new Keyboard(this,R.xml.hoplitekeyboard);
        mKeyboardView = (HopliteKeyboardView)findViewById(R.id.keyboardview);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        mKeyboardView.soundOn = sharedPref.getBoolean("HKSoundOn", false);
        mKeyboardView.vibrateOn = sharedPref.getBoolean("HKVibrateOn", false);
        mKeyboardView.unicodeMode = mKeyboardView.getUnicodeMode();

        mKeyboardView.setKeyboard( mKeyboard );
        mKeyboardView.setPreviewEnabled(false); // do not show the preview balloons

        InputConnection ic = mTextView.onCreateInputConnection(new EditorInfo());
        mKeyboardView.setOnKeyboardActionListener(new HKLocalOnKeyboardActionListener(ic, mKeyboardView, getBaseContext()));

        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                if (key.equals("HKUnicodeMode")) {
                    int uMode = 0;
                    String tempUMode = prefs.getString("HKUnicodeMode", "0");
                    if (tempUMode != null) {
                        uMode = Integer.parseInt(tempUMode);
                    }
                    mKeyboardView.unicodeMode = uMode;
                } else if (key.equals("HKSoundOn")) {
                    mKeyboardView.soundOn = prefs.getBoolean(key, false);
                } else if (key.equals("HKVibrateOn")) {
                    mKeyboardView.vibrateOn = prefs.getBoolean(key, false);
                } else if (key.equals("HKTheme")) {
                    recreate();
                }
            };
        };
        sharedPref.registerOnSharedPreferenceChangeListener(prefListener);

        mTextView.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent motionEvent) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                openKeyboard(view, null);
                mTextView.setCursorVisible(true);
                mTextView.requestFocus();
                return true;
            }
        });
    }



    public void showAbout(View view) {
        hideKeyboard();
        // Do something in response to button
        Intent intent = new Intent(this, AboutActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = "practice history";//editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        //intent.putExtra("GameID", 1); //1 is the practice game.
        //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        overridePendingTransition (0, 0);
        startActivity(intent);
    }

    public void showSettings(View view) {
        hideKeyboard();
        // Do something in response to button
        Intent intent = new Intent(this, HKSettings.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = "practice history";//editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        //intent.putExtra("GameID", 1); //1 is the practice game.
        //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        overridePendingTransition (0, 0);
        startActivity(intent);
    }

    public void showInstallation(View view) {
        hideKeyboard();
        // Do something in response to button
        Intent intent = new Intent(this, InstallationActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = "practice history";//editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        //intent.putExtra("GameID", 1); //1 is the practice game.
        //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        overridePendingTransition (0, 0);
        startActivity(intent);
    }

    public void showTesting(View view) {
        hideKeyboard();
        // Do something in response to button
        Intent intent = new Intent(this, HKTestAppMainActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = "practice history";//editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        //intent.putExtra("GameID", 1); //1 is the practice game.
        //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        overridePendingTransition (0, 0);
        startActivity(intent);
    }

    public void openKeyboard(View v, Runnable onComplete)
    {
        if (mKeyboardView.getVisibility() == View.GONE) {

            Animation animation = AnimationUtils
                    .loadAnimation(HKMainMenu.this,
                            R.anim.slide_in_bottom);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.RESTART);
            animation.setInterpolator(new LinearInterpolator());
            mKeyboardView.showWithAnimation(animation, onComplete);

            mKeyboardView.setVisibility(View.VISIBLE);

            mKeyboardView.setEnabled(true);
            if( v!=null) {
                ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            mKeyboardView.bringToFront();
        }
    }

    public void hideKeyboard()
    {
        if (mKeyboardView.getVisibility() != View.GONE) {

            mKeyboardView.setVisibility(View.GONE);
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
