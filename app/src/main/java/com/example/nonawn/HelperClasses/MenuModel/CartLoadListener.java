package com.example.nonawn.HelperClasses.MenuModel;

import java.util.List;

public interface CartLoadListener {
    void onCartLoadSuccess(List<CartHelperClass>cartHelperClassList);
    void onCartLoadFailed(List<CartHelperClass>CartList);
}
