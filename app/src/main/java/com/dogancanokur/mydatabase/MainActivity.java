package com.dogancanokur.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            SQLiteDatabase database = this.openOrCreateDatabase("MusicianDatabase", MODE_PRIVATE, null);
            //MODE_PRIVATE bu uygulamadan başka bişi ulaşamasın

            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR2(25), age INT(2))"); // buraya sql kodu yazılacak

            database.execSQL("INSERT INTO musicians (name, age) VALUES('Doğan', 26)");
            database.execSQL("INSERT INTO musicians (name, age) VALUES('Kübra', 25)");

            // cursor -- plsql de olan gibi
            Cursor cursor = database.rawQuery("SELECT * FROM musicians", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst(); // cursorda olan ilk satırı al

            while (cursor != null) { // cursor boş değil ise
                System.out.println("Name: " + cursor.getString(nameIndex));
                System.out.println("Age: " + cursor.getString(ageIndex));

                cursor.moveToNext(); // data hala varsa bir sonraki satıra geç
            }


        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
