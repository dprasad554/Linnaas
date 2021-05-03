package com.gsatechworld.linnaas;

import com.gsatechworld.linnaas.core.components.DaggerVersesAppMainComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

import javax.inject.Inject;

import static dagger.android.AndroidInjection.inject;

public class MainApplication extends DaggerApplication {

    private static MainApplication instance;
    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized MainApplication getInstance() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerVersesAppMainComponent.builder().create(this);
    }
}