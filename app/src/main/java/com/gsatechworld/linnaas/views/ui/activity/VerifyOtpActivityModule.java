package com.gsatechworld.linnaas.views.ui.activity;

import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;
import com.gsatechworld.linnaas.utils.login.LoginRepository;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpRepository;
import com.gsatechworld.linnaas.viewmodels.VerifyOtpModel;

import dagger.Module;
import dagger.Provides;

@Module
public class VerifyOtpActivityModule {

    @Provides
    VerifyOtpModel providesViewModel(VerifyOtpRepository verifyOtpRepository) {
        return new VerifyOtpModel(verifyOtpRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(VerifyOtpModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    VerifyOtpRepository provideRepository(NetworkAPI networkAPI)
    {
        return new VerifyOtpRepository(networkAPI);
    }
}