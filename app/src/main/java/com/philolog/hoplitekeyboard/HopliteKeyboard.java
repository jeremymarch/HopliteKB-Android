package com.philolog.hoplitekeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;

import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.media.AudioManager;

/**
 * Created by jeremy on 2/15/17.
 */
//http://stackoverflow.com/questions/15825081/error-default-activity-not-found
    //https://code.tutsplus.com/tutorials/create-a-custom-keyboard-on-android--cms-22615
public class HopliteKeyboard extends InputMethodService implements OnKeyboardActionListener{
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
    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.hoplitekeyboard);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);

        switch (primaryCode) {
            case 38:
            case Keyboard.KEYCODE_DELETE:
                //cursor could be between character and combining accents...
                int maxSubstringForAccent = 7;
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
            case 12://Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char) primaryCode;
                /*
                if (Character.isLetter(code) && caps) {
                    code = Character.toUpperCase(code);
                }
                */

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
                } else if (primaryCode == 39) {
                    s = " ";
                } else if (primaryCode == 35) {
                    s = "\n";
                } else if (primaryCode == 36) {
                    s = ".";
                } else if (primaryCode == 37) {
                    s = ";";
                } else if (primaryCode == 40) {
                    s = ",";
                } else if (primaryCode == 41) {
                    s = "·";
                } else if (primaryCode == 26) { //parentheses
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
                }

                if (!s.equals("")) {
                    if (caps)
                    {
                        s = s.toUpperCase();
                    }
                    ic.commitText(s, 1);
                }



/*
        // Get the EditText and its Editable
        View focusCurrent = MainActivity.this.getWindow().getCurrentFocus();
        if( focusCurrent==null || focusCurrent.getClass()!=EditTypeWriter.class )
            return;
        EditTypeWriter edittext = (EditTypeWriter) focusCurrent;
        Editable editable = edittext.getText();
        int start = edittext.getSelectionStart();

        // this will prevent inserting a char between combining accents
        String str2 = editable.toString();
        start = fixCursorStart(start, str2, edittext);

        vibrator.vibrate(KEYPRESS_VIBRATE);

        if( primaryCode==CodeCancel ) {
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

            }


         else if( primaryCode == 33 ) { //MF
            onMFPressed(editable, start);
        } else if( primaryCode == 34 ) { //Enter
            onEnterPressed();
        } else if( primaryCode == 35 ) { //Delete
            if( start > 0 )
            {
                int i = 0;
                while (isCombiningCharacter(str2.charAt(start - i - 1)) && start - i - 1 > 0)
                    i++;
                editable.delete(start - i - 1, start);
            }

        } */ /* else {// Insert character
                editable.insert(start, Character.toString((char) primaryCode));
            } */
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

        //Log.e("abc", "NUM: " + cc + ", " + (strBeforeLen - cc - 1) + ", " + (strBeforeLen) + ", " + strBefore.substring(strBeforeLen - cc - 1, strBeforeLen));
        String accentedLetter = "";
        accentedLetter = gv1.addAccent(acc, strBefore.substring(strBeforeLen - cc - 1, strBeforeLen));

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
