package com.gsatechworld.linnaas.core.builders;


import com.gsatechworld.linnaas.RecitationActivity;
import com.gsatechworld.linnaas.RecitationActivityModule;
import com.gsatechworld.linnaas.views.ui.activity.HomeActivity;
import com.gsatechworld.linnaas.views.ui.activity.HomeActivityModule;
import com.gsatechworld.linnaas.views.ui.activity.LoginActivity;
import com.gsatechworld.linnaas.views.ui.activity.LoginActivityModule;
import com.gsatechworld.linnaas.views.ui.activity.ProfileActivity;
import com.gsatechworld.linnaas.views.ui.activity.ProfileActivityModule;
import com.gsatechworld.linnaas.views.ui.activity.SearchActivity;
import com.gsatechworld.linnaas.views.ui.activity.SearchActivityModule;
import com.gsatechworld.linnaas.views.ui.activity.VerifyOtpActivity;
import com.gsatechworld.linnaas.views.ui.activity.VerifyOtpActivityModule;
import com.gsatechworld.linnaas.views.ui.activity.WalletActivity;
import com.gsatechworld.linnaas.views.ui.activity.WalletActivityModule;
import com.gsatechworld.linnaas.views.ui.fragments.HistoryFragmentProvider;
import com.gsatechworld.linnaas.views.ui.fragments.HomeFragmentProvider;
import com.gsatechworld.linnaas.views.ui.fragments.ProfileFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = VerifyOtpActivityModule.class)
    abstract VerifyOtpActivity contributeVerifyOtpActivity();

    @ContributesAndroidInjector(modules = ProfileActivityModule.class)
    abstract ProfileActivity contributeProfileActivity();

    /*@ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity contributeHomeActivity();*/

    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity contributeSearchActivity();

    @ContributesAndroidInjector(modules = WalletActivityModule.class)
    abstract WalletActivity contributeWalletActivity();

    @ContributesAndroidInjector(modules = {HomeActivityModule.class, HomeFragmentProvider.class, HistoryFragmentProvider.class, ProfileFragmentProvider.class})
    abstract HomeActivity contributeMainActivity();

    /*@ContributesAndroidInjector(modules = {HomeActivityModule.class, HistoryFragmentProvider.class})
    abstract HomeActivity contributeMainActivity();*/

    /*@ContributesAndroidInjector(modules = RegisterActivityModeule.class)
    abstract RegisterActivity contributeRegisterActivity();*/

    @ContributesAndroidInjector(modules = RecitationActivityModule.class)
    abstract RecitationActivity contributeRecitationActivity();

}
