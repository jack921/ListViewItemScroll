package com.example.jack.listviewitemscroll.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.jack.listviewitemscroll.util.ScreenUtils;

/**
 * Created by Jack on 2016/2/27.
 */

public class ScrollListViewItem extends HorizontalScrollView{

    private static int ScreenWidth=0;
    private static int MenuWidth=0;
    private static int HalfMenuWidth=0;
    private boolean  turnLeft=false;
    private boolean turnRight=false;
    private boolean once;

    ViewGroup left;
    ViewGroup centre;
    ViewGroup right;

    public ScrollListViewItem(Context context) {
        this(context, null);
    }

    public ScrollListViewItem(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public ScrollListViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr,0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       if(!once){
           LinearLayout linearLayout=(LinearLayout)getChildAt(0);
           left=(ViewGroup)linearLayout.getChildAt(0);
           centre=(ViewGroup)linearLayout.getChildAt(1);
           right=(ViewGroup)linearLayout.getChildAt(2);

           ScreenWidth= ScreenUtils.getScreenWidth(getContext());
           MenuWidth=ScreenWidth/4;
           HalfMenuWidth=MenuWidth/2;

           left.getLayoutParams().width=MenuWidth;
           centre.getLayoutParams().width=ScreenWidth;
           right.getLayoutParams().width=MenuWidth;
       }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(MenuWidth,0);
            once=true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_UP:
                //向左滑
                if(turnLeft){
                    if(getScrollX()<HalfMenuWidth){
                        //滑到左最末尾
                        this.scrollTo(0, 0);
                    }else if(getScrollX()>HalfMenuWidth&&getScrollX()<MenuWidth+HalfMenuWidth){
                        //滑到中间
                        this.scrollTo(MenuWidth, 0);
                    }else{
                        this.scrollTo(MenuWidth * 2, 0);
                    }
                }
                //向右滑
                if(turnRight){
                    if(getScrollX()>MenuWidth+HalfMenuWidth){
                        //滑到最右
                        this.scrollTo(MenuWidth + MenuWidth, 0);
                    }else if(getScrollX()>HalfMenuWidth&&getScrollX()<MenuWidth+HalfMenuWidth){
                        //滑到中间
                        this.scrollTo(MenuWidth, 0);
                    }else{
                        //滑到最左
                        this.scrollTo(0, 0);
                    }
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(l>MenuWidth){
            turnLeft=false;
            turnRight=true;
        }else{
            turnLeft=true;
            turnRight=false;
        }
    }
}
