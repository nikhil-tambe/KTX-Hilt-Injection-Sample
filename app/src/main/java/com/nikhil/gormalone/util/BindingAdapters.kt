package com.nikhil.gormalone.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

@BindingAdapter("app:glideImage")
fun glideImage(imageView: ImageView, any: Any) {
    Glide.with(imageView)
        .load(any)
        .transform(CenterCrop())
        .into(imageView)
}

@BindingAdapter("app:setAnyText")
fun setAnyText(textView: TextView, any: Any?) {
    if(any == null) return
    val message = when (any) {
        is String -> any.toString()
        is Int -> textView.context.resources.getString(any)
        else -> ""
    }
    textView.text = message
}