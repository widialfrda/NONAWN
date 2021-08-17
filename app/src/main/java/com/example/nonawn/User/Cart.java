package com.example.nonawn.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nonawn.HelperClasses.MenuModel.CartAdapter;
import com.example.nonawn.HelperClasses.MenuModel.CartHelperClass;
import com.example.nonawn.HelperClasses.MenuModel.Listener.CartLoadListener;
import com.example.nonawn.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cart extends AppCompatActivity implements CartLoadListener {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        init();
        loadCartFromFirebase();
    }

    private void loadCartFromFirebase() {
        FirebaseDatabase.getInstance().getReference("Cart").child("User_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


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
        txt_cart.setText(new StringBuilder("IDR").append(sum));
        CartAdapter adapter = new CartAdapter(this, cartHelperClassList);
        recyclerCart.setAdapter(adapter);
    }

    @Override
    public void onCartLoadFailed(String message) {
        Snackbar.make(layout_cart,message,Snackbar.LENGTH_LONG).show();

    }
}