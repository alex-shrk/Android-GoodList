package ru.ssau.sanya.goodslist.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.ssau.sanya.goodslist.Database.GoodDBHelper;
import ru.ssau.sanya.goodslist.Database.Model.Good;
import ru.ssau.sanya.goodslist.GoodAboutActivity;
import ru.ssau.sanya.goodslist.R;

public class GoodListAdapter extends RecyclerView.Adapter<GoodViewHolder> {
    private List<Good> goodList;
    private Context context;
    private RecyclerView recyclerView;

    public GoodListAdapter(List<Good> goodList,Context context,RecyclerView recyclerView) {
        this.goodList = goodList;
        this.context = context;
        this.recyclerView = recyclerView;
    }


    @Override
    public GoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.good_list_layout,parent,false);
        return new GoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GoodViewHolder holder, final int position) {
        final Good good = goodList.get(position);

        holder.name.setText(good.getName());
        holder.count.setText(good.getCount());
        holder.price.setText(good.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.choose_action);
                builder.setMessage(R.string.update_or_delete);

                builder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callUpdateActivity(good.getId());
                    }
                });
                builder.setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GoodDBHelper dbHelper = new GoodDBHelper(context);
                        dbHelper.deleteGood(good.getId(),context);
                        goodList.remove(position);
                        recyclerView.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,goodList.size());
                        //notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();

            }
        });

    }

    private void callUpdateActivity(Long id) {
        Intent intent = new Intent(context, GoodAboutActivity.class);
        intent.putExtra("GOOD_ID",id);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return goodList.size();
    }


}
