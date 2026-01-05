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

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.media.AudioManager;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager;

@SuppressWarnings("urls")
/*
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

    public boolean capsLock = false;
    public boolean extraKeysLock = false;
    private int mLastBottomInset = 0;

    @Override public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onInitializeInterface() {
        super.onInitializeInterface();
        final Window window = getWindow().getWindow();
        if (window != null) {
            WindowCompat.setDecorFitsSystemWindows(window, false);
        }
    }

    //this is called each time the keyboard comes up
    @Override
    public void onStartInputView (EditorInfo info, boolean restarting)
    {
        //check input type here to decide to show "enter" or "search", etc. button.  Probably also for globe button
        //check here, set variable, and use that variable in onCreateInputView below to actually display/hide buttons
        //https://stackoverflow.com/questions/43938444/custom-keyboard-handling-inputtype-change

        //calling this here allows the theme to reflect current preference settings
        setInputView(onCreateInputView());
        //Log.e("abc", "onstartinputview");
    }

    @Override
    public View onCreateInputView() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String themeName = sharedPref.getString("HKTheme", "HKDayNight");

        int themeResId;
        int layoutResId = switch (themeName) {
            case "HKDark" -> {
                themeResId = R.style.HKDark;
                yield R.layout.keyboard_dark;
            }
            case "HKLight" -> {
                themeResId = R.style.HKLight;
                yield R.layout.keyboard_light;
            }
            default -> {
                themeResId = R.style.HKDayNight;
                yield R.layout.keyboard_daynight;
            }
        };

        final Context themedContext = new ContextThemeWrapper(this, themeResId);
        LayoutInflater inflater = LayoutInflater.from(themedContext);

        FrameLayout container = new FrameLayout(themedContext);

        kv = (HopliteKeyboardView) inflater.inflate(layoutResId, container, false);
        container.addView(kv);

        // Get the background color from the theme
        TypedValue background = new TypedValue();
        themedContext.getTheme().resolveAttribute(R.attr.keyboardBgColor, background, true);
        container.setBackgroundColor(background.data);

        keyboard = new Keyboard(this, R.xml.hoplitekeyboard);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);

        //this removes the yellow preview when key is pressed.
        kv.setPreviewEnabled(false);

        container.setPadding(0, 0, 0, mLastBottomInset);

        ViewCompat.setOnApplyWindowInsetsListener(container, (v, insets) -> {

            androidx.core.graphics.Insets systemBars =
                    insets.getInsets(WindowInsetsCompat.Type.systemBars());

            androidx.core.graphics.Insets systemGestures =
                    insets.getInsets(WindowInsetsCompat.Type.systemGestures());

            int bottomInset = Math.max(
                    systemBars.bottom,
                    systemGestures.bottom
            );

            mLastBottomInset = bottomInset;

            v.setPadding(
                    v.getPaddingLeft(),
                    v.getPaddingTop(),
                    v.getPaddingRight(),
                    bottomInset
            );

            return WindowInsetsCompat.CONSUMED;
        });

        container.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(@NonNull View v) {
                ViewCompat.requestApplyInsets(v);
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull View v) {
                // No-op
            }
        });

        return container;
    }

    public void setKeys(Context baseContext, KeyboardView kv) {
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

    @SuppressWarnings("deprecation")
    @Override public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null)
        {
            return;
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String tempUMode = sharedPref.getString("HKUnicodeMode", "0");
        int unicodeMode = Integer.parseInt(tempUMode);

        boolean soundOn = sharedPref.getBoolean("HKSoundOn", false);
        boolean vibrateOn = sharedPref.getBoolean("HKVibrateOn", false);

        if (primaryCode == HKHandleKeys.HKEnterKey) {
            final int options = this.getCurrentInputEditorInfo().imeOptions;
            final int actionId = options & EditorInfo.IME_MASK_ACTION;

            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                case EditorInfo.IME_ACTION_GO:
                case EditorInfo.IME_ACTION_SEND:
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
            nextKeyboard();
        }
        else {
            HKHandleKeys.onKey(primaryCode, keyCodes, ic, unicodeMode);
        }
        if (soundOn)
        {
            playClick(primaryCode);
        }
        if (vibrateOn)
        {
            vibrate();
        }
    }

    @SuppressWarnings("deprecation")
    @Override public void onPress(int arg0) {
        //this removes the yellow preview when key is pressed.
        kv.setPreviewEnabled(false);
    }

    @SuppressWarnings("deprecation")
    @Override public void onRelease(int primaryCode) {
    }

    @SuppressWarnings("deprecation")
    @Override public void onText(CharSequence text) {
    }

    @SuppressWarnings("deprecation")
    @Override public void swipeDown() {
    }

    @SuppressWarnings("deprecation")
    @Override public void swipeLeft() {
    }

    @SuppressWarnings("deprecation")
    @Override public void swipeRight() {
    }

    @SuppressWarnings("deprecation")
    @Override public void swipeUp() {
    }

    private void nextKeyboard() {
        InputMethodManager imeManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imeManager != null) {
            imeManager.switchToNextInputMethod(getToken(), false /* onlyCurrentIme */);
        }
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

    private void vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(20);
        }
    }

    private void playClick(int keyCode) {
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        if (am != null) {
            switch (keyCode) {
                case HKHandleKeys.HKSpaceKey:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                    break;
                case HKHandleKeys.HKEnterKey: //enter
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                    break;
                case HKHandleKeys.HKDeleteKey: //delete
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                    break;
                default:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
            }
        }
    }
}
