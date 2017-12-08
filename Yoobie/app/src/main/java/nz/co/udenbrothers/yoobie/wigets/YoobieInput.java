package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import nz.co.udenbrothers.yoobie.R;


public class YoobieInput extends RelativeLayout {

    private EditText input;

    public YoobieInput(Context context) {
        super(context);
        init(context, null);
    }

    public YoobieInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    public String getText(){
        return input.getText().toString().trim();
    }

    public void error(String s){
        requestFocus();
        input.setError(s);
    }

    public void setText(String s){
       input.setText(s);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.yoobie_input_layout, this);

        ImageView icon = findViewById(R.id.inputImg);
        input = findViewById(R.id.input);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.YoobieInput,
                    0, 0);

            String placeholder = "";
            Drawable iconImg = null;
            String text = "";

            try {
                placeholder = a.getString(R.styleable.YoobieInput_placeholder);
                iconImg = a.getDrawable(R.styleable.YoobieInput_inputIcon);
                text = a.getString(R.styleable.YoobieInput_inputText);
            } catch (Exception e) {
                Log.e("YoobieInput", "There was an error loading attributes.");
            } finally {
                a.recycle();
            }

            input.setText(text);
            input.setHint(placeholder);
            icon.setBackgroundDrawable(iconImg);
        }
    }
}
