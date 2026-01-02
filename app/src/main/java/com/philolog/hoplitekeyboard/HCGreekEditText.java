package com.philolog.hoplitekeyboard;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;

public class HCGreekEditText extends androidx.appcompat.widget.AppCompatEditText {
    //HopliteKeyboardView kv;

    public HCGreekEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        //to prevent system keyboard from coming up over top of hoplite keyboard
        //https://stackoverflow.com/questions/5803193/android-disable-soft-keyboard-at-all-edittexts
        setInputType(EditorInfo.TYPE_NULL); //if this, then need to set raw input type to text
        setRawInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS); //we def need this if setInputType is set to NULL as above
        setShowSoftInputOnFocus(false); //this is the one that really seems necessary
        //kv = kvn;
        /*
        this.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false; //true blocks copy/paste, but also prevents normal keyboard from appearing
            }
        });
        */
    }

/*
    //put code to popup keyboard here rather than activity?
    @Override
    public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect)
    {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        if (hasFocus()) {
            //context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            kv.openKeyboard(this, null);
            setCursorVisible(true);
            //requestFocus();
        }
        else
        {
            kv.hideKeyboard();
        }

    }
*/



/* @Override
    public boolean onCheckIsTextEditor() {
        //returning false blocks copy/paste, so this will not work
        return false;
    } */


}
