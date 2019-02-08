package com.shubham.srikanth.kishan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PrinterId;
import android.text.StaticLayout;
import android.util.Log;

import com.shubham.srikanth.kishan.DataModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="DATABASE_NAME";
    private static final int DATABASE_VERSION=6;
    private static final String _ID="_id";
    private static final String FROM_STATION_CODE="from_station_code";
    private static final String FROM_STATION_NAME="from_station_name";
    private static final String TO_STATION_CODE="to_station_code";
    private static final String TO_STATION_NAME="to_station_name";
    private static final String RECENT_SEARCH="recent_search";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+RECENT_SEARCH+"("+_ID+" integer primary key autoincrement,"+FROM_STATION_CODE
        +" text  not null,"+FROM_STATION_NAME+" text  not null,"+TO_STATION_CODE+" text  not null,"
                +TO_STATION_NAME+" text  not null );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+RECENT_SEARCH);
        onCreate(sqLiteDatabase);
    }
    public void addDataToRecentSearch(DataModel dataModel){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FROM_STATION_CODE, dataModel.getFromStationCode());
        contentValues.put(FROM_STATION_NAME, dataModel.getFromStationName());
        contentValues.put(TO_STATION_CODE, dataModel.getToStationCode());
        contentValues.put(TO_STATION_NAME, dataModel.getToStationName());

        sqLiteDatabase.insert(RECENT_SEARCH,null ,contentValues );
        sqLiteDatabase.close();
    }
    public ArrayList<DataModel> returnDataFromRecentSearches(){
        ArrayList<DataModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor rowCursor=sqLiteDatabase.rawQuery("select * from "+RECENT_SEARCH+";",null );

        if(rowCursor.moveToFirst()){
            do{
                DataModel dataModel=new DataModel();
                String fromStationCode=rowCursor.getString(1);
                String fromStationName=rowCursor.getString(2);
                String toStationCode=rowCursor.getString(3);
                String toStationName=rowCursor.getString(4);

                dataModel.setFromStationCode(fromStationCode);
                dataModel.setFromStationName(fromStationName);
                dataModel.setToStationCode(toStationCode);
                dataModel.setToStationName(toStationName);

                list.add(dataModel);
            }while (rowCursor.moveToNext());
        }
        rowCursor.close();
        return list;
    }
    public void deleteRecentSearches(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+RECENT_SEARCH+";");
    }
}
