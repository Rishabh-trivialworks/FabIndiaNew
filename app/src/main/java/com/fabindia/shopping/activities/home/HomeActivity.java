package com.fabindia.shopping.activities.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.fabindia.shopping.R;
import com.fabindia.shopping.activities.base.BaseActivity;
import com.fabindia.shopping.databinding.ActivityHomeBinding;
import com.fabindia.shopping.fragments.home.HomeFragment;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    HomeViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView(ActivityHomeBinding.inflate(getLayoutInflater()));
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        setBottomNavigationListener(mBinding.bottomNavigationView,R.id.frameContainer);
        loadFragment(new HomeFragment(),R.id.frameContainer);

    }




}