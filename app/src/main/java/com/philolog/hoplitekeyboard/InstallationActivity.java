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

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by jeremy on 5/15/17.
 */

public class InstallationActivity extends AppCompatActivity {
    TextView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installation);

        iv = (TextView) findViewById(R.id.installationTextView);

        iv.setText(Html.fromHtml("INSTALLATION<br>" +
                "<br>" +
                "To install keyboard on Android device:<br>" +
                "1) Open the Settings app<br>" +
                "2) Select Language &amp; input<br>" +
                "3) Tap Current Keyboard<br>" +
                "4) Tap 'Choose Keyboards'<br>" +
                "5) Enable 'Hoplite Polytonic Greek Keyboard'<br>" +
                "6) Now the Hoplite Keyboard can be selected from the list of keyboards<br>" +
                "<br>" +
                "For best results, install a polytonic Greek font such as New Athena Unicode.", Html.FROM_HTML_MODE_LEGACY));
    }
}
