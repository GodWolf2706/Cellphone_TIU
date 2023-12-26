package com.example.cellphonetiu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cellphonetiu.Model.Product;
import com.example.cellphonetiu.ProductDetailActivity;
import com.example.cellphonetiu.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> ListProduct;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Product> listProduct){
        this.ListProduct = listProduct;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent,false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        Product product = ListProduct.get(position);
        if (product == null){
            return;
        }

        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(product.getImage() + ".jpg");
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        Drawable drawable = Drawable.createFromStream(inputStream, null);
        holder.productImg.setImageDrawable(drawable);

        holder.productName.setText(product.getProductName());
        holder.Price.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return ListProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView productImg;
        private TextView productName, Price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.imgProduct);
            productName = itemView.findViewById(R.id.txtproductName);
            Price = itemView.findViewById(R.id.txtPrice);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_name", productName.getText());
            context.startActivities(new Intent[]{intent});
        }
    }
}
