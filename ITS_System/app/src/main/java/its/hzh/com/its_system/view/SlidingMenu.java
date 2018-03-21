package its.hzh.com.its_system.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Switch;



import its.hzh.com.its_system.R;

/**
 * Created by ken on 2018/3/9.
 */

public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;

    private int mMenuWidth;

    //dp
    private int mMenuRightPadding = 50;

    private boolean once = false;
    private boolean isOpen = false;

    /**
     * 未使用自定义属性时调用
     * @param context
     * @param attrs
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlidingMenu(Context context) {
        this(context,null);
    }

    /**
     * 当使用了自定义属性时，会调用此构造方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SlidingMenu, defStyleAttr,0);

        int n = a.getIndexCount();
        for(int i=0; i<n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = a.getDimensionPixelSize(attr, (int)TypedValue.applyDimension
                            (TypedValue.COMPLEX_UNIT_DIP,50,context
                                    .getResources().getDisplayMetrics()));
                    break;
            }
        }



        a.recycle();


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;


        //把dp转换为px
//        mMenuRightPadding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context
//                .getResources().getDisplayMetrics());
    }

    /**
     * 设置子View的宽和高
     * 设置自己的宽和高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!once) {
            mWapper = (LinearLayout)getChildAt(0);
            mMenu = (ViewGroup)mWapper.getChildAt(0);
            mContent = (ViewGroup)mWapper.getChildAt(1);
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once=true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量将menu隐藏
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed) {
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_UP:
                int scrollx = getScrollX();

                if(scrollx >= mMenuWidth/2) {
                    this.smoothScrollTo(mMenuWidth,0);
                    isOpen = false;
                } else {
                    this.smoothScrollTo(0,0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单
     */
    private void openMenu() {
        if(isOpen) return;
        this.smoothScrollTo(0,0);
        isOpen=true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if(!isOpen) return;
        this.smoothScrollTo(mMenuWidth, 0);
        isOpen=false;
    }

    public void toggle() {
        if(!isOpen) {
            openMenu();
        } else {
            closeMenu();
        }
    }

    /**
     * 滚动发生时
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//        float scale = l * 1.0f / mMenuWidth;
//
//        ViewHelper.setTranslationX(mMenu,mMenuWidth * scale);
//    }
}

