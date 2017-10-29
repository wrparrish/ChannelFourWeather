package com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.models;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithView;
import com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.EpoxyModelRecyclerView;

import java.util.List;

@EpoxyModelClass
public abstract class CarouselModel extends EpoxyModelWithView<EpoxyModelRecyclerView> {
    @EpoxyAttribute
    List<? extends EpoxyModel<?>> models;

    @Override
    public boolean shouldSaveViewState() {
        return true;
    }

    @Override
    protected EpoxyModelRecyclerView buildView(ViewGroup parent) {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false);
        EpoxyModelRecyclerView recView = new EpoxyModelRecyclerView(parent.getContext(), null, layoutManager);
        return recView;
    }

    @Override
    public void bind(EpoxyModelRecyclerView view) {
        view.setModels(models);
    }
}
