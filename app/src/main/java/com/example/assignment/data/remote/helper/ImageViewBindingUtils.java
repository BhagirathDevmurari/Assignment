package com.example.assignment.data.remote.helper;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.assignment.di.base.modules.GlideApp;
import com.example.assignment.di.base.modules.GlideRequest;

public class ImageViewBindingUtils {
    @BindingAdapter(value = {"image_url", "resize", "placeholder"}, requireAll = false)
    public static void setImageUrl(final ImageView imageView, String image_url, boolean resize, Drawable placeholder) {
        GlideRequest<Drawable> requests = GlideApp.with(imageView.getContext()).load(image_url);
        if (resize)
            requests.override(120, 100);
        if (placeholder != null)
            requests.placeholder(placeholder);
        requests.centerCrop();
        requests.into(imageView);
    }

    @BindingAdapter(value = {"load_icon", "placeholder"}, requireAll = false)
    public static void loadIcon(final ImageView imageView, String image_url, Drawable placeholder) {
        GlideRequest<Drawable> requests = GlideApp.with(imageView.getContext()).load(image_url);
        if (placeholder != null)
            requests.placeholder(placeholder);
        requests.into(imageView);
    }

    @BindingAdapter(value = {"rectangle", "view_width", "view_height", "placeholder"}, requireAll = false)
    public static void rectangle(final ImageView imageView, String image_url, Integer view_width, Integer view_height, Drawable placeholder) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        if (view_width != null && view_height != null)
            options.override(view_width, view_height);
        if (placeholder != null)
            options.placeholder(placeholder);
        GlideApp.with(imageView.getContext()).load(image_url).apply(options).into(imageView);
    }


    @BindingAdapter(value = {"square", "placeholder"}, requireAll = false)
    public static void setSqure(final ImageView imageView, String image_url, Drawable placeholder) {
        GlideRequest<Drawable> requests = GlideApp.with(imageView.getContext()).load(image_url);
        requests.override(120);
        if (placeholder != null)
            requests.placeholder(placeholder);
        requests.centerCrop();
        requests.into(imageView);
    }

    @BindingAdapter(value = {"view_image"})
    public static void setImageUrl(final ImageView imageView, String image_url) {
        Glide.with(imageView.getContext()).load(image_url).centerCrop().apply(new RequestOptions()).into(imageView);
    }


    @BindingAdapter(value = {"simpleResourse"})
    public static void setStepGroupIcon(final ImageView imageView, int simpleResourse) {
        if (simpleResourse != -1) {
            imageView.setImageResource(simpleResourse);

        }
    }

    @BindingAdapter(value = {"chat_thumbnil", "corner"}, requireAll = false)
    public static void setThumnilUrl(final ImageView imageView, String image_url, String message) {
        GlideRequest<Drawable> requests = GlideApp.with(imageView.getContext()).load(image_url);
        requests.centerCrop();
        requests.into(imageView);
    }

}
