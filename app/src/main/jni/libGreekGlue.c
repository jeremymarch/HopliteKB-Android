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
Java_philolog_com_hoplitekeyboard_GreekVerb_addAccent( JNIEnv* env, jobject thiz, jint accent, jstring *str) {
    char buffer[1024];
    UCS2 ucs2[1024];
    int ucs2Len = 0;

    const char *letters = (*env)->GetStringUTFChars(env, str, NULL);
    utf8_to_ucs2_string((const unsigned char *)letters, ucs2, &ucs2Len);

    if (ucs2[0] != COMBINING_ACUTE && ucs2[0] != COMBINING_MACRON && ucs2[0] != COMBINING_ROUGH_BREATHING && ucs2[0] != COMBINING_SMOOTH_BREATHING) {
        accentSyllable(ucs2, 0, &ucs2Len, accent, true, PRECOMPOSED_MODE);
        ucs2_to_utf8_string(ucs2, ucs2Len, buffer);
    }
    else
    {
        buffer[0] = '\0';
    }

    return (*env)->NewStringUTF(env, buffer);
}

