package com.gsatechworld.linnaas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.search.SearchRepository;
import com.gsatechworld.linnaas.utils.search.SearchResp;
import com.gsatechworld.linnaas.utils.wallet.WalletHistoryRepository;
import com.gsatechworld.linnaas.utils.wallet.Walletresp;

import javax.inject.Inject;

public class WalletModel extends ViewModel {

    private WalletHistoryRepository walletHistoryRepository;
    private LiveData<Walletresp> walletrespLiveData;
    @Inject
    public WalletModel(WalletHistoryRepository walletHistoryRepository) {
        this.walletHistoryRepository = walletHistoryRepository;
    }

    public LiveData<Walletresp> getWalletHistory(Walletresp walletresp){
        walletrespLiveData = new MutableLiveData<>();
        walletrespLiveData = walletHistoryRepository.getWalletHistory(walletresp);
        return walletrespLiveData;
    }
}
