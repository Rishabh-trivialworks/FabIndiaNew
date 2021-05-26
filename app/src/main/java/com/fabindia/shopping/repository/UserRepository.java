package com.fabindia.shopping.repository;

import com.fabindia.shopping.network.RestClient;

public class UserRepository {
    private static UserRepository mInstance;

    public static UserRepository getInstance(){

        if(mInstance == null){
            mInstance = new UserRepository();
        }
        return mInstance;
    }
}
