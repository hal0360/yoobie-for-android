package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nz.co.udenbrothers.yoobie.R;

public class IconText extends RelativeLayout {

    private ImageView icon;
    private TextView text;

    public IconText(Context context) {
        super(context);
        init(context, null);
    }

    public IconText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.icon_text_layout, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

        icon = findViewById(R.id.iconTxtImg);
        text = findViewById(R.id.iconTxtText);

        // Assign custom attributes
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.IconText,
                    0, 0);

            String titleText = "";
            Drawable iconImg = null;

            try {
                titleText = a.getString(R.styleable.IconText_txtTitle);
                iconImg = a.getDrawable(R.styleable.IconText_txtImg);
            } catch (Exception e) {
                Log.e("IconText", "There was an error loading attributes.");
            } finally {
                a.recycle();
            }

            text.setText(titleText);
            icon.setBackgroundDrawable(iconImg);
        }
    }
}
