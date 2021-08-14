package com.example.nonawn.HelperClasses.MenuModel.Listener;

import com.example.nonawn.HelperClasses.MenuModel.CartHelperClass;

import java.util.List;

public interface CartLoadListener {
    void onCartLoadSuccess(List<CartHelperClass>cartHelperClassList);
    void onCartLoadFailed(String message);
}
