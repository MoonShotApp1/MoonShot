package com.dartmouth.moonshot

import de.hdodenhof.circleimageview.CircleImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter

data class User(
    var name : String? = null,
    var bio : String? = null,
    var savedCoins: String? = null,
    var image: String? = null
){

    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: CircleImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view.context).load(imageUrl).into(view)
            }
        }
    }

}
