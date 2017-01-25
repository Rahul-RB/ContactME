package com.studience.rahul.contactme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addContact extends AppCompatActivity {

    private TextView testText;
    public EditText mName,mMP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        mName=(EditText) findViewById(R.id.Name);
        mMP=(EditText) findViewById(R.id.MobileNumber);
        testText=(TextView) findViewById(R.id.testText);
    }


    public void saveContact(View view) {
        ArrayList <String> list= new ArrayList<String>();
        list.add(mName.getText().toString());
        list.add(mMP.getText().toString());

        String list1=list.toString();
        testText.setText(list1);
        Intent resultIntent = new Intent(this,MainActivity.class);
        resultIntent.putExtra("mylist", list1);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }


}
