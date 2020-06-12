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
import android.os.Debug;
import android.preference.PreferenceManager;
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

    @Override
    public View onCreateInputView() {
        kv = (HopliteKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.hoplitekeyboard);
        kv.setKeyboard(keyboard);
        //kv.setOnKeyboardActionListener(this);
        /*
        InputConnection ic = getCurrentInputConnection();
        kal = new HKNewOnKeyboardActionListener(kv);
        kal.unicodeMode = 1;
        kal.ic = getCurrentInputConnection();
        kal.ims = this;
        kv.setOnKeyboardActionListener(kal);*/
        kv.setOnKeyboardActionListener(this);


        //this removes the yellow preview when key is pressed.
        kv.setPreviewEnabled(false);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        unicodeMode = Integer.parseInt(sharedPref.getString("UnicodeMode", "0"));

        //this doesn't seem to work
        SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
                SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                          String key) {
                        // your stuff here
                        unicodeMode = Integer.parseInt(sharedPreferences.getString("UnicodeMode", "0"));
                        //Log.e("abc", "preferences changed to: " + unicodeMode);
                    }
                };
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
        unicodeMode = Integer.parseInt(sharedPref.getString("UnicodeMode", "0"));

        if (primaryCode == HKHandleKeys.HKEnterKey) {

            final int options = this.getCurrentInputEditorInfo().imeOptions;
            final int actionId = options & EditorInfo.IME_MASK_ACTION;

            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                case EditorInfo.IME_ACTION_GO:
                case EditorInfo.IME_ACTION_SEND:
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
            setKeys(this, kv);
        } else if (primaryCode == HKHandleKeys.HKGlobeKey) {
            InputMethodManager imeManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imeManager.switchToNextInputMethod(getToken(this), false /* onlyCurrentIme */);
        }
        else {
            HKHandleKeys.onKey(primaryCode, keyCodes, ic, unicodeMode);
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

    /*

    public void localAccentLetter(InputConnection ic, int start, int acc)
    {
        //seems wasteful, but performs ok
        //the only thing that lets this change on the fly
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        unicodeMode = Integer.parseInt(sharedPref.getString("UnicodeMode", "0"));

        GreekVerb gv1 = new GreekVerb();

        ExtractedText et = ic.getExtractedText(new ExtractedTextRequest(), 0);
        if (et == null) {
            return;
        }
        int selectionStart = et.selectionStart;
        int selectionEnd = et.selectionEnd;

        int maxSubstringForAccent = 7;
        String strBefore = ic.getTextBeforeCursor(maxSubstringForAccent, 0).toString();

        int strBeforeLen = strBefore.length();
        if (strBeforeLen < 1) {
            //return;
        }

        int cc = numCombiningChars(strBefore);

        //Log.e("abc", "NUM: " + cc + ", " + (strBeforeLen - cc - 1) + ", " + (strBeforeLen) + ", " + strBefore.substring(strBeforeLen - cc - 1, strBeforeLen));
        String accentedLetter = "";
        //Log.e("abc", "unicode mode: " + unicodeMode);

        if (strBeforeLen < 1)
        {
            accentedLetter = gv1.addAccent(acc, unicodeMode, "");
        }
        else {
            accentedLetter = gv1.addAccent(acc, unicodeMode, strBefore.substring(strBeforeLen - cc - 1, strBeforeLen));
        }

        if (!accentedLetter.equals("")) {
            ic.setComposingRegion(selectionStart - (1+cc), selectionEnd);
            ic.setComposingText(accentedLetter, 1);
            ic.finishComposingText();
            //ic.commitText(accentedLetter, start);
        }
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
    }*/
}
