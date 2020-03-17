package edu.pdx.cs401j.airlineapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import edu.pdx.cs410J.AirportNames;

public class MainActivity extends AppCompatActivity {
Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addflight=(Button)findViewById(R.id.AddNewFlight);
        addflight.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent secondpage=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(secondpage);
            }
        });

        Button search=(Button)findViewById(R.id.SearchFlight);
        search.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                EditText airlinename=(EditText)findViewById(R.id.AirlineNameField);
                String aname=airlinename.getText().toString();
                EditText src=(EditText)findViewById(R.id.SourceField);
                String source=src.getText().toString();
                EditText dest=(EditText)findViewById(R.id.DestinationField);
                String destination=dest.getText().toString();


                try {


                    if (source.isEmpty() && destination.isEmpty()) {
                        if (aname.isEmpty()) {
                            throw new Exception("Airline Name not provided");
                        } else {
                            readFile(aname, null, null,Main3Activity.class);
                        }
                    } else if (!source.isEmpty() && !destination.isEmpty()) {
                        if(AirportNames.getName(source)==null){
                            throw new Exception("Source Code not valid!");
                        }
                        if(AirportNames.getName(destination)==null){
                            throw new Exception("Destination Code not valid!");
                        }
                        readFile(aname, source, destination,Main3Activity.class);
                    }else if(!source.isEmpty() && destination.isEmpty()) {
                        if(AirportNames.getName(source)==null){
                            throw new Exception("Source Code not valid!");
                        }
                        if(AirportNames.getName(destination)==null){
                            throw new Exception("Destination Code not valid!");
                        }
                        throw new Exception("Source Code is given but destination code is not given.");
                    }
                    else if(source.isEmpty() && !destination.isEmpty()) {
                        if(AirportNames.getName(source)==null){
                            throw new Exception("Source Code not valid!");
                        }
                        if(AirportNames.getName(destination)==null){
                            throw new Exception("Destination Code not valid!");
                        }
                        throw new Exception("Source Code is not given but destination code is  given.");
                    }
                }catch (Exception e){
                    createAlert(e.getMessage());
                }
            }
        });

        Button pp=(Button)findViewById(R.id.PP);
        pp.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                EditText airlinename=(EditText)findViewById(R.id.AirlineNameField);
                String aname=airlinename.getText().toString();
                EditText src=(EditText)findViewById(R.id.SourceField);
                String source=src.getText().toString();
                EditText dest=(EditText)findViewById(R.id.DestinationField);
                String destination=dest.getText().toString();
                try {
                    if (source.isEmpty() && destination.isEmpty()) {
                        if (aname.isEmpty()) {
                            throw new Exception("Airline Name not provided");
                        } else {
                            readFile(aname, null, null,SearchResult.class);
                        }
                    } else if (!source.isEmpty() && !destination.isEmpty()) {
                        readFile(aname, source, destination,SearchResult.class);
                    }else if(!source.isEmpty() && destination.isEmpty()) {
                        throw new Exception("Source Code is given but destination code is not given.");
                    }
                    else if(source.isEmpty() && !destination.isEmpty()) {
                        throw new Exception("Source Code is not given but destination code is given.");
                    }
                }catch (Exception e){
                    createAlert(e.getMessage());
                }
            }
        });
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

    public void createAlert(String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void readFile(String aname,String src,String dest,Class classname) throws Exception {
        File file = new File(context.getFilesDir(), aname+".txt");
        if(file.exists()) {
            FileInputStream fis = context.openFileInput(aname + ".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            ArrayList<String> result=new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            Intent thirdpage=new Intent(getApplicationContext(),classname);
            thirdpage.putExtra("airline",result);
            thirdpage.putExtra("src",src);
            thirdpage.putExtra("dest",dest);
            startActivity(thirdpage);

        }else{
            createAlert("Airline not found in App. Please check Airline Name entered");
        }

    }
}
