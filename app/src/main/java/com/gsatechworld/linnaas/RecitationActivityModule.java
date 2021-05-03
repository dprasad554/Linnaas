package com.gsatechworld.linnaas;

import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.RecitationRepository;
import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;
import com.gsatechworld.linnaas.utils.homepage.HomeRepository;
import com.gsatechworld.linnaas.viewmodels.HomeViewModel;
import com.gsatechworld.linnaas.viewmodels.RecitationViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class RecitationActivityModule {

    @Provides
    RecitationViewModel providesViewModel(RecitationRepository recitationRepository){
        return new RecitationViewModel(recitationRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(RecitationViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    RecitationRepository provideRepository(NetworkAPI networkAPI){
        return new RecitationRepository(networkAPI);
    }

}
