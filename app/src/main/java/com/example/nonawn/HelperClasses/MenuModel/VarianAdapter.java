package com.example.nonawn.HelperClasses.MenuModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nonawn.HelperClasses.MenuModel.EventBus.UpdateCartItem;
import com.example.nonawn.HelperClasses.MenuModel.Listener.CartLoadListener;
import com.example.nonawn.HelperClasses.MenuModel.Listener.RecyclerViewClickListener;
import com.example.nonawn.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VarianAdapter extends RecyclerView.Adapter<VarianAdapter.VarianViewHolder> {

    private Context context;
    private List<MenuHelperClass>menuHelperClassList;
    private CartLoadListener cartLoadListener;

    public VarianAdapter(Context context, List<MenuHelperClass> menuHelperClassList, CartLoadListener cartLoadListener) {
        this.context = context;
        this.menuHelperClassList = menuHelperClassList;
        this.cartLoadListener = cartLoadListener;
    }

    @NonNull
    @Override
    public VarianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VarianViewHolder(LayoutInflater.from(context).inflate(R.layout.varianmenu_card_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VarianViewHolder holder, int position) {
        Glide.with(context)
                .load(menuHelperClassList.get(position).getImage())
                .into(holder.cardvarian_image);
        holder.varian_harga.setText(new StringBuilder("IDR ").append(menuHelperClassList.get(position).getHarga()));
        holder.varian_nama.setText(new StringBuilder().append(menuHelperClassList.get(position).getVarian()));

        holder.setListener((view, adapterPosition) -> {
            addToCart(menuHelperClassList.get(position));

        });

    }

    private void addToCart(MenuHelperClass menuHelperClass) {
        String getPhoneNumber;
        Intent intent = ((Activity) context).getIntent();
        getPhoneNumber = intent.getStringExtra("phone");

        DatabaseReference userCart = FirebaseDatabase
                .getInstance()
                .getReference("Cart")
                .child(getPhoneNumber);

        Log.e("KEY",""+menuHelperClass.getKey());
        Log.e("VARIAN",""+menuHelperClass.getVarian());

        userCart.child(menuHelperClass.getVarian())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){ //kondisi jika ada produk di keranjang

                            //update jumlah produk dan total harga
                            CartHelperClass cartHelperClass = snapshot.getValue(CartHelperClass.class);
                            cartHelperClass.setQty_barang(cartHelperClass.getQty_barang()+1);
                            Map<String,Object> updateData = new HashMap<>();
                            updateData.put("qty_brg",cartHelperClass.getQty_barang());
                            updateData.put("total_harga",cartHelperClass.getQty_barang()*Float.parseFloat(cartHelperClass.getHarga()));

                            userCart.child(cartHelperClass.getVarian())
                                    .updateChildren(updateData)
                                    .addOnSuccessListener(aVoid -> {
                                        cartLoadListener.onCartLoadFailed("Berhasil menambahkan ke keranjang");
                                    })
                                    .addOnFailureListener(e -> cartLoadListener.onCartLoadFailed(e.getMessage()));

                        }
                        else {//jika tidak ada produk dalam cart, add new
                            String price = menuHelperClass.getHarga().substring(0, menuHelperClass.getHarga().length()-1);

                            CartHelperClass cartHelperClass = new CartHelperClass();
                            cartHelperClass.setImage(menuHelperClass.getImage());
                            cartHelperClass.setVarian(menuHelperClass.getVarian());
                            cartHelperClass.setHarga(price);
                            //cartHelperClass.setKey(menuHelperClass.getKey());

                            cartHelperClass.setQty_barang(1);
                            cartHelperClass.setTotal_harga(Float.parseFloat(price));

                            userCart.child(menuHelperClass.getVarian())
                                    .setValue(cartHelperClass)
                                    .addOnSuccessListener(aVoid -> {
                                        cartLoadListener.onCartLoadFailed("Berhasil menambahkan ke keranjang");
                                    })
                                    .addOnFailureListener(e -> cartLoadListener.onCartLoadFailed(e.getMessage()));
                        }
                        EventBus.getDefault().postSticky(new UpdateCartItem());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.onCartLoadFailed(error.getMessage());

                    }
                });

    }


    @Override
    public int getItemCount() {
        return menuHelperClassList.size();
    }

    public class VarianViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.variancard_image)
        ImageView cardvarian_image;
        @BindView(R.id.varian_name)
        TextView varian_nama;
        @BindView(R.id.varian_price)
        TextView varian_harga;

        RecyclerViewClickListener listener;

        public void setListener(RecyclerViewClickListener listener) {
            this.listener = listener;
        }

        private Unbinder unbinder;
        public VarianViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onRecyclerClick(v,getAdapterPosition());
        }
    }

}