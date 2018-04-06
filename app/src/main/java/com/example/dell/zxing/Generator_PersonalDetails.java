package com.example.dell.zxing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generator_PersonalDetails extends AppCompatActivity {
    String dataname,dataphone,dataemail,datacity,datacompany,datawebsite;
    EditText namepd,phonepd,emailpd,citypd,complanypd,websitepd;
    Pattern web= Patterns.EMAIL_ADDRESS;
    Pattern net= Patterns.WEB_URL;
    String alldata;
    Matcher m,m2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator__personal_details);
        namepd=(EditText)findViewById(R.id.yourname);
        getSupportActionBar().hide();
        phonepd=(EditText)findViewById(R.id.contact);
        emailpd=(EditText)findViewById(R.id.email);
        citypd=(EditText)findViewById(R.id.city);
        complanypd=(EditText)findViewById(R.id.company);
        websitepd=(EditText)findViewById(R.id.website);
    }
    //Generationg QR Code
    public void genpersonaldetails(View view) {
        if(valid())
        {
            alldata="Name :"+dataname+"\n"+"Phone :"+dataphone+"\n"+"Email :"+dataemail+"\n"+"City :"+datacity+"\n"
            +"Company :"+datacompany+"\n"+"Webste :"+datawebsite;
            Intent i=new Intent(this,GeneratedDetails.class);
            i.putExtra("value",alldata);
            startActivity(i);
        }
    }
    //Validation
    private boolean valid() {
        details();
        m=web.matcher(dataemail);
        m2=net.matcher(datawebsite);
        if(dataname.isEmpty())
        {
            namepd.requestFocus();
            namepd.setError("Please Enter Name");
            return false;
        }
        else if(dataphone.isEmpty())
        {
            phonepd.requestFocus();
            phonepd.setError("Please Enter Phonenumber");
            return false;
        }
        else if(!(m.matches()))
        {
            emailpd.requestFocus();
            emailpd.setError("please Enter Valid Mail !");
            return false;
        }
        else if(datacity.isEmpty()||datacity.length()<4)
        {
            citypd.requestFocus();
            citypd.setError("Enter Valid City");
            return false;
        }
        else if(datacompany.isEmpty()||datacompany.length()<4)
        {
            complanypd.requestFocus();
            complanypd.setError("Please Enter Valid Complany !");
            return false;
        }
        else if (!(m2.matches()))
        {
            websitepd.requestFocus();
            websitepd.setError("Please Enter Valid Website");
            return false;
        }
        return true;
    }
    //Retriving
    private void details() {
        dataname=namepd.getText().toString().trim();
        dataphone=phonepd.getText().toString().trim();
        dataemail=emailpd.getText().toString().trim();
        datacity=citypd.getText().toString().trim();
        datacompany=complanypd.getText().toString().trim();
        datawebsite=websitepd.getText().toString().trim();
    }
}
