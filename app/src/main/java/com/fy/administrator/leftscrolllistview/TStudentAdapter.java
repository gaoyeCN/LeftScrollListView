package com.fy.administrator.leftscrolllistview;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.fy.leftscrolllistview.LeftScrollAdapter;
import com.fy.leftscrolllistview.LeftScrollItemBean;
import com.fy.leftscrolllistview.ScreenUtil;

/**
 * 创建者：gaoye
 * 时间：2015/11/19  15:45
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TStudentAdapter extends LeftScrollAdapter{
	private final String TAG = "com.fy.listview.TStudentAdapter";
	private List<TStudentBean> students;
	private Context context;
	private List<ViewHolder> holders;
	private int width;
	
	public TStudentAdapter(Context context, List<TStudentBean> students) {
		super(context, students);
		this.students = students;
		this.context = context;
	}

	/*public TStudentAdapter(Context context, List<TStudentBean> students){
		this.students = students;
		this.context = context;
		this.width = ScreenUtil.getScreenWidth(context);
		this.holders = new ArrayList<ViewHolder>();
	}*/

	/*final static class ViewHolder {
		TextView tv1;
		TextView tv2;
		ImageView iv;
	}*/

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return ((LeftScrollItemBean)students.get(position)).getItemAlphaString().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = ((LeftScrollItemBean)students.get(i)).getItemAlphaString();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}

	class ViewHolder {
		TextView tv1;
		TextView tv2;
		TextView tvr;
		ImageView iv;
	}


	@Override
	protected void setAlpha(View view, int position) {
		TStudentBean studentBean = students.get(position);
		TextView tView = (TextView) view.findViewById(R.id.tv_itemalpha);
		tView.setText(studentBean.getItemAlphaString());
		//根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);
				if(position == getPositionForSection(section)){
					tView.setVisibility(View.VISIBLE);
				}else{
					tView.setVisibility(View.GONE);
				}
	}

	@Override
	protected void setRight(View view, int position) {
		
	}

	@Override
	protected void setLeft(View view, int position) {
		
	}

}
