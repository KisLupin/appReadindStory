package com.t3h.appstoryreading;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ManagerSql {
    private static final String DB_NAME = "truyencuoi";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    public ManagerSql(Context context){
        this.context = context;
        copyExApp();
    }

    private void copyExApp(){
        String path =
                Environment.getDataDirectory().getPath() + "/data/"+ context.getPackageName() + "/db";
        if ( new File(path+"/"+DB_NAME).exists() ){
            return;
        }
        try {
            //lay inputsteam trong asset
            InputStream in = context.getAssets().open(DB_NAME);
            //lay duong duong dan luu vao ex app

            new File(path).mkdir();
            OutputStream out = new FileOutputStream(path+"/"+DB_NAME);
            byte[] b = new byte[1024];
            int le = in.read(b);
            while (le >-1){
                out.write(b, 0, le);
                le = in.read(b);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDB(){
        if ( sqLiteDatabase == null || sqLiteDatabase.isOpen() == false){
            String path =
                    Environment.getDataDirectory().getPath() + "/data/"+ context.getPackageName() + "/db";

            sqLiteDatabase =
                    SQLiteDatabase.openDatabase(path+"/"+DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }


    }

    public void closeDB(){
        if (sqLiteDatabase == null || sqLiteDatabase.isOpen() == false){
            return;
        }
        sqLiteDatabase.close();
        sqLiteDatabase = null;
    }

    public List<Topic> getAllTopic(){
        List<Topic> listTopic = new ArrayList<>();
        openDB();
        Cursor c =
                sqLiteDatabase.rawQuery("select * from categories", null);
        c.moveToFirst();
        int indexIdCat = c.getColumnIndex("id");
        int indexName = c.getColumnIndex("name");

        while (!c.isAfterLast()){
            int idCat = c.getInt(indexIdCat);
            String name = c.getString(indexName);
            //chuyen xuong row tiep theo
            listTopic.add(new Topic(idCat, name));
            c.moveToNext();
        }
        closeDB();

        return listTopic;

    }

    public List<ListStory> getAllNameStory(int idCat){
        List<ListStory> listStories = new ArrayList<>();
        openDB();
        Cursor c =
                sqLiteDatabase.rawQuery("select * from stories where cat_id = " + idCat, null);
        c.moveToFirst();
        int indexName = c.getColumnIndex("name");
        int indexId = c.getColumnIndex("id");
        while (!c.isAfterLast()){
            listStories.add(new ListStory(c.getInt(indexId),c.getString(indexName)));
            c.moveToNext();
        }
        closeDB();
        return listStories;
    }

    public String getContentStory(int idContent){
        openDB();
        Cursor c =
                sqLiteDatabase.rawQuery("select content from stories where id = " + idContent, null);
        c.moveToFirst();
        int indexContent = c.getColumnIndex("content");

        closeDB();
        return c.getString(indexContent);
    }
}
