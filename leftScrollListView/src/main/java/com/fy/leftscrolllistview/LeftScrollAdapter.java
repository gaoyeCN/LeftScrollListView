package com.fy.leftscrolllistview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class LeftScrollAdapter extends BaseAdapter{
	private int rightResource,leftResource,alphaResource;
	private Context context;
	private List<? extends LeftScrollItemBean> itemBeans;
	private List<ViewHolder> holders;
	public LeftScrollAdapter(Context context,List<? extends LeftScrollItemBean> itemBeans) {
		this.context = context;
		this.itemBeans = itemBeans;
		this.holders = new ArrayList<ViewHolder>();
	}
	
	public void setSources(int[] resources){
		this.leftResource = resources[0];
		this.rightResource = resources[1];
		this.alphaResource = resources[2];
	}
	

	@Override
	public void notifyDataSetChanged() {
		this.holders.removeAll(holders);
		super.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return itemBeans.size();
	}

	@Override
	public Object getItem(int position) {
		return itemBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			LinearLayout linearMain = new LinearLayout(context);
			LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
			//linearMain.setLayoutParams(mainParams);
			linearMain.setOrientation(LinearLayout.VERTICAL);
			View alpha = null;
			if (alphaResource != 0) {
				alpha =  LayoutInflater.from(context).inflate(alphaResource, null);
			}else {
				alpha = new TextView(context);
			}
			
			linearMain.addView(alpha);
			
			LeftScrollItemView itemView = new LeftScrollItemView(context);
			ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			itemView.setLayoutParams(params);
			
			LinearLayout linearLayout = new LinearLayout(context);
			LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
			linearLayout.setLayoutParams(linearParams);
			linearLayout.setOrientation(LinearLayout.HORIZONTAL);
			View left = null;
			if (leftResource != 0) {
				left = LayoutInflater.from(context).inflate(leftResource, null);
			}else {
				left = new View(context);
			}
			ViewGroup.LayoutParams leftParams = new ViewGroup.LayoutParams(ScreenUtil.getScreenWidth(context),ViewGroup.LayoutParams.WRAP_CONTENT);
			left.setLayoutParams(leftParams);
			linearLayout.addView(left);
			
			View right = null;
			if (rightResource != 0) {
				right = LayoutInflater.from(context).inflate(rightResource, null);
			}else {
				right = new View(context);
			}
			ViewGroup.LayoutParams rightParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
			right.setLayoutParams(rightParams);
			linearLayout.addView(right);
			
			itemView.addView(linearLayout);
			linearMain.addView(itemView);
			convertView = linearMain;
			holder = new ViewHolder();
			holder.left = left;
			holder.right = right;
			holder.alpha = alpha;
			holder.lsv = itemView;
			holders.add(holder);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.lsv.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				clossAll(position);
				return false;
			}
		});
		setAlpha(holder.alpha,position);
		setRight(holder.right,position);
		setLeft(holder.left,position);
		return convertView;
	}
	
	public void sortItem(){
		Collections.sort(itemBeans);
	}
	
	public void clossAll(){
		for (int i = 0; i < holders.size(); i++) {
			holders.get(i).lsv.closeRight();
		}
	}
	public void clossAll(int position){
		for (int i = 0; i < holders.size(); i++) {
			if (i != position) {
				holders.get(i).lsv.closeRight();
			}
		}
	}
	
	private class ViewHolder{
		private View right,left;
		private View alpha;
		private LeftScrollItemView lsv;
	}
	
	protected abstract void setAlpha(View view,int position);
	protected abstract void setRight(View view,int position);
	protected abstract void setLeft(View view,int position);
}
