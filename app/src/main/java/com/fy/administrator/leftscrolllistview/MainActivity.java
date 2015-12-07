package com.fy.administrator.leftscrolllistview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fy.leftscrolllistview.AlphaUtil;
import com.fy.leftscrolllistview.LeftScrollListView;
import com.fy.leftscrolllistview.SideBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private LeftScrollListView slv_student;
    private TStudentAdapter adapter;
    private List<TStudentBean> students;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		setData();
	}

	private void setData() {
		students = new ArrayList<TStudentBean>();

        for (int i = 0;i<50;i++){
            TStudentBean stuListEntity = new TStudentBean();
            stuListEntity.setUid(i);
            if (i % 2 == 0){
                stuListEntity.setStu_name("啊");
            }else if (i % 3 == 0){
                stuListEntity.setStu_name("罢课");
            }else if (i % 5 == 0){
                stuListEntity.setStu_name("差劲");
            }else {
                stuListEntity.setStu_name("ofda");
            }
            String s = AlphaUtil.getPinYinHeadChar(stuListEntity.getStu_name());
            stuListEntity.setItemAlphaString(s.substring(0,1));
            students.add(stuListEntity);
        }
        
        adapter = new TStudentAdapter(this,students);
        slv_student.setAdapter(adapter);
	}

	private void initView() {
		slv_student = (LeftScrollListView)findViewById(R.id.slv_student);
	}
}
