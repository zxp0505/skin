package com.imesong.themeplugin.entity;

import com.imesong.themeplugin.loader.SkinManager;

import android.view.View;
import android.widget.AbsListView;


public class ListSelectorAttr extends SkinAttr {

	@Override
	public void apply(View view) {
		if(view instanceof AbsListView){
			AbsListView listView = (AbsListView)view;
			
			if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
				listView.setSelector(SkinManager.getInstance().getColor(attrValueRefId));
			}else if(RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)){
				listView.setSelector(SkinManager.getInstance().getDrawable(attrValueRefId));
			}
		}
	}
}
