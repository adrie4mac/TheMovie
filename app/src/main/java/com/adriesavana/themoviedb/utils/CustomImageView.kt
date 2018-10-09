package com.adriesavana.themoviedb.utils

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.adriesavana.themoviedb.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Transformation

class CustomImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var isPlaceHolderUsed = true

    fun setImageUrlCenterCrop(url: String?) {
        this.scaleType = ScaleType.CENTER_CROP

        url?.let {
            if (it.isNotBlank()) {
                try {
                    getDefaultRequestCreator(it)
                            .into(this, getDefaultCallback(this, it))
                } catch (e: Exception) {

                }
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setImage()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setImage()
    }

    override fun onDetachedFromWindow() {
        removeImage()
        super.onDetachedFromWindow()
    }

    private fun setImage() {
        val oldTag = getTag(R.id.image_ref_id)
        if (oldTag != null && oldTag is RequestCreator) {
            try {
                oldTag.into(this, getDefaultCallback(this, oldTag))
            } catch (e: Exception) {

            }
        }
    }

    private fun removeImage() {
        val oldTag = getTag(R.id.image_ref_id)
        if (oldTag != null && oldTag is RequestCreator) {
            try {
                Picasso.with(context).cancelRequest(this)
                setImageBitmap(null)
                setImageDrawable(null)
            } catch (e: Exception) {

            }
        }
    }

    private fun getDefaultRequestCreator(imageUrl: String?, transformation: Transformation? = null): RequestCreator {
        setTag(R.id.image_ref_id, null)
        return Picasso.with(context)
                .load(imageUrl)
                .apply {
                    if (isPlaceHolderUsed) {
                        placeholder(R.drawable.ic_launcher_foreground)
                        error(R.drawable.ic_launcher_foreground)
                    }
                    if (transformation != null) {
                        transform(transformation)
                    }
                    setTag(R.id.image_ref_id, this)
                }
    }

    private fun getDefaultCallback(imageView: AppCompatImageView,
                                   imageUrl: String?): Callback {
        return object : Callback {
            override fun onSuccess() {
//            ignored
            }

            override fun onError() {
                if (imageUrl.isNullOrEmpty() || imageUrl.isNullOrBlank()) return

                val mRequestCreator = getDefaultRequestCreator(imageUrl)

                showImageAfterCallback(imageView, mRequestCreator)
            }
        }
    }

    private fun getDefaultCallback(imageView: AppCompatImageView,
                                   requestCreator: RequestCreator): Callback {
        return object : Callback {
            override fun onSuccess() {
//            ignored
            }

            override fun onError() {
                showImageAfterCallback(imageView, requestCreator)
            }
        }
    }

    private fun showImageAfterCallback(imageView: AppCompatImageView,
                                       requestCreator: RequestCreator) {
        if (imageView.width > 0 && imageView.height > 0) {
            requestCreator.resize(imageView.width, imageView.height)
        }

        try {
            requestCreator.into(imageView)
        } catch (e: Exception) {

        }
    }
}
