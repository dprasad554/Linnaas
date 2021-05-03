package com.gsatechworld.linnaas.views.ui.activity;

import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;
import com.gsatechworld.linnaas.utils.login.LoginRepository;
import com.gsatechworld.linnaas.viewmodels.LoginViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    LoginViewModel providesViewModel(LoginRepository repository){
        return new LoginViewModel(repository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(LoginViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    LoginRepository provideRepository(NetworkAPI networkAPI)
    {
        return new LoginRepository(networkAPI);
    }

}