package com.imesong.themeplugin.listener;

import com.imesong.themeplugin.entity.DynamicAttr;

import android.view.View;

import java.util.List;


public interface IDynamicNewView {
	void dynamicAddView(View view, List<DynamicAttr> pDAttrs);
}
