package ru.ssau.sanya.goodslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import ru.ssau.sanya.goodslist.Database.GoodDBHelper;
import ru.ssau.sanya.goodslist.Database.Model.Good;

public class GoodCreateActivity extends AppCompatActivity {
    private EditText nameEdit;
    private EditText descrEdit;
    private EditText priceEdit;
    private EditText countEdit;

    private GoodDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_create);

        //init
        nameEdit = findViewById(R.id.create_edit_name);
        descrEdit = findViewById(R.id.create_edit_description);
        priceEdit = findViewById(R.id.create_edit_price);
        countEdit = findViewById(R.id.create_edit_count);


        Toolbar toolbar = findViewById(R.id.toolbar_create_good);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void saveGood() {
        String name = nameEdit.getText().toString();
        String descr = descrEdit.getText().toString();
        String price = priceEdit.getText().toString();
        String count = countEdit.getText().toString();

        dbHelper = new GoodDBHelper(this);

        if (name.isEmpty() || descr.isEmpty() || price.isEmpty() || count.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
        } else {
            Good good = new Good(name, descr, price, count);
            if (dbHelper.createGood(good) != -1L) {
                Toast.makeText(this, R.string.good_added_successfully, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GoodCreateActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_good, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.action_create_good) {
            saveGood();
        }
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
