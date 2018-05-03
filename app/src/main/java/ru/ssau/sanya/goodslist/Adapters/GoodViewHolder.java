package ru.ssau.sanya.goodslist.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.ssau.sanya.goodslist.R;

public class GoodViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView price;
    public TextView count;

    GoodViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.good_list_name);
        price = itemView.findViewById(R.id.good_list_price);
        count = itemView.findViewById(R.id.good_list_count);

    }
}
