package com.example.spikee.iworld;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spikee.iworld.Product;

import com.example.spikee.iworld.R;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder> {

    private List<Product> listProducts;

    public ProductRecyclerAdapter(List<Product> listProducts) {
        this.listProducts = listProducts;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_recycler, parent, false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.ProductViewHolder holder, int position) {
        holder.textViewName.setText(listProducts.get(position).getName());
        holder.textViewModel.setText(listProducts.get(position).getModel());
        holder.textViewPrice.setText(listProducts.get(position).getPrice());
        holder.textViewDesc.setText(listProducts.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        Log.v(ProductRecyclerAdapter.class.getSimpleName(),""+listProducts.size());
        return listProducts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewModel;
        public AppCompatTextView textViewPrice;
        public AppCompatTextView textViewDesc;

        public ProductViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewModel = (AppCompatTextView) view.findViewById(R.id.textViewModel);
            textViewPrice = (AppCompatTextView) view.findViewById(R.id.textViewPrice);
            textViewDesc = (AppCompatTextView) view.findViewById(R.id.textViewDesc);
        }
    }
}