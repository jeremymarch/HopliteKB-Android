package com.philolog.hoplitekeyboard;

/**
 * Created by jeremy on 5/6/17.
 */

public class GreekVerb
{
    public native String addAccent(int accent, String str); //included here as a hack

    static {
        //http://stackoverflow.com/questions/1007861/how-do-i-get-a-list-of-jni-libraries-which-are-loaded/1008631#1008631
        //try {
        System.loadLibrary("libGreek");
        //} catch (Exception e) { }
    }
}


