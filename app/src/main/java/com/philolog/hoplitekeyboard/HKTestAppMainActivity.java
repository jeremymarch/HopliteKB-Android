package com.philolog.hoplitekeyboard;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class HKTestAppMainActivity extends AppCompatActivity {
    public TextView mTextView, mCodePointTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/newathu5.ttf");
        mTextView = (TextView) findViewById(R.id.mTextView);
        mCodePointTextView = (TextView) findViewById(R.id.mCodePointTextView);
        mTextView.setTypeface(type);

        mTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                String str = mTextView.getText().toString();
                String res = "";

                final int length = str.length();
                for (int offset = 0; offset < length; ) {
                    final int codepoint = str.codePointAt(offset);
                    res = res + String.format("%04X", codepoint) + " - ";

                    offset += Character.charCount(codepoint);
                }
                if (res.endsWith(" - ")) {
                    res = res.substring(0, res.length() - 3);
                }

                mCodePointTextView.setText(res);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/newathu5.ttf");
        mTextView = (TextView) findViewById(R.id.mTextView);
        mTextView.setTypeface(type);
        //Log.e("abc", "resumed");
    }


}
