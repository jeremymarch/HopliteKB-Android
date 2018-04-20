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

//Useful link: http://developer.android.com/training/articles/perf-jni.html


#include <string.h>
#include <jni.h>
#include <android/log.h>
#include "accent.h"
#include "utilities.h"
#include "GreekUnicode.h"

#include <android/log.h>
#define  LOG_TAG    "your-log-tag"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
// If you want you can add other log definition for info, warning etc

JNIEXPORT jstring JNICALL
Java_com_philolog_hoplitekeyboard_GreekVerb_addAccent( JNIEnv* env, jobject thiz, jint accent, jint unicodeMode, jstring *str) {
    char buffer[1024];
    UCS2 ucs2[1024];
    int ucs2Len = 0;

    const char *letters = (*env)->GetStringUTFChars(env, str, NULL);
    utf8_to_ucs2_string((const unsigned char *)letters, ucs2, &ucs2Len);

    if (ucs2[0] != COMBINING_GRAVE && ucs2[0] != COMBINING_ACUTE && ucs2[0] != COMBINING_MACRON && ucs2[0] != COMBINING_ROUGH_BREATHING && ucs2[0] != COMBINING_SMOOTH_BREATHING) {
        LOGE("ABC uni: %d", unicodeMode);
        accentSyllable(ucs2, 0, &ucs2Len, accent, true, unicodeMode);
        ucs2_to_utf8_string(ucs2, ucs2Len, buffer);
    }
    else
    {
        buffer[0] = '\0';
    }

    return (*env)->NewStringUTF(env, buffer);
}

