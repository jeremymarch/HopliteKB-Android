package com.philolog.hoplitekeyboard;

/**
 * Created by jeremy on 5/15/17.
 */

import android.support.v7.app.AppCompatActivity;
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
            "• Combining only uses only combining diacritics.  This is only supported by certain fonts."));
    }
}