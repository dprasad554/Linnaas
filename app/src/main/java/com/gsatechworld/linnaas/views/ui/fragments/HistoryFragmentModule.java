package com.gsatechworld.linnaas.views.ui.fragments;


import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;
import com.gsatechworld.linnaas.utils.history.HistoryRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class HistoryFragmentModule {

    @Provides
    @Named("HistoryFragment")
    ViewModelProvider.Factory provideViewModelProvider(HistoryFragmentViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }


    /*@Provides
    HomeFragmentViewModel provideAppointmentCompletedFragmentViewModel(HomeFragmentViewModel repository){
        return new HomeFragmentViewModel(repository);
    }*/

    /*@Provides
    AppointmentUpcomingFragmentRepository provideAppointmentCompletedFragmentRepository(NetworkAPI networkAPI){
        return new AppointmentUpcomingFragmentRepository(networkAPI);
    }*/

}
