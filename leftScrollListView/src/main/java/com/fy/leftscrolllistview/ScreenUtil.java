package com.fy.leftscrolllistview;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * 创建者：gaoye
 * 时间：2015/11/24  09:32
 * 类描述：获取屏幕信息的工具类
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ScreenUtil {
	//获取屏幕的宽度
	public static int getScreenWidth(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}

	//dp转px
	public static int dp2px(int dp,Context context) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}

	//获取状态栏高度
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}
