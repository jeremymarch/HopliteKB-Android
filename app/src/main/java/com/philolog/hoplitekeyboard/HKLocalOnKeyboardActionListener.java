package com.philolog.hoplitekeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.inputmethodservice.KeyboardView;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

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

    private boolean caps = false;

    public HKLocalOnKeyboardActionListener(EditText et, HopliteKeyboardView kview)
    {
        e = et;
        kv = kview;
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

        } else if (primaryCode == 39) {
            s = " ";
        } else if (primaryCode == 35) {
            s =  "\n";
        } else if (primaryCode == 36) {
            s = ".";
        } else if (primaryCode == 37) {
            s = ";";
        } else if (primaryCode == 40) {
            s = ",";
        } else if (primaryCode == 41) {
            s = "·";

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
        } else if (primaryCode == 33) { //macron
            localAccentLetter(editable, start, MACRON);
        } else if (primaryCode == 32) { //iota subscript
            localAccentLetter(editable, start, IOTA_SUBSCRIPT);
        } else if (primaryCode == 34) { //iota subscript
            localAccentLetter(editable, start, GRAVE);
        } else if (primaryCode == 42) //Keyboard.KEYCODE_SHIFT:
        {
            caps = !caps;
            kv.caps = !kv.caps;
            //keyboard.setShifted(caps);
            kv.getKeyboard().setShifted(caps);
            kv.invalidateAllKeys();
        }
        else if( primaryCode == 38 ) { //Delete
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
            if (caps)
            {
                s = s.toUpperCase();
            }
            editable.insert(start, s);
        }
    }

    @Override public void onPress(int arg0) {
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
