package ru.ssau.sanya.goodslist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.ssau.sanya.goodslist.Database.GoodDbSchema.GoodTable;
import ru.ssau.sanya.goodslist.Database.Model.Good;
import ru.ssau.sanya.goodslist.R;

public class GoodDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "goodsBase.db";

    public GoodDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + GoodTable.NAME + " (" +
                GoodTable.Cols.ID + " integer primary key autoincrement, " +
                GoodTable.Cols.NAME + " text not null, " +
                GoodTable.Cols.DESCR + " text, " +
                GoodTable.Cols.COUNT + " number not null, " +
                GoodTable.Cols.PRICE + " number not null" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //todo write upgrade db
        db.execSQL("DROP table if exists " + GoodTable.NAME);
        this.onCreate(db);
    }

    public Long createGood(Good good) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoodTable.Cols.NAME, good.getName());
        values.put(GoodTable.Cols.DESCR, good.getDescription());
        values.put(GoodTable.Cols.COUNT, good.getCount());
        values.put(GoodTable.Cols.PRICE, good.getPrice());
        Long result = db.insert(GoodTable.NAME, null, values);
        db.close();
        return result;
    }

    public void updateGood(long goodId, Context context, Good updGood) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + GoodTable.NAME + " SET "
                + GoodTable.Cols.NAME + " ='" + updGood.getName() + "',"
                + GoodTable.Cols.DESCR + " ='" + updGood.getDescription() + "',"
                + GoodTable.Cols.COUNT + " ='" + updGood.getCount() + "',"
                + GoodTable.Cols.PRICE + " ='" + updGood.getPrice() + "'");
        Toast.makeText(context, R.string.db_updated, Toast.LENGTH_LONG).show();
    }

    public Good getGoodById(Long id) {
        String query = "SELECT * FROM " + GoodTable.NAME + " WHERE _id=" + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Good good = new Good();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            good.setName(cursor.getString(cursor.getColumnIndex(GoodTable.Cols.NAME)));
            good.setDescription(cursor.getString(cursor.getColumnIndex(GoodTable.Cols.DESCR)));
            good.setCount(cursor.getString(cursor.getColumnIndex(GoodTable.Cols.COUNT)));
            good.setPrice(cursor.getString(cursor.getColumnIndex(GoodTable.Cols.PRICE)));

        }
        cursor.close();
        return good;
    }

    public List<Good> getGoodList() {
        String query = "SELECT * FROM " + GoodTable.NAME;

        List<Good> goodList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Good good;
        if (cursor.moveToFirst()) {
            do {
                good = new Good();
                good.setId(cursor.getLong(cursor.getColumnIndex(GoodTable.Cols.ID)));
                good.setName(cursor.getString(cursor.getColumnIndex(GoodTable.Cols.NAME)));
                good.setDescription(cursor.getString(cursor.getColumnIndex(GoodTable.Cols.DESCR)));
                good.setCount(cursor.getString(cursor.getColumnIndex(GoodTable.Cols.COUNT)));
                good.setPrice(cursor.getString(cursor.getColumnIndex(GoodTable.Cols.PRICE)));
                goodList.add(good);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return goodList;
    }

    public void deleteGood(Long id,Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + GoodTable.NAME + " WHERE _id='"+id+"'";
        db.execSQL(query);
        Toast.makeText(context, R.string.db_good_deleted, Toast.LENGTH_LONG).show();
    }


}
