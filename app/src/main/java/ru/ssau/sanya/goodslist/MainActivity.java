package ru.ssau.sanya.goodslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.ssau.sanya.goodslist.Adapters.GoodListAdapter;
import ru.ssau.sanya.goodslist.Database.GoodDBHelper;

public class MainActivity extends AppCompatActivity {

    private GoodDBHelper dbHelper;
    private GoodListAdapter goodListAdapter;
    private RecyclerView goodRecycleView;
    private FloatingActionButton fab;
    private TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view for empty good list
        emptyView = findViewById(R.id.emptyTextView);
        fab = findViewById(R.id.fabCreateGood);
        goodRecycleView = findViewById(R.id.recycleViewGoods);

        goodRecycleView.setHasFixedSize(true);//for optimization
        goodRecycleView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new GoodDBHelper(this);

        goodListAdapter = new GoodListAdapter(dbHelper.getGoodList(),this,goodRecycleView);
        goodRecycleView.setAdapter(goodListAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,GoodCreateActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        setupEmptyView();
    }

    // on/off message when list is empty
    private void setupEmptyView(){
        if (goodListAdapter.getItemCount()==0){
            goodRecycleView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else{
            goodRecycleView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        goodListAdapter.notifyDataSetChanged();
    }
}
