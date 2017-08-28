package com.philolog.hoplitekeyboard;

import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
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
/**
 * Created by jeremy on 2/15/17.

//examples:
 http://www.fampennings.nl/maarten/android/09keyboard/

//https://github.com/blackcj/AndroidCustomKeyboard
//http://www.blackcj.com/blog/2016/03/30/building-a-custom-android-keyboard/
//http://stackoverflow.com/questions/18224520/how-to-set-different-background-of-keys-for-android-custom-keyboard

//http://stackoverflow.com/questions/15825081/error-default-activity-not-found
 //https://code.tutsplus.com/tutorials/create-a-custom-keyboard-on-android--cms-22615
*/
 public class HopliteKeyboard extends InputMethodService implements OnKeyboardActionListener {
    public final static int NO_ACCENT = 0;
    public final static int ACUTE               = 1;
    public final static int CIRCUMFLEX          = 2;
    public final static int GRAVE               = 3;
    public final static int MACRON              = 4;
    public final static int ROUGH_BREATHING     = 5;
    public final static int SMOOTH_BREATHING    = 6;
    public final static int IOTA_SUBSCRIPT      = 7;
    public final static int SURROUNDING_PARENTHESES = 8;
    public final static int DIAERESIS           = 9;

    public final static int COMBINING_GRAVE             = 0x0300;
    public final static int COMBINING_ACUTE             = 0x0301;
    public final static int COMBINING_CIRCUMFLEX        = 0x0302;
    public final static int COMBINING_MACRON            = 0x0304;
    public final static int COMBINING_DIAERESIS         = 0x0308;
    public final static int COMBINING_SMOOTH_BREATHING  = 0x0313;
    public final static int COMBINING_ROUGH_BREATHING   = 0x0314;
    public final static int COMBINING_IOTA_SUBSCRIPT    = 0x0345;

    public HopliteKeyboardView kv;
    public Keyboard keyboard;

    private boolean caps = false;
    private String capsL = "l";
    private int unicodeMode = 0;

    @Override
    public View onCreateInputView() {
        kv = (HopliteKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.hoplitekeyboard);
        kv.setKeyboard(keyboard);
        //kv.setOnKeyboardActionListener(this);
        //InputConnection ic = getCurrentInputConnection();
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


    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        int maxSubstringForAccent = 7;

        //make sure the cursor is not between a character and its combining accents
        ExtractedText et = ic.getExtractedText(new ExtractedTextRequest(), 0);
        if (et == null) {
            return;
        }
        int selectionStart = et.selectionStart;
        int selectionEnd = et.selectionEnd;

        String strAfter1 = ic.getTextAfterCursor(maxSubstringForAccent, 0).toString();
        int ccAfter1 = numCombiningCharsAfter(strAfter1);

        if (ccAfter1 > 0) {
            if (selectionStart == selectionEnd) {
                selectionStart += ccAfter1;
                selectionEnd += ccAfter1;
            } else {
                selectionEnd += ccAfter1;
            }
            Log.d("ABC", "HAD TO FIX CURSOR INSERTED BETWEEN CHARACTER AND ITS COMBINING DIACRITICS!");
        }
        ic.setSelection(selectionStart, selectionEnd);


        switch (primaryCode) {
            case 38:
            case Keyboard.KEYCODE_DELETE:
                //cursor could be between character and combining accents...

                String strBefore = ic.getTextBeforeCursor(maxSubstringForAccent, 0).toString();

                int strBeforeLen = strBefore.length();
                if (strBeforeLen < 1) {
                    return;
                }
                int cc = numCombiningChars(strBefore);

                String strAfter = ic.getTextAfterCursor(maxSubstringForAccent, 0).toString();
                int ccAfter = numCombiningCharsAfter(strAfter);

                //Log.e("abc", "to delete: " + cc + ", after: " + ccAfter);
                ic.deleteSurroundingText(cc+1, ccAfter);
                break;
            case 42://Keyboard.KEYCODE_SHIFT:
                /*
                //caps = !caps;
                //kv.caps = !kv.caps;

                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
*/
                Keyboard keyboard;

                if (capsL == "l") {
                    keyboard = new Keyboard(getBaseContext(), R.xml.hoplitekeyboardupper);
                    capsL = "u";
                }
                else if (capsL == "u")
                {
                    keyboard = new Keyboard(getBaseContext(), R.xml.hoplitekeyboardmisc);
                    capsL = "m";
                }
                else
                {
                    keyboard = new Keyboard(getBaseContext(), R.xml.hoplitekeyboard);
                    capsL = "l";
                }
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                kv.invalidateAllKeys();

                break;
            case 49:
                InputMethodManager imeManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imeManager.switchToNextInputMethod(getToken(), false /* onlyCurrentIme */);
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char) primaryCode;

                //if (Character.isLetter(code) && caps) {
                //    code = Character.toUpperCase(code);
               // }


                String s = "";
                if (primaryCode == 1) {
                    s = "α";
                } else if (primaryCode == 2) {
                    s = "β";
                } else if (primaryCode == 3) {
                    s = "γ";
                } else if (primaryCode == 4) {
                    s = "δ";
                } else if (primaryCode == 5) {
                    s = "ε";
                } else if (primaryCode == 6) {
                    s = "ζ";
                } else if (primaryCode == 7) {
                    s = "η";
                } else if (primaryCode == 8) {
                    s = "θ";
                } else if (primaryCode == 9) {
                    s = "ι";
                } else if (primaryCode == 10) {
                    s = "κ";
                } else if (primaryCode == 11) {
                    s = "λ";
                } else if (primaryCode == 12) {
                    s = "μ";
                } else if (primaryCode == 13) {
                    s = "ν";
                } else if (primaryCode == 14) {
                    s = "ξ";
                } else if (primaryCode == 15) {
                    s = "ο";
                } else if (primaryCode == 16) {
                    s = "π";
                } else if (primaryCode == 17) {
                    s = "ρ";
                } else if (primaryCode == 18) {
                    s = "σ";
                } else if (primaryCode == 19) {
                    s = "τ";
                } else if (primaryCode == 20) {
                    s = "υ";
                } else if (primaryCode == 21) {
                    s = "φ";
                } else if (primaryCode == 22) {
                    s = "χ";
                } else if (primaryCode == 23) {
                    s = "ψ";
                } else if (primaryCode == 24) {
                    s = "ω";
                } else if (primaryCode == 25) {
                    s = "ς";
                }


                else if( primaryCode == 51 ) {
                    s = "Α";
                }  else if( primaryCode == 52 ) {
                    s = "Β";
                }  else if( primaryCode == 53 ) {
                    s = "Γ";
                }  else if( primaryCode == 54 ) {
                    s = "Δ";
                }  else if( primaryCode == 55 ) {
                    s = "Ε";
                }  else if( primaryCode == 56 ) {
                    s = "Ζ";
                }  else if( primaryCode == 57 ) {
                    s = "Η";
                }  else if( primaryCode == 58 ) {
                    s = "Θ";
                }  else if( primaryCode == 59 ) {
                    s = "Ι";
                }  else if( primaryCode == 60 ) {
                    s = "Κ";
                }  else if( primaryCode == 61) {
                    s = "Λ";
                }  else if( primaryCode == 62 ) {
                    s = "Μ";
                }  else if( primaryCode == 63 ) {
                    s = "Ν";
                }  else if( primaryCode == 64 ) {
                    s = "Ξ";
                }  else if( primaryCode == 65 ) {
                    s = "Ο";
                }  else if( primaryCode == 66 ) {
                    s = "Π";
                }  else if( primaryCode == 67 ) {
                    s = "Ρ";
                }  else if( primaryCode == 68 ) {
                    s = "Σ";
                }  else if( primaryCode == 69 ) {
                    s = "Τ";
                }  else if( primaryCode == 70 ) {
                    s = "Υ";
                }  else if( primaryCode == 71 ) {
                    s = "Φ";
                }  else if( primaryCode == 72 ) {
                    s = "Χ";
                }  else if( primaryCode == 73 ) {
                    s = "Ψ";
                }  else if( primaryCode == 74 ) {
                    s = "Ω";
                }



                else if( primaryCode == 125 ) {
                    s = "Ϲ";
                }  else if( primaryCode == 105 ) {
                    s = "ϲ";
                }  else if( primaryCode == 117 ) {
                    s = "Ϙ";
                }  else if( primaryCode == 119 ) {
                    s = "ϙ";
                }  else if( primaryCode == 120 ) {
                    s = "Ϝ";
                }  else if( primaryCode == 108 ) {
                    s = "ϝ";
                }  else if( primaryCode == 109 ) {
                    s = "Ϛ";
                }  else if( primaryCode == 115 ) {
                    s = "ϛ";
                }  else if( primaryCode == 116 ) {
                    s = "ϐ";
                }  else if( primaryCode == 101 ) {
                    s = "Ϟ";
                }  else if( primaryCode == 118) {
                    s = "ϟ";
                }  else if( primaryCode == 104 ) {
                    s = "Ϡ";
                }  else if( primaryCode == 121 ) {
                    s = "ϡ";
                }  else if( primaryCode == 103 ) {
                    s = "Ϻ";
                }  else if( primaryCode == 107 ) {
                    s = "ϻ";
                }  else if( primaryCode == 114 ) {
                    s = "Ͳ";
                }  else if( primaryCode == 110 ) {
                    s = "ͳ";
                }  else if( primaryCode == 111 ) {
                    s = "Ϗ";
                }  else if( primaryCode == 106 ) {
                    s = "Ͷ";
                }  else if( primaryCode == 122 ) {
                    s = "ͷ";
                }  else if( primaryCode == 123 ) {
                    s = "Ͱ";
                }  else if( primaryCode == 124 ) {
                    s = "ͱ";
                }  else if( primaryCode == 102 ) {
                    s = "ͻ";
                }  else if( primaryCode == 113 ) {
                    s = "ͼ";
                } else if( primaryCode == 112 ) {
                    s = "ͽ";
                }


                else if (primaryCode == 39) {
                    s = " ";
                } else if (primaryCode == 35) {
                    //s = "\n";
                    final int options = this.getCurrentInputEditorInfo().imeOptions;
                    final int actionId = options & EditorInfo.IME_MASK_ACTION;

                    switch (actionId) {
                        case EditorInfo.IME_ACTION_SEARCH:
                            sendDefaultEditorAction(true);
                            break;
                        case EditorInfo.IME_ACTION_GO:
                            sendDefaultEditorAction(true);
                            break;
                        case EditorInfo.IME_ACTION_SEND:
                            sendDefaultEditorAction(true);
                            break;
                        default:
                            ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                    }
                } else if (primaryCode == 36) {
                    s = ".";
                } else if (primaryCode == 37) {
                    s = ";";
                } else if (primaryCode == 40) {
                    s = ",";
                } else if (primaryCode == 41) {
                    s = "·";
                }

        else if (primaryCode == 75) {
            s = "(";

        } else if (primaryCode == 76) {
            s = ")";

        } else if (primaryCode == 77) {
            s = "’";
        } else if (primaryCode == 78) {
                    s = "-";
        } else if (primaryCode == 79) {
                    s = "—";
                }

                else if (primaryCode == 26) { //parentheses
                    localAccentLetter(ic, 0, SURROUNDING_PARENTHESES);
                } else if (primaryCode == 27) { //rough breathing
                    localAccentLetter(ic, 0, ROUGH_BREATHING);
                } else if (primaryCode == 28) { //smooth breathing
                    localAccentLetter(ic, 0, SMOOTH_BREATHING);
                } else if (primaryCode == 29) { //acute
                    localAccentLetter(ic, 0, ACUTE);
                } else if (primaryCode == 30) { //circumflex
                    localAccentLetter(ic, 0, CIRCUMFLEX);
                } else if (primaryCode == 33) { //macron
                    localAccentLetter(ic, 0, MACRON);
                } else if (primaryCode == 32) { //iota subscript
                    localAccentLetter(ic, 0, IOTA_SUBSCRIPT);
                } else if (primaryCode == 34) { //iota subscript
                    localAccentLetter(ic, 0, GRAVE);
                } else if (primaryCode == 31) { //iota subscript
                    localAccentLetter(ic, 0, DIAERESIS);
                }

                if (!s.equals("")) {
                    /*
                    if (caps)
                    {
                        s = s.toUpperCase();
                    }
                    */
                    ic.commitText(s, 1);
                }

        }
    }

    public boolean isCombiningCharacter(char s)
    {
        //test this with a visible character: s == 0x03B2 ||
        if (s == COMBINING_GRAVE ||
                s == COMBINING_ACUTE ||
                s == COMBINING_CIRCUMFLEX ||
                s == COMBINING_MACRON ||
                s == COMBINING_DIAERESIS ||
                s == COMBINING_SMOOTH_BREATHING ||
                s == COMBINING_ROUGH_BREATHING ||
                s == COMBINING_IOTA_SUBSCRIPT)
            return true;
        else
            return false;
    }

    public int numCombiningChars(String s)
    {
        int combiningChars = 0;
        int sLen = s.length();
        for (int i = sLen; i > 0; i--)
        {
            char c = s.charAt(i - 1);
            if (isCombiningCharacter(c)) {
                combiningChars++;
            }
            else
            {
                break;
            }
        }
        return combiningChars;
    }

    public int numCombiningCharsAfter(String s)
    {
        int combiningChars = 0;
        int sLen = s.length();
        for (int i = 0; i < sLen; i++)
        {
            char c = s.charAt(i);
            if (isCombiningCharacter(c)) {
                combiningChars++;
            }
            else
            {
                break;
            }
        }
        return combiningChars;
    }

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
            return;
        }
        int cc = numCombiningChars(strBefore);

        Log.e("abc", "NUM: " + cc + ", " + (strBeforeLen - cc - 1) + ", " + (strBeforeLen) + ", " + strBefore.substring(strBeforeLen - cc - 1, strBeforeLen));
        String accentedLetter = "";
        Log.e("abc", "unicode mode: " + unicodeMode);
        accentedLetter = gv1.addAccent(acc, unicodeMode, strBefore.substring(strBeforeLen - cc - 1, strBeforeLen));

        if (!accentedLetter.equals("")) {
            ic.setComposingRegion(selectionStart - (1+cc), selectionEnd);
            ic.setComposingText(accentedLetter, 1);
            ic.finishComposingText();
            //ic.commitText(accentedLetter, start);
        }
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
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
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }
}
