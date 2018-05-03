package ru.ssau.sanya.goodslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import ru.ssau.sanya.goodslist.Database.GoodDBHelper;
import ru.ssau.sanya.goodslist.Database.Model.Good;

public class GoodAboutActivity extends AppCompatActivity {
    private TextView nameView;
    private TextView descrView;
    private TextView priceView;
    private TextView countView;

    private GoodDBHelper dbHelper;

    private Long idGood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_about);

        Toolbar toolbar = findViewById(R.id.toolbar_about_good);
        setSupportActionBar(toolbar);

        assert getSupportActionBar()!=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameView = findViewById(R.id.about_name);
        descrView = findViewById(R.id.about_description);
        priceView = findViewById(R.id.about_price);
        countView = findViewById(R.id.about_count);
        dbHelper = new GoodDBHelper(this);

        //restore state of activity
        if (savedInstanceState!=null){
            nameView.setText(savedInstanceState.getCharSequence("nameValue"));
            descrView.setText(savedInstanceState.getCharSequence("descrValue"));
            priceView.setText(savedInstanceState.getCharSequence("priceValue"));
            countView.setText(savedInstanceState.getCharSequence("countValue"));
        }

        try {
            idGood = getIntent().getLongExtra("GOOD_ID", 1);
            Good good = dbHelper.getGoodById(idGood);
            nameView.setText(good.getName());
            descrView.setText(good.getDescription());
            priceView.setText(good.getPrice());
            countView.setText(good.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("nameValue",nameView.getText().toString());
        outState.putCharSequence("descrValue",descrView.getText().toString());
        outState.putCharSequence("priceValue",priceView.getText().toString());
        outState.putCharSequence("countValue",countView.getText().toString());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
