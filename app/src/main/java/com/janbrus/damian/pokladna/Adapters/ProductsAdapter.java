package com.janbrus.damian.pokladna.Adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.janbrus.damian.pokladna.Models.Product;
import com.janbrus.damian.pokladna.R;
/**
 * Created by Honza on 20.07.2017.
 */

public class ProductsAdapter extends ArrayAdapter<Product> {

    Context mContext;
    int resourceId;
    ArrayList<Product> data = new ArrayList<Product>();

    public ProductsAdapter(Context context, int layoutResourceId, ArrayList<Product> data)
    {
        super(context, layoutResourceId, data);
        this.mContext = context;
        this.resourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View itemView = convertView;
        ViewHolder holder = null;

        if (itemView == null)
        {
            final LayoutInflater layoutInflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            //holder.imgItem = (ImageView) itemView.findViewById(R.id.imgItem);
            holder.txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            holder.txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            itemView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) itemView.getTag();
        }

        Product p = getItem(position);
        //holder.imgItem.setImageDrawable(item.getImage());
        holder.txtItem.setText(p.getName());
        holder.txtPrice.setText(p.getPrice().toString()+" Kč");

        return itemView;
    }

    static class ViewHolder
    {
        //ImageView imgItem;
        TextView txtItem;
        TextView txtPrice;
    }
}
