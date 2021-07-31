package com.example.nonawn.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import com.example.nonawn.HelperClasses.MenuModel.CartHelperClass;
import com.example.nonawn.HelperClasses.MenuModel.CartLoadListener;
import com.example.nonawn.HelperClasses.MenuModel.MenuHelperClass;
import com.example.nonawn.HelperClasses.MenuModel.MenuLoadListener;
import com.example.nonawn.HelperClasses.MenuModel.SpaceItemDecoration;
import com.example.nonawn.HelperClasses.MenuModel.VarianAdapter;
import com.example.nonawn.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullMenuMakanan extends AppCompatActivity implements MenuLoadListener, CartLoadListener {

//    @BindView(R.id.recyclerview_varianspicy)
//    RecyclerView recyclerspicy;
    @BindView(R.id.recyclerview_variannonspicy)
    RecyclerView recyclernonspicy;
    @BindView(R.id.menu_layout)
    RelativeLayout layout_menu;
    @BindView(R.id.notif_cart)
    NotificationBadge qty_cart;
    @BindView(R.id.frame_cart)
    FrameLayout btn_cart;

    MenuLoadListener menuLoadListener;
    CartLoadListener cartLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_menu_makanan);

        init();
        loadMenufromFirebase();
    }

    private void loadMenufromFirebase() {
        List<MenuHelperClass> menuHelperClasses = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Varian Rasa")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot menuSnapshot:snapshot.getChildren()){
                                MenuHelperClass menuHelperClass = menuSnapshot.getValue(MenuHelperClass.class);
                                menuHelperClass.setKey(menuSnapshot.getKey());
                                menuHelperClasses.add(menuHelperClass);
                            }
                            menuLoadListener.onMenuLoadSuccess(menuHelperClasses);
                        }
                        else
                            menuLoadListener.onMenuLoadFailed("Varian Rasa Tidak Ditemukan");


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        menuLoadListener.onMenuLoadFailed(error.getMessage());

                    }
                });
    }

    private void init(){
        ButterKnife.bind(this);

        menuLoadListener = this;
        cartLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclernonspicy.setLayoutManager(gridLayoutManager);
        recyclernonspicy.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onMenuLoadSuccess(List<MenuHelperClass> menuHelperClassList) {
        VarianAdapter adapter = new VarianAdapter(this, menuHelperClassList);
        recyclernonspicy.setAdapter(adapter);
    }

    @Override
    public void onMenuLoadFailed(String message) {
        Snackbar.make(layout_menu, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCartLoadSuccess(List<CartHelperClass> cartHelperClassList) {

    }

    @Override
    public void onCartLoadFailed(List<CartHelperClass> CartList) {

    }
}