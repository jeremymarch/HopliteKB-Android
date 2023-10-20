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

package com.philolog.hoplitekb;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.os.Build;
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

    public HKLocalOnKeyboardActionListener(InputConnection icon, HopliteKeyboardView kview, Context co)
    {
        ic = icon;
        kv = kview;
        c = co;
    }

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

    private void vibrate() {

        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) c.getSystemService(c.VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) c.getSystemService(c.VIBRATOR_SERVICE)).vibrate(20);
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
