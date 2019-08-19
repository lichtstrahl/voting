package root.iv.voting.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import root.iv.voting.R;

public class MenuItem extends CardView {
    private int image;
    private String title;
    private Paint paint;

    public MenuItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MenuItem, 0, 0);

        try {
            image = a.getInteger(R.styleable.MenuItem_image, 0);
            title = a.getString(R.styleable.MenuItem_title);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int halfW = this.getMeasuredWidth()/2;
        int halfH = this.getMeasuredHeight()/2;


    }
}
