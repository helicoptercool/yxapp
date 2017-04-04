package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * 圆角按钮
 * Created by kss on 8/25/16.
 */
public class RoundButton extends View {

    private int layout_height = 0;
    private int layout_width = 0;
    private float paddingTop = 0;
    private float paddingBottom = 0;
    private float paddingLeft = 0;
    private float paddingRight = 0;

    private Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF circleBounds = new RectF();
    private RectF borderBounds = new RectF();

    private int backgroundColor;
    private int backgroundPressColor;
    private int borderColor;
    private int borderPressColor;
    private String text;
    private int textColor;
    private int textPressColor;
    private int textSize;
    private Typeface textTypeFace;
    private int borderWidth;
    private boolean isEventDown = false;

    private Bitmap icon = null;
    private Context context;

    private static final int leftRes = 0;
    private static final int rightRes = 1;
    private int compoundDrawablePadding = 0;
    private Bitmap bitmaps[] = new Bitmap[2];

    public RoundButton(Context context, int background_color, int background_press, int border_color) {
        super(context);
        this.context = context;
        init();

        this.backgroundColor = background_color;
        this.backgroundPressColor = background_press;
        this.borderColor = border_color;
    }

    private void init() {
        backgroundColor = 0x00000000;
        borderColor = 0xFFFF0000;
        textColor = 0xFF000000;
        textSize = AndroidUtils.dp(14);
        borderWidth = AndroidUtils.dp(1);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        layout_width = w;
        layout_height = h;

        draw();
    }


    public void setCompoundDrawablePadding(int compoundDrawablePadding) {
        this.compoundDrawablePadding = AndroidUtils.dp(compoundDrawablePadding);
    }

    public void setLeftRes(int res) {
        if(res > 0){
            Bitmap br = BitmapFactory.decodeResource(getResources(), res);
            bitmaps[leftRes] = br;
            invalidate();
        }else{
            if( bitmaps[leftRes] != null)  bitmaps[leftRes] = null;
        }
    }

    public void setRightRes(int res) {
        if(res > 0){
            Bitmap br = BitmapFactory.decodeResource(getResources(), res);
            bitmaps[rightRes] = br;
            invalidate();
        }else{
            if(bitmaps[rightRes] != null)  bitmaps[rightRes] = null;
        }
    }


    private void drawLeftRes(Canvas canvas, float baseline) {
        canvas.drawText(text, borderBounds.centerX() + (bitmaps[leftRes].getWidth() + compoundDrawablePadding) / 2, baseline, textPaint);
        float textW = textPaint.measureText(text);
        canvas.drawBitmap(bitmaps[leftRes],
                borderBounds.centerX() - (textW + bitmaps[leftRes].getWidth() + compoundDrawablePadding) / 2,
                borderBounds.centerY() - bitmaps[leftRes].getHeight() / 2,
                textPaint);
    }

    private void drawRightRes(Canvas canvas, float baseline) {
        canvas.drawText(text, borderBounds.centerX() - (bitmaps[rightRes].getWidth() + compoundDrawablePadding) / 2, baseline, textPaint);
        float textW = textPaint.measureText(text);
        canvas.drawBitmap(bitmaps[rightRes],
                borderBounds.centerX() + (textW + bitmaps[rightRes].getWidth() + compoundDrawablePadding) / 2,
                borderBounds.centerY() - bitmaps[rightRes].getHeight() / 2,
                textPaint);
    }

    private boolean isDraw() {
        if (bitmaps[leftRes] != null || bitmaps[rightRes] != null) return true;
        return false;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float radius = (float) layout_height / 2;

        if (borderWidth > 0)
            canvas.drawRoundRect(borderBounds, radius, radius, borderPaint);

        canvas.drawRoundRect(circleBounds, radius, radius, backgroundPaint);

        if (!TextUtils.isEmpty(text)) {
            Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
            float baseline = (borderBounds.bottom + borderBounds.top - fontMetrics.bottom - fontMetrics.top) / 2;
            if (isDraw()) {
                if (bitmaps[leftRes] != null) drawLeftRes(canvas, baseline);
                else if (bitmaps[rightRes] != null) drawRightRes(canvas, baseline);
            } else {
                canvas.drawText(text, borderBounds.centerX(), baseline, textPaint);
            }
        } else if (icon != null) {
            float x = borderBounds.centerX() - icon.getWidth() / 2;
            float y = borderBounds.centerY() - icon.getHeight() / 2;
            canvas.drawBitmap(icon, x, y, textPaint);
        }
        canvas.save();
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                isEventDown = true;
                draw();
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL: {
                isEventDown = false;
                draw();
                break;
            }
        }

        return super.onTouchEvent(event);
    }

    private void setupBounds() {
        paddingTop = this.getPaddingTop();
        paddingBottom = this.getPaddingBottom();
        paddingLeft = this.getPaddingLeft();
        paddingRight = this.getPaddingRight();

        circleBounds = new RectF(
                paddingLeft + borderWidth,
                paddingTop + borderWidth,
                layout_width - paddingRight - borderWidth,
                layout_height - paddingBottom - borderWidth);

        float t = borderWidth / 2;
        borderBounds = new RectF(
                paddingLeft + t + 1,
                paddingTop + t + 1,
                layout_width - paddingRight - t - 1,
                layout_height - paddingBottom - t - 1);
    }

    private void setupPaints() {
        if (isEventDown && backgroundPressColor != 0) {
            backgroundPaint.setColor(backgroundPressColor);
        } else {
            backgroundPaint.setColor(backgroundColor);
        }
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.FILL);

        if (isEventDown && borderPressColor != 0) {
            borderPaint.setColor(borderPressColor);
        } else {
            borderPaint.setColor(borderColor);
        }
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);

        if (isEventDown && textPressColor != 0) {
            borderPaint.setColor(textPressColor);
        } else {
            textPaint.setColor(textColor);
        }
        textPaint.setAntiAlias(true);
        textPaint.setTypeface(this.textTypeFace);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw() {
        setupBounds();
        setupPaints();
        invalidate();
    }

    public void setText(String text) {
        this.text = text;
        draw();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextSize(int textSize) {
        this.textSize = AndroidUtils.dp(textSize);
    }

    public void setTypeface(Typeface tf) {
        this.textTypeFace = tf;
    }

    public void setBorderPressColor(int borderPressColor) {
        this.borderPressColor = borderPressColor;
    }

    public void setTextPressColor(int textPressColor) {
        this.textPressColor = textPressColor;
    }

    public void setImageResource(int resId) {
        icon = BitmapFactory.decodeResource(getResources(), resId);
    }

    public void setBackgroudColor(int backgroud) {
        this.backgroundColor = backgroud;
        draw();
    }

    public void setBorderColor(int color) {
        this.borderColor = color;
    }

    public void setBackgroundPressColor(int backgroundColor) {
        this.backgroundPressColor = backgroundColor;
        draw();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        draw();
    }

}