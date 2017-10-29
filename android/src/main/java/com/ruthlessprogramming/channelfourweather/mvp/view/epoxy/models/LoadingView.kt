package com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.models

import android.support.constraint.ConstraintLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.ruthlessprogramming.channelfourweather.R

@EpoxyModelClass
abstract class LoadingView : EpoxyModel<ConstraintLayout>() {
    @EpoxyAttribute
    var progressString: String = ""

    override fun getDefaultLayout(): Int {
        return R.layout.loading_view
    }

    override fun bind(view: ConstraintLayout) {
        val progressText = view.findViewById<TextView>(R.id.tvProgress)
        progressText.text = progressString
    }
}