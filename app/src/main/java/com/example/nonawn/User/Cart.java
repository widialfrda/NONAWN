package com.example.nonawn.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nonawn.Common.SignUpLogin.Login;
import com.example.nonawn.HelperClasses.MenuModel.CartAdapter;
import com.example.nonawn.HelperClasses.MenuModel.CartHelperClass;
import com.example.nonawn.HelperClasses.MenuModel.EventBus.UpdateCartItem;
import com.example.nonawn.HelperClasses.MenuModel.Listener.CartLoadListener;
import com.example.nonawn.HelperClasses.MenuModel.MenuHelperClass;
import com.example.nonawn.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cart extends AppCompatActivity implements CartLoadListener {

    String uipn;

    @BindView(R.id.cart_recycler)
    RecyclerView recyclerCart;
    @BindView(R.id.layout_cart)
    RelativeLayout layout_cart;
    @BindView(R.id.cart_back)
    ImageView back_cart;
    @BindView(R.id.txt_cart)
    TextView txt_cart;

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

        loadCartFromFirebase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        uipn = intent.getStringExtra("uipn");
        Log.e("UIPN-CART",""+uipn);

        init();
        loadCartFromFirebase();
    }

    private void loadCartFromFirebase() {



        List<CartHelperClass> cartHelperClasses = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Cart").child(uipn)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot cartSnapshot:snapshot.getChildren()){
                                CartHelperClass cartHelperClass = cartSnapshot.getValue(CartHelperClass.class);
                                cartHelperClass.setKey(cartSnapshot.getKey());

                                cartHelperClasses.add(cartHelperClass);
                            }
                            cartLoadListener.onCartLoadSuccess(cartHelperClasses);
                        }
                        else
                            cartLoadListener.onCartLoadFailed("Keranjang Kosong");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.onCartLoadFailed(error.getMessage());

                    }
                });
    }

    private void init(){
        ButterKnife.bind(this);

        cartLoadListener = this;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerCart.setLayoutManager(layoutManager);
        recyclerCart.addItemDecoration(new DividerItemDecoration(this,layoutManager.getOrientation()));

        back_cart.setOnClickListener(v -> finish());
    }

    @Override
    public void onCartLoadSuccess(List<CartHelperClass> cartHelperClassList) {
        double sum = 0;
        for (CartHelperClass cartHelperClass : cartHelperClassList){
            sum+=cartHelperClass.getTotal_harga();
        }
        txt_cart.setText(new StringBuilder("IDR ").append(sum));
        CartAdapter adapter = new CartAdapter(this, cartHelperClassList);
        recyclerCart.setAdapter(adapter);
    }

    @Override
    public void onCartLoadFailed(String message) {
        Snackbar.make(layout_cart,message,Snackbar.LENGTH_LONG).show();

    }

    public void back_fullMenuMakanan(View view) {
        startActivity(new Intent(getApplicationContext(), FullMenuMakanan.class));
        finish();
    }
}