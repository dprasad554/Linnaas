package com.gsatechworld.linnaas.core.components;


import com.gsatechworld.linnaas.MainApplication;
import com.gsatechworld.linnaas.core.builders.ActivityBuilder;
import com.gsatechworld.linnaas.core.modules.AppModule;
import com.gsatechworld.linnaas.core.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
                    AppModule.class,
                    NetworkModule.class,
                    ActivityBuilder.class})

public interface VersesAppMainComponent extends AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MainApplication> {
    }
}