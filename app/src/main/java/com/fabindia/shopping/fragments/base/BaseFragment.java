package com.fabindia.shopping.fragments.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.fabindia.shopping.listeners.NetworChangeListener;
import com.fabindia.shopping.receivers.NetworkConnectivityReceiver;
import com.fabindia.shopping.storage.SharedPreferenceUtil;
import com.fabindia.shopping.utils.CommonUtils;
import com.fabindia.shopping.utils.ImageUtils;

public class BaseFragment <B extends ViewBinding> extends Fragment implements NetworChangeListener {
    protected B mBinding;
    public Context mContext;
    private BroadcastReceiver mNetworkReceiver;
    public ImageUtils mImageUtils;
    public SharedPreferenceUtil mPreferenceUtil;
    private ProgressDialog mProgressDialog;

    protected View bindView(B dataBinding) {
        this.mBinding = dataBinding;
        mContext =getActivity();
        mNetworkReceiver = new NetworkConnectivityReceiver(this);
        registerNetworkBroadcastForNougat();
        mImageUtils = ImageUtils.getInstance(mContext);
        mPreferenceUtil = SharedPreferenceUtil.getInstance(mContext);
        //api= RetrofitClientInstance.getRetrofitInstance().create(ApiDataService.class);
        return dataBinding.getRoot();

    }
    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mContext.registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mContext.registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

    }
    protected void unregisterNetworkChanges() {
        try {
            mContext.unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    public void hideLoading() {
        try{
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }
        }catch (Exception e){
           e.printStackTrace();
        }

    }
    public void showLoading() {
        try{
            hideLoading();
            mProgressDialog = CommonUtils.showLoadingDialog(mContext);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
    @Override
    public void onNetworkChange(boolean status) {

    }
}
