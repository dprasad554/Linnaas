package com.gsatechworld.linnaas.views.ui.fragments;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HistoryFragmentProvider {

    @ContributesAndroidInjector(modules = HistoryFragmentModule.class)
    abstract HistoryFragment provideHistoryFragmentFactory();

}
