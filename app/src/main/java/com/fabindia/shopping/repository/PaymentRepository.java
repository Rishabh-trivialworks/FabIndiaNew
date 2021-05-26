package com.fabindia.shopping.repository;

public class PaymentRepository {

    private static PaymentRepository mInstance;

    public static PaymentRepository getInstance(){

        if(mInstance == null){
            mInstance = new PaymentRepository();
        }
        return mInstance;
    }

}
