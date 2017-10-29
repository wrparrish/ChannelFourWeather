package com.ruthlessprogramming.channelfourweather.mvp.view.epoxy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.SimpleEpoxyController;

import java.util.List;


public class EpoxyModelRecyclerView extends RecyclerView {
    private SimpleEpoxyController controller;

    public EpoxyModelRecyclerView(Context context, @Nullable AttributeSet attrs, RecyclerView.LayoutManager layoutManager) {
        super(context, attrs);
        setLayoutManager(layoutManager);
    }

    public void setModels(List<? extends EpoxyModel<?>> models) {
        if (controller == null) {
            controller = new SimpleEpoxyController();
            controller.setSpanCount(1);
            setAdapter(controller.getAdapter());
        }
        controller.setModels(models);
    }
}
