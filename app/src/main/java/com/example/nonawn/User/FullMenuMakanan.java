package com.example.nonawn.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.nonawn.Common.SignUpLogin.PilihanReset;
import com.example.nonawn.HelperClasses.MenuModel.CartHelperClass;
import com.example.nonawn.HelperClasses.MenuModel.EventBus.UpdateCartItem;
import com.example.nonawn.HelperClasses.MenuModel.Listener.CartLoadListener;
import com.example.nonawn.HelperClasses.MenuModel.MenuHelperClass;
import com.example.nonawn.HelperClasses.MenuModel.MenuLoadListener;
import com.example.nonawn.HelperClasses.MenuModel.SpaceItemDecoration;
import com.example.nonawn.HelperClasses.MenuModel.VarianAdapter;
import com.example.nonawn.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullMenuMakanan extends AppCompatActivity implements MenuLoadListener, CartLoadListener {

    String uipn;

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
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if (EventBus.getDefault().hasSubscriberForEvent(UpdateCartItem.class))
            EventBus.getDefault().removeStickyEvent(UpdateCartItem.class);
        EventBus.getDefault().unregister(this);

        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onUpdateCart(UpdateCartItem event){
        countCartItem();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_menu_makanan);

        Intent intent = getIntent();
        uipn = intent.getStringExtra("uipn");
        Log.e("UIPN-FMM",""+uipn);

        init();
        loadMenufromFirebase();
        countCartItem();
    }
    //get data dari tabel varian rasa utk ngedisplay halaman full menu
    private void loadMenufromFirebase() {

        String gambar, harga;
        List<MenuHelperClass> menuHelperClasses = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("Varian Rasa").orderByChild("Varian Rasa")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                        /*Log.e("produk",""+snapshot.child("Produk").getValue().toString());
                        Log.e("gambar uri",""+snapshot.child("Gambar").getValue().toString());
                        Log.e("harga",""+snapshot.child("Harga").getValue().toString());*/

                        MenuHelperClass menuHelperClass = snapshot.getValue(MenuHelperClass.class);
                        menuHelperClass.setVarian(snapshot.child("Produk").getValue().toString());
                        menuHelperClass.setImage(snapshot.child("Gambar").getValue().toString());
                        menuHelperClass.setHarga(snapshot.child("Harga").getValue().toString());
                        menuHelperClasses.add(menuHelperClass);

                        menuLoadListener.onMenuLoadSuccess(menuHelperClasses);
                    }

                    @Override
                    public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

        /*FirebaseDatabase.getInstance()
                .getReference().child("Varian Rasa")
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
                        //menuLoadListener.onMenuLoadFailed(error.getMessage());
                    }
                });*/
    }

    private void init(){
        ButterKnife.bind(this);

        menuLoadListener = this;
        cartLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclernonspicy.setLayoutManager(gridLayoutManager);
        recyclernonspicy.addItemDecoration(new SpaceItemDecoration());

        btn_cart.setOnClickListener(v -> startActivity(new Intent(this, Cart.class).putExtra("uipn",uipn)));
    }

    @Override
    public void onMenuLoadSuccess(List<MenuHelperClass> menuHelperClassList) {
        VarianAdapter adapter = new VarianAdapter(this, menuHelperClassList, cartLoadListener);
        recyclernonspicy.setAdapter(adapter);
    }

    @Override
    public void onMenuLoadFailed(String message) {
        Snackbar.make(layout_menu, message, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onCartLoadSuccess(List<CartHelperClass> cartHelperClassList) {
        int cartSum = 0;
        for (CartHelperClass cartHelperClass : cartHelperClassList)
            cartSum += cartHelperClass.getQty_barang();
        qty_cart.setNumber(cartSum);
    }

    @Override
    public void onCartLoadFailed(String message) {
        Snackbar.make(layout_menu,message,Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        countCartItem();
    }

    //get data untuk update notif bagde
    private void countCartItem() {
        List<CartHelperClass> cartHelperClasses = new ArrayList<>();
        FirebaseDatabase
                .getInstance().getReference("Cart")
                .child(uipn)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot cartSnapshot:snapshot.getChildren()){
                            CartHelperClass cartHelperClass = cartSnapshot.getValue(CartHelperClass.class);
                            cartHelperClass.setKey(cartSnapshot.getKey());
                            cartHelperClasses.add(cartHelperClass);
                        }
                        cartLoadListener.onCartLoadSuccess(cartHelperClasses);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.onCartLoadFailed(error.getMessage());

                    }
                });
    }

    public void back_dashboard(View view) {
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
    }
}