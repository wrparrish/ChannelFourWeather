package com.ruthlessprogramming.channelfourweather.common.di;

import com.ruthlessprogramming.channelfourweather.ChannelFour;
import com.ruthlessprogramming.channelfourweather.MainActivity;
import com.ruthlessprogramming.channelfourweather.common.di.component.ChannelFourComponent;

import org.jetbrains.annotations.NotNull;

public class Injector {
    private static final Injector ourInstance = new Injector();
    private ChannelFourComponent component;

    public static Injector getInstance() {
        return ourInstance;
    }

    private Injector() {
    }

    public void setComponent(ChannelFourComponent component) {
        this.component = component;
    }

    public void inject(ChannelFour app) {
        ourInstance.component.inject(app);
    }

    public void inject(@NotNull MainActivity mainActivity) {
        ourInstance.component.inject(mainActivity);
    }
}
