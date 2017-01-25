package com.studience.rahul.contactme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public char i='0';
    public Button bt;
    public TextView viewToRemove,t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void init(View view){
        bt=(Button) findViewById(R.id.button1);
        Context context= MainActivity.this;
        Class destination=addContact.class;
        final Intent intent=new Intent(context,destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent,1);
    }

    public void deleteContact(View view){
        ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==1){
            if(resultCode == Activity.RESULT_OK){

                String returnValue = data.getStringExtra("mylist");
                t = new TextView(MainActivity.this);
                LinearLayout l1= (LinearLayout) findViewById(R.id.contacts_list);
                l1.setOrientation(LinearLayout.VERTICAL);

                returnValue=returnValue.replace("[","");
                returnValue=returnValue.replace("]","\n");
                returnValue=returnValue.replace(",","\n");
                Context context= MainActivity.this;
                Class destination=viewContact.class;
                final Intent intent1=new Intent(context,destination);

                t.setId(i++);
                t.setTextSize(20);
                t.setTextColor(Color.BLACK);
                t.setFocusable(false);
                t.setClickable(true);
                t.setText(returnValue);
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast toast = Toast.makeText(getApplicationContext(), t.getText().toString(), Toast.LENGTH_LONG);
//                        toast.show();

                        TextView text=(TextView) findViewById(view.getId());
                        String[] input=text.getText().toString().split("\n");
                        String Name=input[0];
                        String MobilePhone=input[1];
                        intent1.putExtra("Name",Name);
                        intent1.putExtra("MobilePhone",MobilePhone);
                        intent1.putExtra("userID",Integer.toString(view.getId()));
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent1,2);
                    }
                });
                l1.addView(t);

            }
        }
        if(requestCode==2){
            if(resultCode==Activity.RESULT_OK){
                final String backPressed = data.getStringExtra("backPressed");
//                Toast toast = Toast.makeText(getApplicationContext(), backPressed, Toast.LENGTH_LONG);
//                toast.show();
                if(backPressed.charAt(0)=='1') {
                    String toDelete=backPressed.substring(1);
                    viewToRemove=(TextView) findViewById(Integer.parseInt(toDelete));
                    deleteContact(viewToRemove);
                }
        }
        }

    }

}
