/*
 * Created by Jeremy March on 4/15/15.
 * Copyright © 2015 Jeremy March. All rights reserved.
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

#ifndef accent_h
#define accent_h

#include <stdbool.h> //for bool type
#include "utilities.h"


enum {
    NO_ACCENT = 0,
    ACUTE,
    CIRCUMFLEX,
    GRAVE,
    MACRON,
    ROUGH_BREATHING,
    SMOOTH_BREATHING,
    IOTA_SUBSCRIPT,
    SURROUNDING_PARENTHESES,
    DIAERESIS,
    BREVE
};

//diacriticMask bit flags
enum {
    _MACRON     = 1 << 0,
    _SMOOTH     = 1 << 1,
    _ROUGH      = 1 << 2,
    _ACUTE      = 1 << 3,
    _GRAVE      = 1 << 4,
    _CIRCUMFLEX = 1 << 5,
    _IOTA_SUB   = 1 << 6,
    _DIAERESIS  = 1 << 7,
    _BREVE      = 1 << 8
};

enum {
    PRECOMPOSED_MODE            = 0,
    PRECOMPOSED_WITH_PUA_MODE,
    COMBINING_ONLY_MODE,
    PRECOMPOSED_HC_MODE //this is legacy for the hoplite challenge app which uses combining macron even if no other diacritics
};

void accentSyllable(UCS2 *ucs2String, int i, int *len, int accentToAdd, bool toggleOff, int unicodeMode);

int analyzeLetter(UCS2 *ucs2String, int i, int len, int *letterCode, int *accentBitMask);

bool makeLetter(UCS2 *ucs2String, int *newLetterLen, int letterCode, int accentBitMask, int unicodeMode);

#endif /* accent_h */
