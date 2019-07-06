package com.example.gestion_cabinet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class List_rendez_sec extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rendez_sec);
        listView = findViewById(R.id.list2);
        Get_Rendezvous();

    }

    private void Get_Rendezvous() {
        String url = "http://192.168.1.7/cabinet/Liste_rendezvous.php";
        Ion.with(this)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            Toast.makeText(List_rendez_sec.this, "ERROR " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Gson gson=new Gson();
                            Rendez_vous[] contactArray=gson.fromJson(result.toString(),Rendez_vous[].class);
                            ArrayList<Rendez_vous> client=new ArrayList<>();
                            for(Rendez_vous row:contactArray)
                            {
                                client.add(row);
                            }
                            Adapter_sec_rendezvous adapter_sec_rendezvous=new Adapter_sec_rendezvous(List_rendez_sec.this,client);
                            listView.setAdapter(adapter_sec_rendezvous);
                        }
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_rendez_vous,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.sign_out)
        {
            Intent intent = new Intent(List_rendez_sec.this, Genre.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

}

