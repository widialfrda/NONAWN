package com.example.nonawn.HelperClasses.MenuModel;

import java.util.List;

public interface MenuLoadListener {
    void onMenuLoadSuccess(List<MenuHelperClass> menuHelperClassList);
    void onMenuLoadFailed(String message);
}
