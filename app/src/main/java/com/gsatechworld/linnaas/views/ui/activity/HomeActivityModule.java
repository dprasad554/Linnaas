package com.gsatechworld.linnaas.views.ui.activity;

import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;
import com.gsatechworld.linnaas.utils.homepage.HomeRepository;
import com.gsatechworld.linnaas.viewmodels.HomeViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    @Provides
    HomeViewModel providesViewModel(HomeRepository homeRepository){
        return new HomeViewModel(homeRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(HomeViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    HomeRepository provideRepository(NetworkAPI networkAPI){
        return new HomeRepository(networkAPI);
    }

}