package edu.pdx.cs401j.airlineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AddFlightPage extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Adds new flight to AirlineName.txt file
        Button addflight = (Button) findViewById(R.id.AddFlight);
        addflight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText airlinename = (EditText) findViewById(R.id.AirlineNameField);
                String aname = airlinename.getText().toString();
                EditText flightnum = (EditText) findViewById(R.id.FlightNumber);
                String fnum = flightnum.getText().toString();
                EditText source = (EditText) findViewById(R.id.Source);
                String src = source.getText().toString();
                EditText depdate = (EditText) findViewById(R.id.DepDate);
                String ddate = depdate.getText().toString();
                EditText deptime = (EditText) findViewById(R.id.DepTime);
                String dtime = deptime.getText().toString();
                EditText destination = (EditText) findViewById(R.id.Destination);
                String dest = destination.getText().toString();
                EditText arrivaldate = (EditText) findViewById(R.id.ArrivalDate);
                String adate = arrivaldate.getText().toString();
                EditText arrivaltime = (EditText) findViewById(R.id.ArrivalTime);
                String atime = arrivaltime.getText().toString();
                try {
                    Airline a1 = new Airline(aname);
                    Flight f1 = new Flight(fnum);
                    f1.setSource(src);
                    f1.setDeparture_time(ddate, dtime);
                    f1.setDestination(dest);
                    f1.setArrival_time(adate, atime);
                    if (!Flight.checkarranddepdate(f1.getDepartureString(), f1.getArrivalString())) {
                        throw new Exception("Arrival date or time is not before departure date or time");
                    }
                    writeFile(aname, f1);

                } catch (Exception e) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage(e.getMessage());
                    builder1.setCancelable(true);
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
    }

    /**
     * Writes the flight information to Airline file.
     *
     * @param aname Airline Name
     * @param f1    Flight information
     * @throws IOException
     */
    public void writeFile(String aname, Flight f1) throws IOException {
        File file = new File(context.getFilesDir(), aname + ".txt");
        if (file.exists()) {
            System.out.println("File exists " + context.getFilesDir());
            FileWriter writer = new FileWriter(file, true);
            writer.write(aname + ";" + f1.getNumber() + ";" + f1.getSource() + ";" + f1.getDepartureString() + ";" + f1.getDestination() + ";" + f1.getArrivalString() + System.getProperty("line.separator"));
            writer.close();
            Toast.makeText(this, "Flight has been added..", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("File doesnt exits ");
            file.createNewFile();
            FileWriter writer = new FileWriter(file, true);
            writer.write(aname + ";" + f1.getNumber() + ";" + f1.getSource() + ";" + f1.getDepartureString() + ";" + f1.getDestination() + ";" + f1.getArrivalString() + System.getProperty("line.separator"));
            writer.close();
            Toast.makeText(this, "Flight has been added..", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Loading the help menu
     *
     * @param menu Menu items
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_menu, menu);
        return true;
    }

    /**
     * Selecting readme option
     * @param item Menu item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.readme) {
            Intent thirdpage = new Intent(getApplicationContext(), README.class);
            startActivity(thirdpage);
        }
        return true;
    }


}
