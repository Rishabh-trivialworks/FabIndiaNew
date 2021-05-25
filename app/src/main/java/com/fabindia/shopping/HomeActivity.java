package com.fabindia.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.fabindia.shopping.Fragment.CartFragment;
import com.fabindia.shopping.Fragment.HomeFragment;
import com.fabindia.shopping.Fragment.MenuFragment;
import com.fabindia.shopping.Fragment.ProfileFragment;
import com.fabindia.shopping.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navHome:
                    fragment = new HomeFragment(); // add your fragment
                    loadFragment(fragment);
                    return true;
                case R.id.navMenu:
                    fragment = new MenuFragment(); // add your fragment
                    loadFragment(fragment);
                    return true;
                case R.id.navSearch:
                    fragment = new SearchFragment(); // add your fragment
                    loadFragment(fragment);
                    return true;
                case R.id.navProfile:
                    fragment = new ProfileFragment(); // add your fragment
                    loadFragment(fragment);
                    return true;
                case R.id.navCart:
                    fragment = new CartFragment(); // add your fragment
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}