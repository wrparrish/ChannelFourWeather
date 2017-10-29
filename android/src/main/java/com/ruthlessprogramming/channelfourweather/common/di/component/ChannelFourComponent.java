package com.ruthlessprogramming.channelfourweather.common.di.component;

import com.ruthlessprogramming.channelfourweather.ChannelFour;
import com.ruthlessprogramming.channelfourweather.MainActivity;
import com.ruthlessprogramming.channelfourweather.common.di.module.AppModule;
import com.ruthlessprogramming.channelfourweather.common.di.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface ChannelFourComponent {

    void inject(ChannelFour app);

    void inject(MainActivity mainActivity);
}