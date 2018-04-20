/*
 * Created by Jeremy March on 5/15/17.
 * Copyright © 2017 Jeremy March. All rights reserved.
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

package com.philolog.hoplitekeyboard;

public class GreekVerb
{
    public native String addAccent(int accent, int unicodeMode, String str);

    static {
        //http://stackoverflow.com/questions/1007861/how-do-i-get-a-list-of-jni-libraries-which-are-loaded/1008631#1008631
        //try {
        System.loadLibrary("libGreek");
        //} catch (Exception e) { }
    }
}


