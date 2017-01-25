package com.studience.rahul.contactme;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class viewContact extends AppCompatActivity {

    String userID = new String();
    public TextView Name, MobilePhone;
    public ImageView addImage;
    public Button Delete, imagePicker;
    private final int SELECT_PHOTO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        Name = (TextView) findViewById(R.id.Name);
        MobilePhone = (TextView) findViewById(R.id.MobileNumber);
        Delete = (Button) findViewById(R.id.Delete);

        Bundle extras = getIntent().getExtras();
        viewUserContact(extras);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        addImage.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
        }


    public void viewUserContact(View view){
        Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);
        resultIntent.putExtra("backPressed","0"+userID);
        setResult(Activity.RESULT_OK,resultIntent);
        finish();
    }

    public void deleteContact(View view){
        new AlertDialog.Builder(this)
            .setTitle("Delete Contact?")
            .setMessage("Do you want to delete the contact?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);
                    resultIntent.putExtra("backPressed","1"+userID);
                    setResult(Activity.RESULT_OK,resultIntent);
                    finish();
                }})
            .setNegativeButton(android.R.string.no, null).show();
//        Toast toast = Toast.makeText(getApplicationContext(), userID, Toast.LENGTH_LONG);
//        toast.show();
    }



    public void viewUserContact(Bundle extras) {
        userID = extras.getString("userID");
        String userName = extras.getString("Name");
        String userMobilePhone = extras.getString("MobilePhone");
        Name.setText("Name:\n" + userName);
        MobilePhone.setText("Mobile Phone:\n" + userMobilePhone);
        }
    }
