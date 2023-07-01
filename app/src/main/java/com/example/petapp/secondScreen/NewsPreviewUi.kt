package com.example.petapp.secondScreen

import android.widget.TextView

interface NewsPreviewUi {
    fun title(): String
    fun content(): String

    //    fun icon(): BitmapDrawable
//    fun map(iconView: ImageButton, titleView: TextView, contentView: TextView)
    fun map(titleView: TextView, contentView: TextView)

    abstract class Abstract(
        protected open val title: String,
        protected open val content: String,
//        protected open val drawable: BitmapDrawable
    ) : NewsPreviewUi {

        override fun title(): String = title
        override fun content(): String = content
//        override fun icon(): BitmapDrawable = drawable

        //        override fun map(iconView: ImageButton, titleView: TextView, contentView: TextView) {
//            iconView.setImageDrawable(drawable)
//            titleView.text = title
//            contentView.text = content
//        }
        override fun map(titleView: TextView, contentView: TextView) {
            titleView.text = title
            contentView.text = content
        }
    }

    // сделано, если потом будут состояния, чтобы можно было что-то дефолтно прописать
    class Base(
        override val title: String,
        override val content: String,
//        override val drawable: BitmapDrawable
    ) : Abstract(title, content)
}
