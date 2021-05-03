package com.gsatechworld.linnaas.views.ui.fragments;


import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.linnaas.utils.ViewModelProviderFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class ProfileFragmentModule {

    @Provides
    @Named("ProfileFragment")
    ViewModelProvider.Factory provideViewModelProvider(ProfileFragmentViewModel viewModel){
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
