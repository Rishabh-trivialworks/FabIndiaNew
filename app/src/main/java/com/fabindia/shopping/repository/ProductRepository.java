package com.fabindia.shopping.repository;

public class ProductRepository {
    private static ProductRepository mInstance;

    public static ProductRepository getInstance(){

        if(mInstance == null){
            mInstance = new ProductRepository();
        }
        return mInstance;
    }

}
