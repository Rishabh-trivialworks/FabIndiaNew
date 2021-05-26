package com.fabindia.shopping.activities.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.fabindia.shopping.R;
import com.fabindia.shopping.fragments.cart.CartFragment;
import com.fabindia.shopping.fragments.home.HomeFragment;
import com.fabindia.shopping.fragments.menu.MenuFragment;
import com.fabindia.shopping.fragments.profile.ProfileFragment;
import com.fabindia.shopping.fragments.search.SearchFragment;
import com.fabindia.shopping.listeners.NetworChangeListener;
import com.fabindia.shopping.receivers.NetworkConnectivityReceiver;
import com.fabindia.shopping.storage.SharedPreferenceUtil;
import com.fabindia.shopping.utils.CommonUtils;
import com.fabindia.shopping.utils.ImageUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BaseActivity<B extends ViewBinding> extends AppCompatActivity implements NetworChangeListener {
    private ProgressDialog mProgressDialog;
    public Context mContext;
    private BroadcastReceiver mNetworkReceiver;
    protected B mBinding;
    public ImageUtils mImageUtils;
    public SharedPreferenceUtil mPreferenceUtil;
    public Toolbar mToolbar;





    protected void bindView(B dataBinding) {
        this.mBinding = dataBinding;
        super.setContentView(dataBinding.getRoot());
        mContext =this;
        mNetworkReceiver = new NetworkConnectivityReceiver(this);
        registerNetworkBroadcastForNougat();
        mImageUtils = ImageUtils.getInstance(mContext);
        mPreferenceUtil = SharedPreferenceUtil.getInstance(mContext);
        //api= RetrofitClientInstance.getRetrofitInstance().create(ApiDataService.class);

    }


    public void setToolbar(Toolbar toolbar){
        mToolbar =toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setBackNavigationFinish();
       // setHomeAsUpIndicatorIcon(R.drawable.ic_arrow_back);
        setTitle("Welcome: " );


    }







    public void hidetoolbar(){
      getSupportActionBar().hide();
    }
    public void setBackNavigationFinish()
    {
       getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setTitle(String title){
        getSupportActionBar().setTitle(title);
        getToolbar().setTitleTextColor(getResources().getColor(android.R.color.white));


    }
    public void setHomeAsUpIndicatorIcon(int resId){
        getSupportActionBar().setHomeAsUpIndicator(resId);
    }
    public void hideHomeAsUpIndicatorIcon(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    public Toolbar getToolbar(){
        return mToolbar;
    }
    public void  setBottomNavigationListener(BottomNavigationView bottomNavigationView,int containerViewId){
         BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = item -> {
                    Fragment fragment;
                    switch (item.getItemId()) {
                        case R.id.navHome:
                            fragment = HomeFragment.newInstance(); // add your fragment
                            loadFragment(fragment,containerViewId);
                            return true;
                        case R.id.navMenu:
                            fragment = MenuFragment.newInstance(); // add your fragment
                            loadFragment(fragment,containerViewId);
                            return true;
                        case R.id.navSearch:
                            fragment = SearchFragment.newInstance(); // add your fragment
                            loadFragment(fragment,containerViewId);
                            return true;
                        case R.id.navProfile:
                            fragment = ProfileFragment.newInstance(); // add your fragment
                            loadFragment(fragment,containerViewId);
                            return true;
                        case R.id.navCart:
                            fragment = CartFragment.newInstance(); // add your fragment
                            loadFragment(fragment,containerViewId);
                            return true;
                    }
                    return false;
                };
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    public void loadFragment(Fragment fragment,int containerViewId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

    }
    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }

    @Override
    public void onNetworkChange(boolean status) {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
