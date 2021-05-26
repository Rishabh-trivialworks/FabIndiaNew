package com.fabindia.shopping.fragments.menu;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabindia.shopping.R;
import com.fabindia.shopping.databinding.MenuFragmentBinding;
import com.fabindia.shopping.fragments.base.BaseFragment;
import com.fabindia.shopping.fragments.search.SearchViewModel;

public class MenuFragment  extends BaseFragment<MenuFragmentBinding> {

    private MenuViewModel mViewModel;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return bindView(MenuFragmentBinding.inflate(inflater,container,false));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
    }

}