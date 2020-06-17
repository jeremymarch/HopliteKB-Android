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

import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Debug;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.preference.PreferenceManager;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.EditorInfo;

import android.view.inputmethod.InputMethodManager;

import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.media.AudioManager;
import android.content.Context;

import android.os.IBinder;
import android.app.Dialog;
import android.view.Window;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDelegate;

/**

//examples:
 http://www.fampennings.nl/maarten/android/09keyboard/

//https://github.com/blackcj/AndroidCustomKeyboard
//http://www.blackcj.com/blog/2016/03/30/building-a-custom-android-keyboard/
//http://stackoverflow.com/questions/18224520/how-to-set-different-background-of-keys-for-android-custom-keyboard

//http://stackoverflow.com/questions/15825081/error-default-activity-not-found
 //https://code.tutsplus.com/tutorials/create-a-custom-keyboard-on-android--cms-22615
*/
 public class HopliteKeyboard extends InputMethodService implements OnKeyboardActionListener {

    public HopliteKeyboardView kv;
    public Keyboard keyboard;

    private int unicodeMode = 0;
    //public InputConnection exIC = null;
    //public HKNewOnKeyboardActionListener kal = null;

    public boolean capsLock = false;
    public boolean extraKeysLock = false;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;
/*
    public void localSetTheme(Context context)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String themeName = sharedPref.getString("HKTheme", "HKDayNight");
        if (themeName == null)
        {
            themeName = "HKDayNight";
        }

        switch(themeName)
        {
            case "HKDark":
                context.setTheme(R.style.HKDark);
                break;
            case "HKLight":
                context.setTheme(R.style.HKLight);
                break;
            default:
                context.setTheme(R.style.HKDayNight);
                break;
        }
    }
*/
    @Override public void onCreate() {
        super.onCreate();
    }

    //this is called each time the keyboard comes up
    @Override
    public void onStartInputView (EditorInfo info, boolean restarting)
    {
        //calling this here allows the theme to reflect current preference settings
        setInputView(onCreateInputView());
        Log.e("abc", "onstartinputview");
    }

    @Override
    public View onCreateInputView() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = sharedPref.getString("HKTheme", "HKDayNight");
        if (theme == null)
        {
            theme = "HKDayNight";
        }
        switch(theme)
        {
            case "HKDark":
                kv = (HopliteKeyboardView)getLayoutInflater().inflate(R.layout.keyboard_dark, null);
                break;
            case "HKLight":
                kv = (HopliteKeyboardView)getLayoutInflater().inflate(R.layout.keyboard_light, null);
                break;
            default:
                kv = (HopliteKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
                break;
        }

        keyboard = new Keyboard(this, R.xml.hoplitekeyboard);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);

        //this removes the yellow preview when key is pressed.
        kv.setPreviewEnabled(false);
/* we check this on each key anyway, so no need to set it here
        unicodeMode = Integer.parseInt(sharedPref.getString("HKUnicodeMode", "0"));

        //this doesn't seem to work
        SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
                SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                          String key) {
                // your stuff here
                unicodeMode = Integer.parseInt(sharedPreferences.getString("HKUnicodeMode", "0"));
                //Log.e("abc", "preferences changed to: " + unicodeMode);
            }
        };*/

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        return kv;
    }

    public void setKeys(Context baseContext, KeyboardView kv)
    {
        if (baseContext != null && kv != null) {
            Keyboard keyboard;
            if (capsLock && !extraKeysLock) {
                keyboard = new Keyboard(baseContext, R.xml.hoplitekeyboardupper);
            } else if (capsLock && extraKeysLock) {
                keyboard = new Keyboard(baseContext, R.xml.hoplitekeyboardmiscupper);
            } else if (!capsLock && extraKeysLock) {
                keyboard = new Keyboard(baseContext, R.xml.hoplitekeyboardmisc);
            } else {
                keyboard = new Keyboard(baseContext, R.xml.hoplitekeyboard);
            }
            kv.setKeyboard(keyboard);
            kv.setOnKeyboardActionListener(this);
            kv.invalidateAllKeys();
        }
    }

    @Override public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String tempUMode = sharedPref.getString("HKUnicodeMode", "0");
        if (tempUMode != null) {
            unicodeMode = Integer.parseInt(tempUMode);
        }
        boolean soundOn = sharedPref.getBoolean("HKSoundOn", false);
        boolean vibrateOn = sharedPref.getBoolean("HKVibrateOn", false);

        if (primaryCode == HKHandleKeys.HKEnterKey) {

            final int options = this.getCurrentInputEditorInfo().imeOptions;
            final int actionId = options & EditorInfo.IME_MASK_ACTION;

            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                case EditorInfo.IME_ACTION_GO:
                case EditorInfo.IME_ACTION_SEND:
                case EditorInfo.IME_ACTION_DONE:
                case EditorInfo.IME_ACTION_NEXT:
                case EditorInfo.IME_ACTION_PREVIOUS:
                    this.sendDefaultEditorAction(true);
                    break;
                default:
                    ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                    break;
            }

        } else if (primaryCode == HKHandleKeys.HKCapsKey) {
            capsLock = !capsLock;
            setKeys(this, kv );
        } else if (primaryCode == HKHandleKeys.HKExtraKey) {
            extraKeysLock = !extraKeysLock;
            capsLock = false; //force initial lowercase when pressing extra key
            setKeys(this, kv);
        } else if (primaryCode == HKHandleKeys.HKGlobeKey) {
            InputMethodManager imeManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imeManager.switchToNextInputMethod(getToken(this), false /* onlyCurrentIme */);
        }
        else {
            HKHandleKeys.onKey(primaryCode, keyCodes, ic, unicodeMode);
        }
        if (soundOn)
        {
            playClick(primaryCode);
            Log.e("abc", "play click");
        }
        if (vibrateOn)
        {
            vibrate(primaryCode);
            Log.e("abc", "vibrate");
        }
    }

    @Override public void onPress(int arg0) {
        //this removes the yellow preview when key is pressed.
        kv.setPreviewEnabled(false);
    }

    @Override public void onRelease(int primaryCode) {
    }

    @Override public void onText(CharSequence text) {
    }

    @Override public void swipeDown() {
    }

    @Override public void swipeLeft() {
    }

    @Override public void swipeRight() {
    }

    @Override public void swipeUp() {
    }

    //needed to get token to switch to next keyboard.
    private IBinder getToken() {
        final Dialog dialog = getWindow();
        if (dialog == null) {
            return null;
        }
        final Window window = dialog.getWindow();
        if (window == null) {
            return null;
        }
        return window.getAttributes().token;
    }

    //needed to get token to switch to next keyboard.
    private IBinder getToken(InputMethodService ims) {
        final Dialog dialog = ims.getWindow();
        if (dialog == null) {
            return null;
        }
        final Window window = dialog.getWindow();
        if (window == null) {
            return null;
        }
        return window.getAttributes().token;
    }

    private void vibrate(int keyCode) {

        //if (isSetVibration) {
            if (Build.VERSION.SDK_INT >= 26) {
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(20);
            }/*
        } else {
            if (Build.VERSION.SDK_INT >= 26) {
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(0, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(0);
            }
        }*/


    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        if (am != null) {
            switch (keyCode) {
                case 32:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                    break;
                case Keyboard.KEYCODE_DONE:
                case 10:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                    break;
                case Keyboard.KEYCODE_DELETE:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                    break;
                default:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
            }
        }
    }

}
