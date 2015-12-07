package com.fy.leftscrolllistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.List;
/**
 * 创建者：gaoye
 * 时间�?2015/11/25  09:49
 * 类描述：为解决首字母和左滑冲突的横向srcollview
 * 修改人：
 * 修改时间�?
 * 修改备注�?
 */
@SuppressLint({ "Recycle", "ClickableViewAccessibility" })
public class LeftScrollItemView extends HorizontalScrollView{
	private final String TAG = "com.fy.listview.ListViewItemHorizontalScrollView";
	private Context context;
	private LinearLayout ll_main;
	private int rightViewWidth,leftViewWidth;
	private int state;
	private final int STATE_CLOSED = 0,STATE_OPENED = 1;
	private int currentPosition;
	private boolean isMove;

	public LeftScrollItemView(Context context) {
		this(context,null);
	}
	
	public LeftScrollItemView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	
	public LeftScrollItemView(Context context,AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
	}

	private void init() {
		
	}
	
	/*public void setContral(List<Integer> stateControl,int currentPosition){
		this.stateControl = stateControl;
		this.currentPosition = currentPosition;
	}*/

	public void setCurrentPosition(int currentPosition){
		this.currentPosition = currentPosition;
	}
	
	/*public List<Integer> getContralList(){
		return stateControl;
	}*/
	
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		ll_main = (LinearLayout) getChildAt(0);
		View left = ll_main.getChildAt(0);
		leftViewWidth = left.getMeasuredWidth();
		View right = ll_main.getChildAt(1);
		rightViewWidth = right.getMeasuredWidth();
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	
	private float startX;
	private float endX;
	private float distance;
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = ev.getX();
			endX = ev.getX();
			distance = 0;
			isMove = false;
			break;
		case MotionEvent.ACTION_MOVE:
			endX = ev.getX();
			if (Math.abs(distance) > 10 || isMove){
				if (!isMove) {
					distance = 0;
				}
				isMove = true;
				if (distance < 0 && state == STATE_CLOSED) {
					if (Math.abs(endX - startX) > 5) {
						smoothScrollBy(-(int) (endX - startX), 0);
					}
				}else if(distance > 0 && state == STATE_OPENED){
					if (Math.abs(endX - startX) > 5) {
						smoothScrollBy(-(int) (endX - startX), 0);
					}
				}else{
					smoothScrollBy(-(int) (endX - startX), 0);
				}
			}
			distance += (endX - startX);
			startX = ev.getX();
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_OUTSIDE:
			Log.i(TAG, "distance:" + distance);
			Log.i(TAG, "rightViewWidth:" + rightViewWidth);
			if (Math.abs(distance) > 10){
				if (Math.abs(distance) < rightViewWidth * 2 / 3) {
					if (distance < 0) {
						if (state == STATE_CLOSED) {
							closeRight();
						}
					}else {
						if (state == STATE_OPENED) {
							closeRight();
						}
					}
				}else if (Math.abs(distance) > rightViewWidth / 2){
					if (distance < 0) {
						if (state == STATE_CLOSED) {
							openRight();
						}
					}else {
						if (state == STATE_OPENED) {
							closeRight();
						}
					}
				}
			}else {
				if (state == STATE_OPENED) {
					openRight();
				}else if(state == STATE_CLOSED){
					closeRight();
				}
			}
			break;
		}
		return true;
	}
	
	public void closeRight(){
			smoothScrollTo(-rightViewWidth - 1, 0);
			state = STATE_CLOSED;
	}
	
	public void openRight(){
			smoothScrollTo(rightViewWidth, 0);
			state = STATE_OPENED;
	}
}
