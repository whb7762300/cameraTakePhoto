package com.jackwang.takepic;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class RatioSurfaceView extends SurfaceView {
    private float ratio = 0f;//宽高比

    public RatioSurfaceView(Context context) {
        this(context, null);
    }

    public RatioSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioSurfaceView);
        ratio = typedArray.getFloat(R.styleable.RatioSurfaceView_ratio, 0);
        typedArray.recycle();
    }

    //设置宽高比
    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    /**
     * 重写onMeasure，对宽高进行自定义
     * MeasureSpec：测量规则，由size和mode组成
     * size ： 就是具体宽高
     * mode: MeasureSpec.AT_MOST 对应的是wrap_content
     * MeasureSpec.EXACTLY 对应的是具体的dp值，match_parent
     * MeasureSpec.UNSPECIFIED 未定义的，很少见，只在adapter的view测量中用到
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);//获取图片的宽度
        if (ratio != 0) {
            float height = width / ratio;//根据宽高比得到height
            //重新构造高度的测量规则heightMeasureSpec
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
