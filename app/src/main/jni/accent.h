//
//  accent.h
//  HCPolytonicGreekKBapp
//
//  Created by Jeremy March on 3/4/17.
//  Copyright © 2017 Jeremy March. All rights reserved.
//

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
    DIAERESIS
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
    _DIAERESIS  = 1 << 7//,
    //_BREVE      = 1 << 8
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
