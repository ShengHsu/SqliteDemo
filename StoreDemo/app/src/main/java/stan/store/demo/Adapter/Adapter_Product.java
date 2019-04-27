package stan.store.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;

import stan.store.demo.Model.Product;
import stan.store.demo.R;

public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.ViewHolder> {
    private Context mContext;
    private ArrayList<Product> mArrayList_Product;

    public Adapter_Product(Context context, ArrayList<Product> list) {
        this.mContext = context;
        this.mArrayList_Product = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //宣告View底家
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.mTextView_ProductName = (TextView) view.findViewById(R.id.textView_ProductName);
        holder.mTextView_ProductPrice = (TextView) view.findViewById(R.id.textView_ProductPrice);
        holder.mButton_AddCart = (Button) view.findViewById(R.id.button_AddCart);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //設定資料底家
        holder.mButton_AddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        holder.mTextView_ProductName.setText(mArrayList_Product.get(position).getName());
        holder.mTextView_ProductPrice.setText("$" + mArrayList_Product.get(position).getPrice());
    }


    @Override
    public int getItemCount() {
        return mArrayList_Product.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView_ProductName;
        public TextView mTextView_ProductPrice;
        public Button mButton_AddCart;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}