# android换肤总结 #
## 1.通过设置不同的style  ## 
这种方式是通过设置theme 然后activity reCreate重新调用activity
设置主题必须在oncreate()方法  并且在setContentveiw()方法之前 才有效

[这篇文章](http://blog.csdn.net/u010687392/article/details/48088571)就是利用这种方式切换主题   
缺点是：需要重启activity

[换肤技术总结](http://blog.zhaiyifan.cn/2015/09/10/Android%E6%8D%A2%E8%82%A4%E6%8A%80%E6%9C%AF%E6%80%BB%E7%BB%93/)

[换肤总结2](http://www.jianshu.com/p/3d59ee0ad433)

[Bilibili/MagicaSakura](https://github.com/Bilibili/MagicaSakura)

[最终方案按照这个实现的](http://www.jianshu.com/p/8c4b2171fa8c) android-skin-load 动态加载skin包 
## 2.Android-Skin-Loader ##

## 加载皮肤包 ##
 其内部通过反射调用AssetManager.addAssetPath()把外部的皮肤资源加载到AssetManager中，并通过该AssetManager创建相应的Resource。当执行换肤操作的时候，就可以设置需要换肤View的相关属性为Resource中相应的资源

## 代码中的细节 ##

1.每个skinAttr记录的是某个需要换肤的view的 id  background 以及对应的 color / color_title_bar_bg    skinAttr -----某个换肤view 的单个属性的一系列字段

2.然后将每个skinAttr 添加到 List<SkinAttr> viewAttrs-----添加某个view换肤时所要更改的属性（可以是多个属性）

3.SkinItem （View view; List<SkinAttr> attrs;  ） 用于封装单个view和其需要换肤的对应的属性集合

4.List<SkinItem> mSkinItems 将所有skinitem进行存储 换肤时遍历刷新


## 关键的两个判断  ##

boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig
                .ATTR_SKIN_ENABLE, false);
先做 skin:enable="true"  view里是否有这个属性的判断

 if (!AttrFactory.isSupportedAttr(attrName)) 这个判断是看自己是否支持这个属性 里面一般不全 需要自己添加

## 更换皮肤 ##
主要根据List<SkinItem> mSkinItems 遍历刷新 每个skinitem 用自己的id从skinmanager获取资源中的color background 

skinmanager //里面的资源的获取需要自己实现 

public int getColor(int resId)
 
getDrawable(int resId)



