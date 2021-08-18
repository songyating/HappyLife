package com.example.happylife.adapter;

import android.content.Context;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.happylife.R;
import com.example.happylife.bean.CarouselNews;
import com.xinhuamm.carousel.CarouselViewCreator;

/**
 * 轮播图
 */
public class BannerCreator implements CarouselViewCreator<CarouselNews> {

    private Context mContext;

    public BannerCreator(Context context) {
        this.mContext = context;
    }

    @Override
    public int layoutId() {
        return R.layout.list_item_banner;
    }

    @Override
    public void convert(View container, CarouselNews item) {
        ImageView ivImg = container.findViewById(R.id.iv_img);
        TextView tvTitle = container.findViewById(R.id.tv_title);
        tvTitle.setText(item.getTitle());
        Glide.with(mContext)
                .load(item.getImgUrl())
                .into(ivImg);
    }
}

