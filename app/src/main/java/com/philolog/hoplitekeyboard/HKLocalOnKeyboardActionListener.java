package com.philolog.hoplitekeyboard;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.inputmethodservice.KeyboardView;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.util.Log;
import android.inputmethodservice.Keyboard;

/**
 * Created by jeremy on 5/13/17.
 */

public class HKLocalOnKeyboardActionListener implements KeyboardView.OnKeyboardActionListener {
    public final static int CodeDelete   = -5; // Keyboard.KEYCODE_DELETE
    public final static int CodeCancel   = -3; // Keyboard.KEYCODE_CANCEL
    public final static int CodePrev     = 55000;
    public final static int CodeAllLeft  = 55001;
    public final static int CodeLeft     = 55002;
    public final static int CodeRight    = 55003;
    public final static int CodeAllRight = 55004;
    public final static int CodeNext     = 55005;
    public final static int CodeClear    = 55006;

    public final static int NO_ACCENT = 0;
    public final static int ACUTE = 1;
    public final static int CIRCUMFLEX = 2;
    public final static int GRAVE = 3;
    public final static int MACRON = 4;
    public final static int ROUGH_BREATHING = 5;
    public final static int SMOOTH_BREATHING =6;
    public final static int IOTA_SUBSCRIPT = 7;
    public final static int SURROUNDING_PARENTHESES = 8;
    public final static int DIAERESIS = 9;

    public final static int COMBINING_GRAVE             = 0x0300;
    public final static int COMBINING_ACUTE             = 0x0301;
    public final static int COMBINING_CIRCUMFLEX        = 0x0302;
    public final static int COMBINING_MACRON            = 0x0304;
    public final static int COMBINING_DIAERESIS         = 0x0308;
    public final static int COMBINING_SMOOTH_BREATHING  = 0x0313;
    public final static int COMBINING_ROUGH_BREATHING   = 0x0314;
    public final static int COMBINING_IOTA_SUBSCRIPT    = 0x0345;

    public EditText e;
    public HopliteKeyboardView kv;
    public Context c;

    private boolean caps = false;
    private String capsL = "l";
    private String miscL = "n"; //m for misc, n for normal

    public HKLocalOnKeyboardActionListener(EditText et, HopliteKeyboardView kview, Context co)
    {
        e = et;
        kv = kview;
        c = co;
    }

    @Override public void onKey(int primaryCode, int[] keyCodes) {

        Editable editable = e.getText();
        int start = e.getSelectionStart();

        // this will prevent inserting a char between combining accents
        String str2 = editable.toString();
        start = fixCursorStart(start, str2, e);
        String s = "";
        if( primaryCode == CodeCancel ) {
            //hideCustomKeyboard();

        } /* else if( primaryCode==CodeDelete ) {
                if( editable!=null && start>0 ) editable.delete(start - 1, start);
            } else if( primaryCode==CodeClear ) {
                if( editable!=null ) editable.clear();
            } else if( primaryCode==CodeLeft ) {
                if( start>0 ) edittext.setSelection(start - 1);
            } else if( primaryCode==CodeRight ) {
                if (start < edittext.length()) edittext.setSelection(start + 1);
            } else if( primaryCode==CodeAllLeft ) {
                edittext.setSelection(0);
            } else if( primaryCode==CodeAllRight ) {
                edittext.setSelection(edittext.length());
            } else if( primaryCode==CodePrev ) {
                View focusNew= edittext.focusSearch(View.FOCUS_BACKWARD);
                if( focusNew!=null ) focusNew.requestFocus();
            } else if( primaryCode==CodeNext ) {
                View focusNew= edittext.focusSearch(View.FOCUS_FORWARD);
                if( focusNew!=null ) focusNew.requestFocus();

            } */
        else if( primaryCode == 1 ) {
            s = "α";
        }  else if( primaryCode == 2 ) {
            s = "β";
        }  else if( primaryCode == 3 ) {
            s = "γ";
        }  else if( primaryCode == 4 ) {
            s = "δ";
        }  else if( primaryCode == 5 ) {
            s = "ε";
        }  else if( primaryCode == 6 ) {
            s = "ζ";
        }  else if( primaryCode == 7 ) {
            s = "η";
        }  else if( primaryCode == 8 ) {
            s = "θ";
        }  else if( primaryCode == 9 ) {
            s = "ι";
        }  else if( primaryCode == 10 ) {
            s = "κ";
        }  else if( primaryCode == 11) {
            s = "λ";
        }  else if( primaryCode == 12 ) {
            s = "μ";
        }  else if( primaryCode == 13 ) {
            s = "ν";
        }  else if( primaryCode == 14 ) {
            s = "ξ";
        }  else if( primaryCode == 15 ) {
            s = "ο";
        }  else if( primaryCode == 16 ) {
            s = "π";
        }  else if( primaryCode == 17 ) {
            s = "ρ";
        }  else if( primaryCode == 18 ) {
            s = "σ";
        }  else if( primaryCode == 19 ) {
            s = "τ";
        }  else if( primaryCode == 20 ) {
            s = "υ";
        }  else if( primaryCode == 21 ) {
            s = "φ";
        }  else if( primaryCode == 22 ) {
            s = "χ";
        }  else if( primaryCode == 23 ) {
            s = "ψ";
        }  else if( primaryCode == 24 ) {
            s = "ω";
        }  else if( primaryCode == 25 ) {
            s = "ς";
        } else if ( primaryCode == 51 ) {
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
        } else if (primaryCode == 39) {
            s = " ";
        } else if (primaryCode == 35) {
            s = "\n";
            /*
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
            */
        } else if (primaryCode == 36) {
            s = ".";
        } else if (primaryCode == 37) {
            s = ";";
        } else if (primaryCode == 40) {
            s = ",";
        } else if (primaryCode == 41) {
            s = "·";
        } else if (primaryCode == 75) {
            s = "(";
        } else if (primaryCode == 76) {
            s = ")";
        } else if (primaryCode == 77) {
            s = "’";
        } else if (primaryCode == 78) {
            s = "-";
        } else if (primaryCode == 79) {
            s = "—";
        } else if (primaryCode == 80) {
            s = "ʹ";
        } else if (primaryCode == 81) {
            s = "͵";
        } else if (primaryCode == 82) {
            s = "[";
        } else if (primaryCode == 83) {
            s = "]";
        } else if (primaryCode == 84) {
            s = "{";
        } else if (primaryCode == 85) {
            s = "}";
        } else if (primaryCode == 87) {
            s = "_";
        } else if (primaryCode == 88) {
            s = "<";
        } else if (primaryCode == 89) {
            s = ">";
        } else if (primaryCode == 90) {
            s = "=";
        } else if (primaryCode == 91) {
            s = "+";
        } else if (primaryCode == 92) {
            s = "\"";
        } else if (primaryCode == 93) {
            s = "'";
        } else if (primaryCode == 94) {
            s = "/";
        } else if (primaryCode == 95) {
            s = "\\";
        } else if (primaryCode == 96) {
            s = "ϸ";
        } else if (primaryCode == 97) {
            s = "Ϸ";
        } else if (primaryCode == 98) {
            s = "*";
        } else if (primaryCode == 99) {
            s = "#";
        } else if (primaryCode == 43) {
            s = "ϵ";
        } else if (primaryCode == 44) {
            s = "϶";
        } else if (primaryCode == 200) {
            s = "1";
        } else if (primaryCode == 201) {
            s = "2";
        } else if (primaryCode == 202) {
            s = "3";
        } else if (primaryCode == 203) {
            s = "4";
        } else if (primaryCode == 204) {
            s = "5";
        } else if (primaryCode == 205) {
            s = "6";
        } else if (primaryCode == 206) {
            s = "7";
        } else if (primaryCode == 207) {
            s = "8";
        } else if (primaryCode == 208) {
            s = "9";
        } else if (primaryCode == 209) {
            s = "0";
        } else if (primaryCode == 210) {
            s = "×";
        } else if (primaryCode == 211) {
            s = "‒";
        } else if (primaryCode == 212) {
            s = "⏑";
        } else if (primaryCode == 213) {
            s = "⏒";
        } else if (primaryCode == 214) {
            s = "⏓";
        } else if (primaryCode == 215) {
            s = "⏔";
        } else if (primaryCode == 216) {
            s = "⏕";
        } else if (primaryCode == 217) {
            s = "⏖";
        } else if (primaryCode == 218) {
            s = "|";
        } else if (primaryCode == 219) {
            s = "‖";
        } else if (primaryCode == 26) { //parentheses
            localAccentLetter(editable, start, SURROUNDING_PARENTHESES);
        } else if (primaryCode == 27) { //rough breathing
            localAccentLetter(editable, start, ROUGH_BREATHING);
        } else if (primaryCode == 28) { //smooth breathing
            localAccentLetter(editable, start, SMOOTH_BREATHING);
        } else if (primaryCode == 29) { //acute
            localAccentLetter(editable, start, ACUTE);
        } else if (primaryCode == 30) { //circumflex
            localAccentLetter(editable, start, CIRCUMFLEX);
        } else if (primaryCode == 31) { //iota subscript
            localAccentLetter(editable, start, DIAERESIS);
        } else if (primaryCode == 32) { //iota subscript
            localAccentLetter(editable, start, IOTA_SUBSCRIPT);
        } else if (primaryCode == 33) { //macron
            localAccentLetter(editable, start, MACRON);
        }  else if (primaryCode == 34) { //iota subscript
            localAccentLetter(editable, start, GRAVE);
        } else if (primaryCode == 86) {
            s = "̆";
        } else if (primaryCode == 42) //Keyboard.KEYCODE_SHIFT:
        {
            Keyboard keyboard;

            if (capsL == "l") {
                if (miscL == "n") {
                    keyboard = new Keyboard(c, R.xml.hoplitekeyboardupper);
                }
                else
                {
                    keyboard = new Keyboard(c, R.xml.hoplitekeyboardmiscupper);
                }
                capsL = "u";
            }
            else
            {
                if (miscL == "n") {
                    keyboard = new Keyboard(c, R.xml.hoplitekeyboard);
                }
                else
                {
                    keyboard = new Keyboard(c, R.xml.hoplitekeyboardmisc);
                }
                capsL = "l";
            }
            kv.setKeyboard(keyboard);
            kv.setOnKeyboardActionListener(this);
            kv.invalidateAllKeys();
/*
            caps = !caps;
            kv.caps = !kv.caps;
            //keyboard.setShifted(caps);
            kv.getKeyboard().setShifted(caps);
            kv.invalidateAllKeys();
            */
        }
        else if (primaryCode == 250) {
            Keyboard keyboard;

            if (miscL == "n") {
                //if (capsL == "l") {
                    keyboard = new Keyboard(c, R.xml.hoplitekeyboardmisc);
                //} else {
                 //   keyboard = new Keyboard(c, R.xml.hoplitekeyboardmiscupper);
                //}
                miscL = "m";
                capsL = "l";
            } else {
                //if (capsL == "l") {
                    keyboard = new Keyboard(c, R.xml.hoplitekeyboard);
                //} else {
                //    keyboard = new Keyboard(c, R.xml.hoplitekeyboardupper);
                //}
                miscL = "n";
                capsL = "l";
            }
            kv.setKeyboard(keyboard);
            kv.setOnKeyboardActionListener(this);
            kv.invalidateAllKeys();
        } else if( primaryCode == 38 ) { //Delete
            if( start > 0 )
            {
                int i = 0;
                while (isCombiningCharacter(str2.charAt(start - i - 1)) && start - i - 1 > 0)
                    i++;
                editable.delete(start - i - 1, start);
            }
        } /* else {// Insert character
                editable.insert(start, Character.toString((char) primaryCode));
            } */
        if (!s.equals("")) {
            /*
            if (caps)
            {
                s = s.toUpperCase();
            }
            */
            editable.insert(start, s);
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


    public void localAccentLetter(Editable editable, int start, int acc) {
        int unicodeMode = kv.getUnicodeMode();
        GreekVerb gv1 = new GreekVerb();
        int maxSubstringForAccent = 7;
        String str2 = editable.toString();
        if (str2.length() < 1)
        {
            return;
        }
        String sub;
        String accentedLetter = "";

        int cc = numCombiningChars(str2);
        sub = str2.substring(start - cc - 1, start);
        accentedLetter = gv1.addAccent(acc, unicodeMode, sub);
        if (!accentedLetter.equals("")) {
            editable.replace(start - cc - 1, start, accentedLetter);
        }
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

    //see if there are one or more combining characters to the right of the cursor
    //if so move past them, so we don't insert a character between the combining characters
    //and their letter.
    public int fixCursorStart(int start, String s, EditText edittext)
    {
        if (start < s.length()) //doesn't matter if we're already at end of string
        {
            char charToRight = s.charAt(start);
            while (isCombiningCharacter(charToRight))
            {
                start++;
                edittext.setSelection(start);

                if (start + 1 <= s.length())
                {
                    charToRight = s.charAt(start);
                }
                else //we're at the end
                {
                    break;
                }
            }
            return start;
        }
        else
        {
            return start;
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

}
