package com.footprint.hackduke2018.myfootprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Iterator;

public class Productpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        setTitle(value);



        View cPP = findViewById(R.id.contentProductPage);

        TextView crueltyLabel = (TextView) cPP.findViewById(R.id.cruelty);

        TextView donorLabel = (TextView) cPP.findViewById(R.id.donors);

        TextView commitLabel = (TextView) cPP.findViewById(R.id.commit);


        TextView emissionsLabel = (TextView) cPP.findViewById(R.id.emissions);

        if (value.equals("Coca-Cola")) {
            commitLabel.setText("Commits to reducing agricultural emissions.");
            emissionsLabel.setText("Currently emits 523 thousand metric tons of carbon dioxide.");
        }
        else {
            commitLabel.setText("Does not commit to reducing agricultural emissions.");
            emissionsLabel.setText("No data on emissions.");
        }


//        String crueltyCo = "";
//        try (BufferedReader reader = new BufferedReader(
//                new InputStreamReader(getAssets().open("CrueltyCompanies.json")))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                crueltyCo += line + '\n';
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            JSONObject jObj = new JSONObject(crueltyCo.toString());
//
//
//            JSONArray companies = (JSONArray) jObj.getJSONArray("companies");
//
//
//            for (int i = 0; i < companies.length(); i++)
//            {
//                if (companies.getJSONObject(i).toString().equals(value)) {
//                    crueltyLabel.setText("Animal Cruelty: True");
//                } else {
//                    crueltyLabel.setText("Animal Cruelty: False");
//                }
//            }
//
//        } catch (Exception e ) {
//            e.printStackTrace();
//        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getAssets().open("CrueltyCompanies.json")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(value)) {
                    crueltyLabel.setText("Tests on animals.");
                    break;
                } else {
                    crueltyLabel.setText("Animal cruelty free.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getAssets().open("donors.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(value)) {
                    donorLabel.setText("Donates to non-profits.");
                    break;
                } else {
                    donorLabel.setText("Does not donate.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new
            Intent(getApplicationContext(),Historypage.class));
            }
        });

    }

}
