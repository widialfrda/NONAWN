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

public class VarianAdapter extends RecyclerView.Adapter<VarianAdapter.VarianViewHolder> {

    private Context context;
    private List<MenuHelperClass>menuHelperClassList;

    public VarianAdapter(Context context, List<MenuHelperClass> menuHelperClassList) {
        this.context = context;
        this.menuHelperClassList = menuHelperClassList;
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
        holder.varian_harga.setText(new StringBuilder("Rp. ").append(menuHelperClassList.get(position).getHarga()));
        holder.varian_nama.setText(new StringBuilder().append(menuHelperClassList.get(position).getVarian()));

    }

    @Override
    public int getItemCount() {
        return menuHelperClassList.size();
    }

    public class VarianViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.variancard_image)
        ImageView cardvarian_image;
        @BindView(R.id.varian_name)
        TextView varian_nama;
        @BindView(R.id.varian_price)
        TextView varian_harga;

        private Unbinder unbinder;

        public VarianViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }

}
