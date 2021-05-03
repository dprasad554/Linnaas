package com.gsatechworld.linnaas.views.ui.fragments;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileFragmentProvider {

    @ContributesAndroidInjector(modules = ProfileFragmentModule.class)
    abstract ProfileFragment provideProfileFragmentFactory();

}
