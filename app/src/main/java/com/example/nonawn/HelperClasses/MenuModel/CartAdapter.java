package com.example.nonawn.HelperClasses.MenuModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nonawn.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartHelperClass> cartHelperClassList;

    public CartAdapter(Context context, List<CartHelperClass> cartHelperClassList) {
        this.context = context;
        this.cartHelperClassList = cartHelperClassList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.activity_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Glide.with(context)
                .load(cartHelperClassList.get(position).getImage())
                .into(holder.variancard_image);
        holder.txt_harga_cart.setText(new StringBuilder("IDR ").append(cartHelperClassList.get(position).getHarga()));
        holder.cart_varian.setText(new StringBuilder().append(cartHelperClassList.get(position).getVarian()));
        holder.txt_qty.setText(new StringBuilder().append(cartHelperClassList.get(position).getQty_barang()));

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
        @BindView(R.id.delete_cart)
        ImageView delete_cart;
        @BindView(R.id.btn_plus)
        ImageView btn_plus;
        @BindView(R.id.variancard_image)
        ImageView variancard_image;
        @BindView(R.id.cart_varian)
        TextView cart_varian;
        @BindView(R.id.txt_harga_cart)
        TextView txt_harga_cart;
        @BindView(R.id.txt_qty)
        TextView txt_qty;

        Unbinder unbinder;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
        }
    }
}
