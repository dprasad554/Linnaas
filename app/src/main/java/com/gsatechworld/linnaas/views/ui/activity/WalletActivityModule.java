package com.gsatechworld.linnaas.views.ui.activity;

import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;
import com.gsatechworld.linnaas.utils.wallet.WalletHistoryRepository;
import com.gsatechworld.linnaas.viewmodels.WalletModel;

import dagger.Module;
import dagger.Provides;

@Module
public class WalletActivityModule {

    @Provides
    WalletModel providesViewModel(WalletHistoryRepository walletHistoryRepository) {
        return new WalletModel(walletHistoryRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(WalletModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    WalletHistoryRepository provideRepository(NetworkAPI networkAPI){
        return new WalletHistoryRepository(networkAPI);
    }

}