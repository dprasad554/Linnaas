package com.gsatechworld.linnaas.views.ui.activity;

import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;
import com.gsatechworld.linnaas.utils.search.SearchRepository;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpRepository;
import com.gsatechworld.linnaas.viewmodels.SearchModel;
import com.gsatechworld.linnaas.viewmodels.VerifyOtpModel;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {

    @Provides
    SearchModel providesViewModel(SearchRepository searchRepository) {
        return new SearchModel(searchRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(SearchModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }


    @Provides
    SearchRepository provideRepository(NetworkAPI networkAPI)
    {
        return new SearchRepository(networkAPI);
    }

}