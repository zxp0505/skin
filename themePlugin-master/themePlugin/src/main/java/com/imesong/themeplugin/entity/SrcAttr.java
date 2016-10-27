package com.imesong.themeplugin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.imesong.themeplugin.loader.SkinManager;

/**
 * Created by ping on 2016/10/27.
 */

public class SrcAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof ImageView) {
            if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
//                int resoureseId = SkinManager.getInstance().getDrawableId(attrValueRefId);
//                ((ImageView) view).setImageResource(resoureseId);
                Drawable drawable = SkinManager.getInstance().getDrawable(attrValueRefId);
                ((ImageView)view).setImageDrawable(drawable);
            }
        }
    }
}
