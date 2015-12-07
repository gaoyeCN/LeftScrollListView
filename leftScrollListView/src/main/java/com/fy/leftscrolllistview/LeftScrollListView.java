package com.fy.leftscrolllistview;

import com.fy.leftscrolllistview.R.attr;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 创建者：gaoye
 * 时间：2015/11/27  16:15
 * 类描述：与自定义的scrollview使用 一旦触发侧滑 则不会上下滑动
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LeftScrollListView extends ListView {
    private Context context;
    private boolean giveself = true,sortornot;
    private AttributeSet attrs;
    private int[] resources;
    private BaseAdapter adapter;
    public LeftScrollListView(Context context) {
        this(context, null);
    }

    public LeftScrollListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        init();
    }

    private void init() {
    	TypedArray tArray = context.obtainStyledAttributes(attrs,R.styleable.LeftScrollListView);
    	sortornot = tArray.getBoolean(R.styleable.LeftScrollListView_leftscroll_issort, false);
    	int rightResource = tArray.getResourceId(R.styleable.LeftScrollListView_leftscroll_rightview, 0);
    	int leftResource = tArray.getResourceId(R.styleable.LeftScrollListView_leftscroll_leftview, 0);
    	int alphaResource = tArray.getResourceId(R.styleable.LeftScrollListView_leftscroll_alphaview, 0);
    	resources = new int[]{leftResource,rightResource,alphaResource};
    	tArray.recycle();
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
    	if (adapter instanceof LeftScrollAdapter) {
    		if (sortornot) {
        		((LeftScrollAdapter)adapter).sortItem();
    		}
    		((LeftScrollAdapter)adapter).setSources(resources);
		}
    	
    	super.setAdapter(adapter);
    }
    
    /*
    @Override
    public ListAdapter getAdapter() {
    	if (!hasRight) {
			return adapter;
		}else {
			return ((LeftScrollAdapter)adapter).getListAdapter();
		}
    }*/

	float startX,endX,startY,endY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                giveself = true;
                startX = endX = ev.getX();
                startY = endY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = ev.getX();
                endY = ev.getY();
                if (Math.abs(endX - startX) > Math.abs(endY - startY) + 10 || giveself == false){
                    giveself = false;
                    startX = endX ;
                    startY = endY;
                    return false;
                }else{
                    startX = endX ;
                    startY = endY;
                    return super.onInterceptTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startY = startX = endX = endY = 0;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    
}
