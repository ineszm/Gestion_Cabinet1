package com.example.gestion_cabinet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class Fiche_rendez_vous extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_rendez_vous);
        listView = findViewById(R.id.list3);
        Intent intent = getIntent();
        String ch = intent.getStringExtra("email");
        GetClient(ch);
    }

    private void GetClient(String ch1) {
        String url = "http://192.168.1.7/cabinet/GetFiche.php?email=" + ch1;
        Ion.with(this)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            Toast.makeText(Fiche_rendez_vous.this, "ERROR " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Gson gson=new Gson();
                          Rendez_vous[] contactArray=gson.fromJson(result.toString(),Rendez_vous[].class);
                            ArrayList<Rendez_vous> client=new ArrayList<>();
                            for(Rendez_vous row:contactArray)
                            {
                                client.add(row);
                            }
                            Adapter_fiche adapter_fiche=new Adapter_fiche(Fiche_rendez_vous.this,client);
                            listView.setAdapter(adapter_fiche);

                          }
                    }
                });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.sign_out)
        {
            Intent intent = new Intent(Fiche_rendez_vous.this, Genre.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}

