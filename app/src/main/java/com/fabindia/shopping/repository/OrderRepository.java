package com.fabindia.shopping.repository;

public class OrderRepository {
    private static OrderRepository mInstance;

    public static OrderRepository getInstance(){

        if(mInstance == null){
            mInstance = new OrderRepository();
        }
        return mInstance;
    }
}
