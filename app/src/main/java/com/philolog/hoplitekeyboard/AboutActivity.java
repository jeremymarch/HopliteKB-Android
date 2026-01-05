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

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
TextView tv;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.about);

    tv = (TextView) findViewById(R.id.aboutTextView);

    tv.setText(Html.fromHtml("USE:<br>" +
            "• Type the letter first, then add diacritics.<br>" +
            "• Diacritics can be added in any order.<br>" +
            "• Pressing the diacritic again will toggle it on or off.<br>" +
            "• Contrasting diacritics replace each other: accents, breathings.<br>" +
            "• Redundant diacritics replace each other: circumflex/macron.<br>" +
            "• Press caps lock to use the diaeresis key.<br><br>" +
            "SETTINGS:<br>" +
            "• Precomposed will use precomposed unicode characters when possible, falling back to combining diacritics if a precomposed character doesn't exist.<br>" +
            "• Precomposed with PUA is like the above, but will also use precomposed characters from the Private Use Area when possible.  This is only supported by certain fonts and is not standard unicode.<br>" +
            "• Combining only uses only combining diacritics.  This is only supported by certain fonts.", Html.FROM_HTML_MODE_LEGACY));
    }
}