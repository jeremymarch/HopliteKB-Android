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

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.inputmethodservice.Keyboard;

import static android.content.Context.AUDIO_SERVICE;

public class HKLocalOnKeyboardActionListener implements KeyboardView.OnKeyboardActionListener {
    public HopliteKeyboardView kv;
    public Context c;
    public InputConnection ic;

    public boolean capsLock = false;
    public boolean extraKeysLock = false;
    //public int unicodeMode = 1;

    public HKLocalOnKeyboardActionListener(InputConnection icon, HopliteKeyboardView kview, Context co) {
        ic = icon;
        kv = kview;
        c = co;
    }

    @SuppressWarnings("deprecation")
    @Override public void onKey(int primaryCode, int[] keyCodes) {
        if (ic == null || kv == null || c == null) {
            return;
        }

        if (primaryCode == HKHandleKeys.HKEnterKey) {
            //ic.commitText("\n", 1);
            ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
        } else if (primaryCode == HKHandleKeys.HKCapsKey) {
            capsLock = !capsLock;
            setKeys(c, kv );
        } else if (primaryCode == HKHandleKeys.HKExtraKey) {
            extraKeysLock = !extraKeysLock;
            capsLock = false; //force initial lowercase when pressing extra key
            setKeys(c, kv);
        }
        //else if (primaryCode == HKHandleKeys.HKGlobeKey) {
        //    InputMethodManager imeManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        //    imeManager.switchToNextInputMethod(getToken(this), false /* onlyCurrentIme */);
        //}
        else {
            HKHandleKeys.onKey(primaryCode, keyCodes, ic, kv.unicodeMode);
        }

        if (kv.soundOn)
        {
            playClick(primaryCode);
        }
        if (kv.vibrateOn)
        {
            vibrate();
        }
    }

    public void setKeys(Context baseContext, KeyboardView kv)
    {
        if (baseContext != null && kv != null) {
            Keyboard keyboard;
            int keyboard_type;
            if (capsLock) {
                if (extraKeysLock) {
                    keyboard_type = R.xml.hoplitekeyboardmiscupper;
                }
                else {
                    keyboard_type = R.xml.hoplitekeyboardupper;
                }
            }
            else {
                if (extraKeysLock) {
                    keyboard_type = R.xml.hoplitekeyboardmisc;
                }
                else {
                    keyboard_type = R.xml.hoplitekeyboard;
                }
            }
            keyboard = new Keyboard(baseContext, keyboard_type);
            kv.setKeyboard(keyboard);
            kv.setOnKeyboardActionListener(this);
            kv.invalidateAllKeys();
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

    private void vibrate() {
        Vibrator v = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
        if (v != null) {
            v.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)c.getSystemService(AUDIO_SERVICE);
        if (am != null) {
            switch (keyCode) {
                case HKHandleKeys.HKSpaceKey:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                    break;
                case HKHandleKeys.HKEnterKey:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                    break;
                case HKHandleKeys.HKDeleteKey:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                    break;
                default:
                    am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
            }
        }
    }
}
