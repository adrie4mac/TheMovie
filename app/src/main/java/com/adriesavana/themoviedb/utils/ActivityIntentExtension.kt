package com.adriesavana.themoviedb.utils

import android.app.Activity
import android.content.Intent

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
