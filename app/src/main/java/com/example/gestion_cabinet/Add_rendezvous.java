package com.example.gestion_cabinet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Add_rendezvous extends AppCompatActivity {
    EditText edit3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rendezvous);

        edit3 = findViewById(R.id.date_rendez);
        button = findViewById(R.id.valider);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = edit3.getText().toString();
                Rendez_vous rendez_vous = new Rendez_vous(date);
                Add_Rende(rendez_vous);
            }
        });
    }

    private void Add_Rende(Rendez_vous rendez_vous) {
        String url = "http://192.168.1.7/cabinet/Add_rendezvous.php?date_r=" + rendez_vous.getDate_r();
        Ion.with(Add_rendezvous.this)
                .load(url)//url eli testineh fel url eli fih el name wel phone
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Toast.makeText(Add_rendezvous.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            boolean isInserted= result.get("reponse").getAsBoolean();
                            if(isInserted)
                            {
                                Toast.makeText(Add_rendezvous.this, "success...!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Add_rendezvous.this,List_rendez_sec.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Add_rendezvous.this, "error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }
}

