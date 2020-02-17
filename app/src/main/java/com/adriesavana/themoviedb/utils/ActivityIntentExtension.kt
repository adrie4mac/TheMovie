package com.adriesavana.themoviedb.utils

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * How to:
 * activity.start(TargetActvity::class.java) {
 *      putExtra("key", "value")
 * }
 *
 * or You can just use it like this
 * activity.start(TargetActivity::class.java)
 */
fun Activity.start(target: Class<*>, requestCode: Int? = null, func: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, target)
    func?.let { intent.it() }
    if (requestCode != null) {
        startActivityForResult(intent, requestCode)
    } else {
        startActivity(intent)
    }
}

fun Activity.toolbarTransparent() {
    with(window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = 0x00000000 // transparent
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}