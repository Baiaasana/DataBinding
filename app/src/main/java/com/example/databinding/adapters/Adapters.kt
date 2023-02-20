package com.example.databinding.adapters

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun AppCompatImageView.setImage(url: String) {
    this.load(url)

}

@BindingAdapter("setVisibility")
fun View.setVisibility(visibilityLevel: Int){
    if(visibilityLevel > 0){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.INVISIBLE
    }
}

//class Adapters{
//
//    companion object{
//
//        @BindingAdapter("setImage")
//        @JvmStatic
//        fun AppCompatImageView.setImage(url: String) {
//            Glide.with(this.context).load(url).into(this)
//
//        }
//    }
//}

