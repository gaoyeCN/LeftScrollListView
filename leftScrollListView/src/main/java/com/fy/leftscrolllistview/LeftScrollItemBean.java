package com.fy.leftscrolllistview;


public class LeftScrollItemBean implements Comparable<LeftScrollItemBean>{
	protected String itemAlphaString;

	public String getItemAlphaString() {
		return itemAlphaString;
	}

	public void setItemAlphaString(String itemAlphaString) {
		this.itemAlphaString = itemAlphaString;
	}
	
	void seta(Object o){
		
	}

	@Override
	public int compareTo(LeftScrollItemBean o) {
        char c1 = getItemAlphaString().charAt(0);
        char c2 = o.getItemAlphaString().charAt(0);
        if (c1 > c2) return 1;
        else if(c1 < c2) return -1;
        else return 0;
	}
	
	/*@Override
    public int compareTo(Object o) {
        if (!(o instanceof LeftScrollItemBean)){
            return -1;
        }
        char c1 = getItemAlphaString().charAt(0);
        char c2 = ((LeftScrollItemBean)o).getItemAlphaString().charAt(0);
        if (c1 > c2) return 1;
        else if(c1 < c2) return -1;
        else return 0;
    }*/
}
