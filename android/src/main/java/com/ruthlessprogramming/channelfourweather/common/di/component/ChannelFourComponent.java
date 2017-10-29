package com.ruthlessprogramming.channelfourweather.common.di.component;

/**
 * Created by billyparrish on 10/28/17.
 */

import com.ruthlessprogramming.channelfourweather.ChannelFour;
import com.ruthlessprogramming.channelfourweather.MainActivity;
import com.ruthlessprogramming.channelfourweather.common.di.module.AppModule;
import com.ruthlessprogramming.channelfourweather.common.di.module.DataModule;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface ChannelFourComponent {

    void inject(ChannelFour app);

    void inject(MainActivity mainActivity);
}