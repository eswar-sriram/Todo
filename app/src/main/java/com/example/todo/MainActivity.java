package com.example.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String > data;
    DataBaseHelper mydb;
    FloatingActionButton actionButton;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        data=new ArrayList<>();
        mydb=new DataBaseHelper(this);
        actionButton=findViewById(R.id.floatingActionButton);
        Cursor res=mydb.getalldata();
        while(res.moveToNext()){
            data.add(res.getString(0));
        }
        MyAdapter adapter=new MyAdapter(this,data);
        listView.setAdapter(adapter);
    }





    public void openDialog(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Enter Todo");
        final EditText editText=new EditText(MainActivity.this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setWidth(70);
        builder.setView(editText);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean res=mydb.insertdata(editText.getText().toString());
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
            }
        });
        builder.show();
    }

    public void deltodo(View view) {
        TextView c=(TextView)view;
        mydb.deletedata(c.getText().toString());
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }
}