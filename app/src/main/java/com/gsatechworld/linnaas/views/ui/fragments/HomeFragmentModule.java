package com.gsatechworld.linnaas.views.ui.fragments;


import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class HomeFragmentModule {

    @Provides
    @Named("HomeFragment")
    ViewModelProvider.Factory provideViewModelProvider(HomeFragmentViewModel viewModel){
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
