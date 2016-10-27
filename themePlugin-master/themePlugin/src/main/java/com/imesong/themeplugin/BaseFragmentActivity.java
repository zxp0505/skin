package com.imesong.themeplugin;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.imesong.themeplugin.entity.DynamicAttr;
import com.imesong.themeplugin.listener.IDynamicNewView;
import com.imesong.themeplugin.listener.ISkinUpdate;
import com.imesong.themeplugin.loader.SkinInflaterFactory;
import com.imesong.themeplugin.loader.SkinManager;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;


/**
 * Base Fragment Activity for development
 * 
 * <p>NOTICE:<br> 
 * You should extends from this if you want to do skin change
 */
public class BaseFragmentActivity extends FragmentActivity implements ISkinUpdate, IDynamicNewView {
	
	/**
	 * Whether response to skin changing after create
	 */
	private boolean isResponseOnSkinChanging = true;
	
	private SkinInflaterFactory mSkinInflaterFactory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
        try {
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(getLayoutInflater(), false);
            
    		mSkinInflaterFactory = new SkinInflaterFactory();
    		getLayoutInflater().setFactory(mSkinInflaterFactory);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
		PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, new String[]{Manifest
				.permission.READ_EXTERNAL_STORAGE}, new PermissionsResultAction() {
			@Override
			public void onGranted() {
				SkinManager.getInstance().load();
			}

			@Override
			public void onDenied(String permission) {
				Toast.makeText(BaseFragmentActivity.this,String.format(getResources().getString(R.string
						.permission_request_failed),permission),Toast
						.LENGTH_SHORT).show();
			}
		});


	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SkinManager.getInstance().attach(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		SkinManager.getInstance().detach(this);
	}
	
	protected void dynamicAddSkinEnableView(View view, String attrName, int attrValueResId){	
		mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
	}
	
	protected void dynamicAddSkinEnableView(View view, List<DynamicAttr> pDAttrs){
		mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
	}
	
	final protected void enableResponseOnSkinChanging(boolean enable){
		isResponseOnSkinChanging = enable;
	}

	@Override
	public void onThemeUpdate() {
		if(!isResponseOnSkinChanging) return;
		mSkinInflaterFactory.applySkin();
	}
	
	@Override
	public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
		mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		PermissionsManager.getInstance().notifyPermissionsChange(permissions,grantResults);
	}
}
