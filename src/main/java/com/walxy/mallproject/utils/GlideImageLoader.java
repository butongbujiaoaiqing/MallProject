package com.walxy.mallproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.walxy.mallproject.R;

/**
 * 类描述： Banner图片加载工具类
 * 创建人： 王兵洋
 * 创建时间：20170907
 */
public class GlideImageLoader extends com.youth.banner.loader.ImageLoader {
    private ImageLoader imageloader;

    private DisplayImageOptions options;

    public GlideImageLoader(Context context) {
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);

        //将configuration配置到imageloader中*/
        imageloader = ImageLoader.getInstance();
        imageloader.init(configuration);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .showImageOnLoading(R.drawable.npc)
                .showImageForEmptyUri(R.drawable.npc)
                .showImageOnFail(R.drawable.npc)
                .build();
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        imageloader.displayImage(path.toString(), imageView, options);
    }
}
