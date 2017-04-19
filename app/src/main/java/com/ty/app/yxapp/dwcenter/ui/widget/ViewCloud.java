package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import java.io.File;
import java.util.List;

/**
 * Created by kss on 2017/4/4.
 */

public class ViewCloud extends ViewGroup {
    private Context context;
    private int sizeWidth;
    private int sizeHeight;
    private int totalWidth;
    private int mViewBorder = AndroidUtils.dp(2);
    private int totalHeight;
    private int mTagBorderHor = AndroidUtils.dp(1);
    private AddMoreCell addMoreCell;


    public ViewCloud(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        getTotalH();
        setMeasuredDimension(
                sizeWidth,
                (heightMode == MeasureSpec.EXACTLY ? sizeHeight : totalHeight));
    }


    public int getTotalH() {
        int childWidth;
        int childHeight;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();

            if (i == 0) {
                totalHeight = childHeight + mViewBorder;
                totalWidth = 0;
            }

            totalWidth += childWidth + mViewBorder;


            // + marginLeft 保证最右侧与 ViewGroup 右边距有边界
            if (totalWidth + mTagBorderHor + mViewBorder > sizeWidth) {
                totalWidth = mViewBorder;
                totalHeight += childHeight + AndroidUtils.dp(1);
                child.layout(
                        totalWidth + mTagBorderHor,
                        totalHeight - childHeight,
                        totalWidth + childWidth + mTagBorderHor,
                        totalHeight);
                totalWidth += childWidth;
            } else {
                child.layout(
                        totalWidth - childWidth + mTagBorderHor,
                        totalHeight - childHeight,
                        totalWidth + mTagBorderHor,
                        totalHeight);
            }
        }
        return totalHeight + mViewBorder;
    }

    public void postView(final List<? extends Object> list, boolean onlyShowPic) {
        if (list == null) return;

        if (getChildCount() > 0) {
            removeAllViews();
            totalHeight = 0;
            totalWidth = 0;
        }
        for (int i = 0; i < list.size(); i++) {
            final int j = i;
            FrameLayout frameLayout = new FrameLayout(context);
            addView(frameLayout, new LayoutParams(AndroidUtils.dp(70), AndroidUtils.dp(70)));

            AddMoreCell addMore = new AddMoreCell(context);
            addMore.setImg(R.drawable.timg);
            addMore.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onClick(j,ViewCloud.this);
                    }
                }
            });

            if (list.get(i) instanceof Integer) {
                addMore.setImg((Integer) list.get(i));
            } else if (list.get(i) instanceof Uri) {
                String path = AndroidUtils.getPath(context, (Uri) list.get(i));
                MediaMetadataRetriever media = new MediaMetadataRetriever();
                media.setDataSource(path);
                Bitmap bitmap = media.getFrameAtTime();

                if (bitmap != null) {
                    addMore.setImg(bitmap);
                }
            } else if (list.get(i) instanceof Bitmap) {
                addMore.setImg((Bitmap) list.get(i));
            } else if (list.get(i) instanceof ImageView) {
                addMore.setImageView((ImageView) list.get(i));
            } else if (list.get(i) instanceof String) {
                addMore.setImg((String) list.get(i));
            }
            frameLayout.addView(addMore, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

            ImageView close = new ImageView(context);
            close.setVisibility(onlyShowPic ? GONE : VISIBLE);
            close.setImageResource(R.mipmap.close);
            FrameLayout.LayoutParams closeFl = new FrameLayout.LayoutParams(AndroidUtils.dp(30), AndroidUtils.dp(30));
            closeFl.gravity = Gravity.RIGHT | Gravity.TOP;
            frameLayout.addView(close, closeFl);
            close.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onListener != null) {
                        onListener.close(j, ViewCloud.this);
                    }
                }
            });

            if (i == list.size() - 1 && !onlyShowPic) {
                addMoreCell = new AddMoreCell(context);
                addMoreCell.setOnTouchListener(onTouchListener);
                addView(addMoreCell, new LayoutParams(AndroidUtils.dp(70), AndroidUtils.dp(70)));
            }
        }

        if (list.isEmpty() && !onlyShowPic) {
            addMoreCell = new AddMoreCell(context);
            addMoreCell.setOnTouchListener(onTouchListener);
            addView(addMoreCell, new LayoutParams(AndroidUtils.dp(70), AndroidUtils.dp(70)));
        }

        postInvalidate();
    }


    private OnListener onListener;

    public void postView(final List<? extends Object> list) {
        postView(list, false);
    }

    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (onListener != null) {
                return onListener.onTouch(ViewCloud.this, motionEvent);
            }
            return false;
        }
    };

    public void setOnListener(OnListener onListener) {
        this.onListener = onListener;
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }


    public void setAddMoreText(String text) {
        if (addMoreCell != null)
            addMoreCell.setText(text);
    }


    public interface OnListener {
        boolean onTouch(View view, MotionEvent motionEvent);

        void close(int i, View view);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener( OnItemClickListener onItemClickListener){
        this.onItemClickListener =  onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(int pos,View view);
    }
}
