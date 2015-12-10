# LeftScrollListView
Listview左滑最常用的是SwipListView
但如果加入首字母分组的话，SwipListview会连同首字母一起向左划

LeftScrollListView则支持首字母分组
使用LeftScrollListView时，每个item的bean类需继承LeftScrollItemBean,并调用setItemAlphaString(String itemAlphaString);设置首字母
LeftScrollListView属性:
  1、leftscroll_rightview(右view的resourceId)
  2、leftscroll_leftview(左view的resourceId)
  3、leftscroll_alphaview(首字母所在的view的resourceId)
  4、leftscroll_issort(是否进行排序)
adapter需继承LeftScrollAdapter,实现三个方法
  void setAlpha(View view,int position);  首字母所在的view以及在listview中的position
	void setRight(View view,int position); 右布局所在的view以及在listview中的position
	void setLeft(View view,int position);  左布局所在的view以及在listview中的position
	
	
	
QQ:1391142249
