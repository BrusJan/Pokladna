package com.janbrus.damian.pokladna.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.janbrus.damian.pokladna.Models.Product;
import com.janbrus.damian.pokladna.Models.ProductList;
import com.janbrus.damian.pokladna.R;

/**
 * Created by jbrus on 30.1.2018.
 */
public class ProductsSummaryAdapter extends RecyclerView.Adapter<ProductsSummaryAdapter.ViewHolder> {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout mTextView;
        public ViewHolder(LinearLayout v) {
            super(v);
            mTextView = v;
        }
    }

    private ProductList productsSummary;

    public ProductsSummaryAdapter(ProductList productsSummary) {
        super();
        this.productsSummary = productsSummary;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductsSummaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_summary, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Product p = productsSummary.get(position);
        int count = productsSummary.getProductCount(p);
        ((TextView)holder.mTextView.getChildAt(0)).setText(p.getName());
        ((TextView)holder.mTextView.getChildAt(1)).setText(count+"x");
        ((TextView)holder.mTextView.getChildAt(2)).setText(Double.toString(p.getPrice()*count)+" Kč");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return productsSummary == null ? 0 : productsSummary.length();
    }

    public void swapData(ProductList newProductsSummary) {
        this.productsSummary = newProductsSummary;
        notifyDataSetChanged();
    }
}
