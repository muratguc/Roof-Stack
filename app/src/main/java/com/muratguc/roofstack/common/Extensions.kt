package com.muratguc.roofstack.common

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.muratguc.roofstack.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Murat Güç on 2/1/2021.
 */

@BindingAdapter("url")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view).load(url)
        .placeholder(R.mipmap.ic_launcher)
        .centerCrop()
        .into(view)
}

fun Context.openInBrowser(activity: Activity, url: String) {
    try {
        val url = Uri.parse(url)
        val intent = CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(ContextCompat.getColor(this, R.color.purple_500))
                    .setNavigationBarColor(ContextCompat.getColor(this, R.color.purple_700))
                    .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.purple_700))
                    .build()
            )
            .setStartAnimations(activity, R.anim.come_from_right, R.anim.out_to_left)
            .setExitAnimations(activity, R.anim.come_from_left, R.anim.out_to_right)
            .setShowTitle(false)
            .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
            .setUrlBarHidingEnabled(true)
            .build()
        intent.launchUrl(activity, url)
    } catch (e: Exception) {
    }

}

fun convertLongToTime(time: Long?): String? {
    return if (time == null) null else {
        val date = Date(time)
        val format = SimpleDateFormat("yyy-MM-dd", Locale.UK)
        format.format(date)
    }
}