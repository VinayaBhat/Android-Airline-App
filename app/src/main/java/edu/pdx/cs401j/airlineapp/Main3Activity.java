package edu.pdx.cs401j.airlineapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class Main3Activity extends AppCompatActivity {
Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ArrayList<String> finalresult=new ArrayList<>();
        Intent intent=getIntent();
        ArrayList<String> result=intent.getStringArrayListExtra("airline");
        String source=intent.getStringExtra("src");
        String destination=intent.getStringExtra("dest");

        Button search=(Button)findViewById(R.id.PrettyPrint);
        search.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v) {
                Intent thirdpage=new Intent(getApplicationContext(),SearchResult.class);
                thirdpage.putExtra("airline",getIntent().getStringArrayListExtra("airline"));
                thirdpage.putExtra("src",getIntent().getStringExtra("src"));
                thirdpage.putExtra("dest",getIntent().getStringExtra("dest"));
                startActivity(thirdpage);
            }
        });
        ArrayList<Flight> result2=new ArrayList<>();
        Airline a1 = null;
        ListView listView=(ListView)findViewById(R.id.ListView);
        for(String str:result){
            String[] temp=str.split(";");
            a1=new Airline(temp[0]);
            Flight f1=new Flight(temp[1]);
            f1.setSource(temp[2]);
            String[] ddate=temp[3].split(" ");
            try {
                f1.setDeparture_time(ddate[0],ddate[1]+" "+ddate[2]);
                f1.setDestination(temp[4]);
                if(source!=null && destination!=null && source.equals(f1.getSource()) && destination.equals(f1.getDestination())) {
                    String[] adate = temp[5].split(" ");
                    f1.setArrival_time(adate[0], adate[1] + " " + adate[2]);
                    result2.add(f1);
                }else if(source==null && destination==null){
                    String[] adate = temp[5].split(" ");
                    f1.setArrival_time(adate[0], adate[1] + " " + adate[2]);
                    result2.add(f1);
                }
            } catch (Exception e) {
                createAlert(e.getMessage());
            }

        }

        Collections.sort(result2);
        try {
            for (Flight f : result2) {
                finalresult.add(f.toString());
            }
        }catch (Exception e){
            createAlert(e.getMessage());
        }
        if(finalresult.size()==0 && source==null && destination==null){
            finalresult.add("No flights with Airline "+a1.getName());
        }else if(finalresult.size()==0 && source!=null && destination!=null){
            finalresult.add("No flights with Source "+source+" and destination "+destination+" from Airline "+a1.getName());
        }


        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,finalresult);
        listView.setAdapter(arrayAdapter);

    }

    public void createAlert(String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.help_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.readme){
            Intent thirdpage=new Intent(getApplicationContext(),README.class);
            startActivity(thirdpage);
        }
        return true;
    }
}
