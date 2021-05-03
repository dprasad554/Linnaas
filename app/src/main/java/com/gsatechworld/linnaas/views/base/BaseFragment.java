package com.gsatechworld.linnaas.views.base;


import android.content.Context;

import androidx.lifecycle.ViewModel;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewModel> extends DaggerFragment {

    private T viewModel;

    public abstract T getViewModel();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
