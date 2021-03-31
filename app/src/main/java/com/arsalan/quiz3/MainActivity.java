package com.arsalan.quiz3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText EdttxtName, EdttxtAge,EdttxtColor, EdttxtGender ;
    Button BtnSaveData, BtnShowData;
    DataBaseHelper DbHelper;
    String Name ,Color, Gender;
    int Age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper = new DataBaseHelper(this);
        EdttxtName = findViewById(R.id.edttxt_name);
        EdttxtAge = findViewById(R.id.edttxt_age);
        EdttxtColor = findViewById(R.id.edttxt_color);
        EdttxtGender =findViewById(R.id.edttxt_gender);
        BtnSaveData = findViewById(R.id.btn_savedata);
        BtnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = EdttxtName.getText().toString().trim();
                Age = Integer.parseInt(EdttxtAge.getText().toString().trim());
                Color = EdttxtColor.getText().toString().trim();
                Gender = EdttxtGender.getText().toString().trim();
                long id = DbHelper.insertData(Name, Age, Color, Gender);
                Log.i("LOG", "id = " + id);
                if (id == -1) {
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Record inserted successfully",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        BtnShowData = findViewById(R.id.btn_showalldata);
        BtnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllData();
            }
        });
    }
    public void showAllData() {
        StringBuilder MessageString = new StringBuilder();
        Cursor cursor = DbHelper.getAllData();
        while (cursor.moveToNext()) {
            MessageString.append(cursor.getString(0));
            MessageString.append(" ");
            MessageString.append(cursor.getString(1));
            MessageString.append("\n");
            MessageString.append(cursor.getString(2));
            MessageString.append(" ");
            MessageString.append(cursor.getString(3));
            MessageString.append("\n");
        }
        cursor.close();
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Database Data");
        dialog.setMessage(MessageString);
        dialog.show();
    }
}
