package com.gsatechworld.linnaas.views.ui.activity;

import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;
import com.gsatechworld.linnaas.utils.profile.ProfileRepository;
import com.gsatechworld.linnaas.viewmodels.ProfileViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileActivityModule {

    @Provides
    ProfileViewModel providesViewModel(ProfileRepository profileRepository){
        return new ProfileViewModel(profileRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(ProfileViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    ProfileRepository profileRepository(NetworkAPI networkAPI){
        return new ProfileRepository(networkAPI);
    }

}