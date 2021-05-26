package com.fabindia.shopping.repository;

public class CommonRepository {
    private static CommonRepository mInstance;

    public static CommonRepository getInstance(){

        if(mInstance == null){
            mInstance = new CommonRepository();
        }
        return mInstance;
    }

}
