package com.philolog.hoplitekeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                "For best results, install a polytonic Greek font such as New Athena Unicode."));
    }
}