package com.example.nonawn.HelperClasses.MenuModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.nonawn.R;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartHelperClass> cartHelperClassList;
    private Intent intent;

    public CartAdapter(Context context, List<CartHelperClass> cartHelperClassList) {
        this.context = context;
        this.cartHelperClassList = cartHelperClassList;
        this.intent = intent;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.card_forcart,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Glide.with(context)
                .load(cartHelperClassList.get(position).getImage())
                .into(holder.variancard_image);
        holder.txt_harga_cart.setText(new StringBuilder("IDR ").append(cartHelperClassList.get(position).getHarga()));
        holder.cart_varian.setText(new StringBuilder().append(cartHelperClassList.get(position).getVarian()));
        holder.txt_qty.setText(new StringBuilder().append(cartHelperClassList.get(position).getQty_barang()));

        //Event untuk btn add, minus, dan delete
        holder.btn_minus.setOnClickListener(v -> {
            minusCartItem(holder, cartHelperClassList.get(position));
        });
        holder.btn_plus.setOnClickListener(v -> {
            plusCartItem(holder, cartHelperClassList.get(position));
        });
        holder.delete_cart.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Hapus Produk")
                    .setMessage("Apakah kamu ingin menghapus produk ini?")
                    .setNegativeButton("Tidak", (dialog1, which) -> dialog1.dismiss())
                    .setPositiveButton("Iya", (dialog12, which) -> {

                        //Hapus Item dari cart
                        notifyItemRemoved(position);

                        deleteFromFirebase(cartHelperClassList.get(position));
                        dialog12.dismiss();

                    }).create();
            dialog.show();
        });

    }

    private void deleteFromFirebase(CartHelperClass cartHelperClass) {
        String getPhone_CA;
        Intent intent = ((Activity) context).getIntent();
        getPhone_CA = intent.getStringExtra("uipn");

        FirebaseDatabase.getInstance()
                .getReference("Cart")
                .child(getPhone_CA)
                .child(cartHelperClass.getKey())
                .removeValue()
                .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new UpdateCartItem()));
        Log.e("KEY",""+cartHelperClass.getKey());
        Log.e("VARIAN",""+cartHelperClass.getVarian());
    }

    private void plusCartItem(CartViewHolder holder, CartHelperClass cartHelperClass) {

        cartHelperClass.setQty_barang(cartHelperClass.getQty_barang() + 1);
        cartHelperClass.setTotal_harga(cartHelperClass.getQty_barang()*Float.parseFloat(cartHelperClass.getHarga()));

        //Update kuantitas
        holder.txt_qty.setText(new StringBuilder().append(cartHelperClass.getQty_barang()));
        updateFirebase(cartHelperClass);
    }

    private void minusCartItem(CartViewHolder holder, CartHelperClass cartHelperClass) {
        if (cartHelperClass.getQty_barang()>1) {
            cartHelperClass.setQty_barang(cartHelperClass.getQty_barang() - 1);
            cartHelperClass.setTotal_harga(cartHelperClass.getQty_barang()*Float.parseFloat(cartHelperClass.getHarga()));

            //Update kuantitas
            holder.txt_qty.setText(new StringBuilder().append(cartHelperClass.getQty_barang()));
            updateFirebase(cartHelperClass);
        }
    }

    private void updateFirebase(CartHelperClass cartHelperClass) {
        String uipn_ca;
        Intent intent = ((Activity) context).getIntent();
        uipn_ca = intent.getStringExtra("uipn");

        FirebaseDatabase.getInstance()
                .getReference("Cart")
                .child(uipn_ca)
                .child(cartHelperClass.getKey())
                .setValue(cartHelperClass).addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new UpdateCartItem()));
        Log.e("KEY",""+cartHelperClass.getKey());
        Log.e("VARIAN",""+cartHelperClass.getVarian());
    }

    @Override
    public int getItemCount() {
        return cartHelperClassList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.btn_minus)
        ImageView btn_minus;
        @BindView(R.id.btn_plus)
        ImageView btn_plus;
        @BindView(R.id.cart_delete)
        ImageView delete_cart;
        @BindView(R.id.cart_image)
        ImageView variancard_image;
        @BindView(R.id.cart_namavarian)
        TextView cart_varian;
        @BindView(R.id.cart_harga)
        TextView txt_harga_cart;
        @BindView(R.id.cart_qty_barang)
        TextView txt_qty;

        Unbinder unbinder;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
        }
    }
}