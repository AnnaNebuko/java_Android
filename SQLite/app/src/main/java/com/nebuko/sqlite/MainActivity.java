package com.nebuko.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nebuko.sqlite.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    Button _btnInsert, _btnDelete, _btnUpdate;
    EditText _txtId, _txtSurname, _txtName;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnInsert=(Button)findViewById(R.id.btnInsert);
        _btnDelete=(Button)findViewById(R.id.btnDlt);
        _btnUpdate=(Button)findViewById(R.id.btnUpdate);

        _txtId=(EditText)findViewById(R.id.txtId);
        _txtSurname=(EditText)findViewById(R.id.txtSurname);
        _txtName=(EditText)findViewById(R.id.txtName);
        openHelper=new DatabaseHelper(this);

        _btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String surname =_txtSurname.getText().toString();
                String name =_txtName.getText().toString();

                db=openHelper.getWritableDatabase();
                insertData(surname, name);
                Toast.makeText(getApplicationContext(), "INSERTED SUCCESSFULLY", Toast.LENGTH_LONG).show();
            }
        });
        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String id = _txtId.getText().toString();
                deleteData(id);
                Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_LONG).show();
            }
        });
        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String surname = _txtSurname.getText().toString();
                String name =_txtName.getText().toString();
                db=openHelper.getWritableDatabase();
                updateData(surname, name);
                Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean updateData(String surname, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLS_2, surname);
        contentValues.put(DatabaseHelper.COLS_3, name);
        String id = _txtId.getText().toString();
        return db.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.COLS_1 + "=?", new String[]{id})>0;
    }

    public boolean deleteData(String id){
        return db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLS_1 + "=?", new String[]{id})>0;
    }

    private void insertData(String surname, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLS_2, surname);
        contentValues.put(DatabaseHelper.COLS_3, name);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }


}