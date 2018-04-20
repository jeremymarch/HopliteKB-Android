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

#ifndef utilities_h
#define utilities_h

#include <stdio.h>

typedef unsigned short UCS2;

int ucs2_to_utf8 (UCS2 ucs2, unsigned char * utf8);
UCS2 utf8_to_ucs2 (const unsigned char * input, const unsigned char ** end_ptr);

void utf8_to_ucs2_string(const unsigned char *utf8, UCS2 *ucs2, int *len);
int ucs2_to_utf8_string(UCS2 *ucs2, int len, unsigned char *utf8);

void rightShiftFromOffsetSteps(UCS2 *ucs2, int offset, int steps, int *len);
void leftShiftFromOffsetSteps(UCS2 *ucs2, int offset, int steps, int *len);

#endif /* utilities_h */
