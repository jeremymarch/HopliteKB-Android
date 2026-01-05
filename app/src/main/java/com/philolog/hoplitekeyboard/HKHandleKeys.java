package com.philolog.hoplitekeyboard;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.ExtractedText;

public class HKHandleKeys {

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
    public final static int BREVE = 10;
    public final static int UNDERDOT = 11;

    public final static int COMBINING_GRAVE             = 0x0300;
    public final static int COMBINING_ACUTE             = 0x0301;
    public final static int COMBINING_CIRCUMFLEX        = 0x0342; //0x0302
    public final static int COMBINING_MACRON            = 0x0304;
    public final static int COMBINING_DIAERESIS         = 0x0308;
    public final static int COMBINING_SMOOTH_BREATHING  = 0x0313;
    public final static int COMBINING_ROUGH_BREATHING   = 0x0314;
    public final static int COMBINING_IOTA_SUBSCRIPT    = 0x0345;
    public final static int COMBINING_BREVE             = 0x0306;
    public final static int COMBINING_UNDERDOT          = 0x0323;
    public final static int MAX_COMBINING_DIACRITICS    = 10;

    public final static int HKEnterKey    = 1;
    public final static int HKDeleteKey    = 2;
    public final static int HKGlobeKey    = 3;
    public final static int HKCapsKey    = 4;
    public final static int HKExtraKey    = 5;

    public final static int HKRoughBreathingKey    = 10;
    public final static int HKSmoothBreathingKey    = 11;
    public final static int HKAcuteKey    = 12;
    public final static int HKGraveKey    = 13;
    public final static int HKCircumflexKey    = 14;
    public final static int HKIotaSubscriptKey    = 15;
    public final static int HKMacronKey    = 16;
    public final static int HKBreveKey    = 17;
    public final static int HKUnderdotKey    = 18;
    public final static int HKDiaeresisKey    = 19;

    public final static int HKSpaceKey    = 150;
    /*lower*/
    public final static int HKPeriodKey    = 151;
    public final static int HKCommaKey    = 152;
    public final static int HKMiddleDotKey    = 153;
    /*upper*/
    public final static int HKQuestionKey    = 154;
    public final static int HKApostropheKey    = 155;
    public final static int HKEMDashKey    = 156;
    /* misc */
    public final static int HKHyphenMinusKey    = 157;
    public final static int HKLowLineKey    = 158;
    public final static int HKExclamationKey    = 159;

    public final static int HKZeroKey    = 160;
    public final static int HKNineKey    = 169;
    public final static int HKLunateSigmaKey    = 169;

    public final static int HKForwardSlashKey = 187;
    public final static int HKPlusKey = 188;
    public final static int HKAsterixKey = 189;
    public final static int HKDoubleQuoteKey = 190;
    public final static int HKOpenParenKey = 191;
    public final static int HKCloseParenKey = 192;
    public final static int HKOpenSquareBKey = 193;
    public final static int HKCloseSquareBKey = 194;

    public final static int HKBackSlashKey = 222;
    public final static int HKEqualsKey = 223;
    public final static int HKNumKey = 224;
    public final static int HKSingleQuoteKey = 225;
    public final static int HKOpenAngleBKey = 226;
    public final static int HKCloseAngleBKey = 227;
    public final static int HKOpenCurlyBKey = 228;
    public final static int HKCloseCurlyBKey = 229;

    public final static int HKMultipleFormsKey = 500;


    private final static String[] letters = {
            "α", "β", "γ", "δ", "ε", "ζ", "η", "θ", "ι", "κ", "λ", "μ", "ν", "ξ", "ο", "π", "ρ", "σ", "τ", "υ", "φ", "χ", "ψ", "ω", "ς",
            "Α", "Β", "Γ", "Δ", "Ε", "Ζ", "Η", "Θ", "Ι", "Κ", "Λ", "Μ", "Ν", "Ξ", "Ο", "Π", "Ρ", "Σ", "Τ", "Υ", "Φ", "Χ", "Ψ", "Ω",
            " ",
            ".", ",", "\u00B7" /*middle dot*/,
            ";", "\u2019" /*right quote*/, "\u2014" /*em dash*/,
            "\u002D"/*hyphen-minus*/, "\u005F"/*low line*/, "!",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "ϲ", "ϙ", "ϝ", "ϛ", "ϟ", "ϡ", "ϻ", "ͷ", "ͳ", "ͱ", "ϸ", "ͻ", "ͼ", "ϵ", "ϐ", "Ϗ", "ʹ", "/", "+", "*", "\"", "(", ")", "[", "]",
            "×", "‒", "⏑", "⏒", "⏓", "⏔", "⏕", "⏖", "|", "‖", "Ϲ", "Ϙ", "Ϝ", "Ϛ", "Ϟ", "Ϡ", "Ϻ", "Ͷ", "Ͳ", "Ͱ", "Ϸ", "ͻ", "ͽ", "϶", "ϐ", "Ϗ", "͵", "\\", "=", "#", "'", "<", ">", "{", "}"
    };

    public static void onKey(int primaryCode, int[] keyCodes, InputConnection ic, int unicodeMode)
    {
        if ( primaryCode > 100 && primaryCode < 230) {
            int offset = 101;
            String let = letters[primaryCode - offset];

            if (!let.isEmpty()) {
                ic.commitText(let, 1);
            }
        } else if (primaryCode == HKDeleteKey) {
            //cursor could be between character and combining accents...
            CharSequence charsBefore = ic.getTextBeforeCursor(MAX_COMBINING_DIACRITICS, 0);
            if (charsBefore == null) {
                return;
            }
            String strBefore = charsBefore.toString();
            int strBeforeLen = strBefore.length();
            if (strBeforeLen < 1) {
                return;
            }
            int cc = numCombiningChars(strBefore);

             CharSequence charsAfter = ic.getTextAfterCursor(MAX_COMBINING_DIACRITICS, 0);
            if (charsAfter == null) {
                return;
            }
            String strAfter = charsAfter.toString();
            int ccAfter = numCombiningCharsAfter(strAfter);
            ic.deleteSurroundingText(cc+1, ccAfter);
        } else if (primaryCode == HKEnterKey) {
            //ic.commitText("\n", 1);
            ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
        }/*
        else if (primaryCode == HKCapsKey) {
            capsLock = !capsLock;
            setKeys(context, kv );
        } else if (primaryCode == HKExtraKey) {
            extraKeysLock = !extraKeysLock;
            setKeys(context, kv);
        } else if (primaryCode == HKGlobeKey) {

        } */ else if (primaryCode == HKRoughBreathingKey) {
            localAccentLetter(ic, 0, ROUGH_BREATHING, unicodeMode);
        } else if (primaryCode == HKSmoothBreathingKey) {
            localAccentLetter(ic, 0, SMOOTH_BREATHING, unicodeMode);
        } else if (primaryCode == HKAcuteKey) {
            localAccentLetter(ic, 0, ACUTE, unicodeMode);
        } else if (primaryCode == HKGraveKey) {
            localAccentLetter(ic, 0, GRAVE, unicodeMode);
        } else if (primaryCode == HKCircumflexKey) {
            localAccentLetter(ic, 0, CIRCUMFLEX, unicodeMode);
        } else if (primaryCode == HKIotaSubscriptKey) {
            localAccentLetter(ic, 0, IOTA_SUBSCRIPT, unicodeMode);
        } else if (primaryCode == HKMacronKey) {
            localAccentLetter(ic, 0, MACRON, unicodeMode);
        } else if (primaryCode == HKBreveKey) {
            localAccentLetter(ic, 0, BREVE, unicodeMode);
        } else if (primaryCode == HKUnderdotKey) {
            localAccentLetter(ic, 0, UNDERDOT, unicodeMode);
        } else if (primaryCode == HKDiaeresisKey) {
            localAccentLetter(ic, 0, DIAERESIS, unicodeMode);
        }
    }

    public static void localAccentLetter(InputConnection ic, int start, int acc, int unicodeMode) {
        //GreekVerb gv1 = new GreekVerb();

        ExtractedText et = ic.getExtractedText(new ExtractedTextRequest(), 0);
        if (et == null) {
            return;
        }
        int selectionStart = et.selectionStart;
        int selectionEnd = et.selectionEnd;

        int maxSubstringForAccent = 7;
        CharSequence charsBefore = ic.getTextBeforeCursor(maxSubstringForAccent, 0);
        if (charsBefore == null) {
            return;
        }
        String strBefore = charsBefore.toString();

        int strBeforeLen = strBefore.length();
//        if (strBeforeLen < 1) {
//            //return;
//        }

        int cc = numCombiningChars(strBefore);

        //Log.e("abc", "NUM: " + cc + ", " + (strBeforeLen - cc - 1) + ", " + (strBeforeLen) + ", " + strBefore.substring(strBeforeLen - cc - 1, strBeforeLen));
        String accentedLetter;
        //Log.e("abc", "unicode mode: " + unicodeMode);

        if (strBeforeLen < 1) {
            accentedLetter = GreekVerb.addAccent(acc, unicodeMode, "");
        }
        else {
            accentedLetter = GreekVerb.addAccent(acc, unicodeMode, strBefore.substring(strBeforeLen - cc - 1, strBeforeLen));
        }

        if (!accentedLetter.isEmpty()) {
            ic.setComposingRegion(selectionStart - (1+cc), selectionEnd);
            ic.setComposingText(accentedLetter, 1);
            ic.finishComposingText();
            //ic.commitText(accentedLetter, start);
        }
    }

    public static int numCombiningChars(String s) {
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

    public static int numCombiningCharsAfter(String s) {
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
    /*
    public int fixCursorStart(int start, String s, InputConnection edittext)
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
        }
        return start;
    }
*/
    public static boolean isCombiningCharacter(char s) {
        return (s == COMBINING_GRAVE ||
                s == COMBINING_ACUTE ||
                s == COMBINING_CIRCUMFLEX ||
                s == COMBINING_MACRON ||
                s == COMBINING_DIAERESIS ||
                s == COMBINING_SMOOTH_BREATHING ||
                s == COMBINING_ROUGH_BREATHING ||
                s == COMBINING_IOTA_SUBSCRIPT ||
                s == COMBINING_BREVE ||
                s == COMBINING_UNDERDOT);
    }

    public static boolean isDiacriticKey(int code) {
        return (code == HKRoughBreathingKey ||
        code == HKSmoothBreathingKey ||
        code == HKAcuteKey ||
        code == HKGraveKey ||
        code == HKCircumflexKey ||
        code == HKIotaSubscriptKey ||
        code == HKMacronKey ||
        code == HKBreveKey ||
        code == HKUnderdotKey ||
        code == HKDiaeresisKey);
    }

    public static boolean isPunctuationKey(int code) {
        return (code ==  HKPeriodKey ||
        code ==  HKCommaKey ||
        code ==  HKMiddleDotKey ||
        code ==  HKQuestionKey ||
        code ==  HKApostropheKey ||
        code ==  HKEMDashKey ||
        code ==  HKHyphenMinusKey ||
        code ==  HKLowLineKey ||
        code ==  HKExclamationKey ||
        code ==  HKForwardSlashKey ||
        code ==  HKPlusKey ||
        code ==  HKAsterixKey ||
        code ==  HKDoubleQuoteKey  ||
        code ==  HKOpenParenKey ||
        code ==  HKCloseParenKey ||
        code ==  HKOpenSquareBKey ||
        code ==  HKCloseSquareBKey ||
        code ==  HKBackSlashKey ||
        code ==  HKEqualsKey ||
        code ==  HKNumKey ||
        code ==  HKSingleQuoteKey ||
        code ==  HKOpenAngleBKey ||
        code ==  HKCloseAngleBKey ||
        code ==  HKOpenCurlyBKey ||
        code ==  HKCloseCurlyBKey);
    }
}
