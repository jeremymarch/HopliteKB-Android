//
//  accent.h
//  HCPolytonicGreekKBapp
//
//  Created by Jeremy March on 3/4/17.
//  Copyright © 2017 Jeremy March. All rights reserved.
//
// This Source Code Form is subject to the terms of the Mozilla Public
// License, v. 2.0. If a copy of the MPL was not distributed with this
// file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    DIAERESIS,
    BREVE,
    UNDERDOT
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
    _BREVE      = 1 << 8,
    _UNDERDOT   = 1 << 9
};

enum {
    PRECOMPOSED_MODE            = 0,
    PRECOMPOSED_WITH_PUA_MODE,
    COMBINING_ONLY_MODE,
    PRECOMPOSED_HC_MODE //this is legacy for the hoplite challenge app which uses combining macron even if no other diacritics
};

void allowSpacingDiacritics(bool val);

void accentSyllable(UCS2 *ucs2String, int i, int *len, int accentToAdd, bool toggleOff, int unicodeMode);

int scanLetter(UCS2 *ucs2String, int i, int len, UCS2 *letterCode, int *accentBitMask);
int analyzeLetter(UCS2 *ucs2String, int i, int len, UCS2 *letterCode, int *accentBitMask);
int analyzePrecomposedLetter(UCS2 letterToAnalyze, UCS2 *l, int *a);

bool makeLetter(UCS2 *ucs2String, int *newLetterLen, UCS2 letterCode, int accentBitMask, int unicodeMode);

bool isCombiningDiacritic(UCS2 l);

int compareSort(int len_a, const unsigned char *a, int len_b, const unsigned char *b);
int hccontainsPUA(const unsigned char *utf8);
int hcucHex(const unsigned char *utf8, int bufferLen, char *buffer);

#endif /* accent_h */
