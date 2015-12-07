package com.fy.administrator.leftscrolllistview;

import java.io.Serializable;

import com.fy.leftscrolllistview.LeftScrollItemBean;

public class TStudentBean extends LeftScrollItemBean{
    private int uid;
    private String stu_name;
    private String avatar;
    private boolean selected = true;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getStu_name() {
        return stu_name;
    }

    public int getUid() {
        return uid;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
