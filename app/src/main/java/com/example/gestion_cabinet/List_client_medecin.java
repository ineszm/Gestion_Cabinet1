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

public class List_client_medecin extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client_medecin);
        listView = findViewById(R.id.list);
        GetAll();

    }

    private void GetAll() {
        String url = "http://192.168.1.7/cabinet/Liste_client.php";
        Ion.with(this)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            Toast.makeText(List_client_medecin.this, "ERROR " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Gson gson=new Gson();
                           Client[] contactArray=gson.fromJson(result.toString(),Client[].class);
                            ArrayList<Client> client=new ArrayList<>();
                            for(Client row:contactArray)
                            {
                                client.add(row);
                            }
                            Adapter_med_client adapter_med_client=new Adapter_med_client(List_client_medecin.this,client);
                            listView.setAdapter(adapter_med_client);
                        }
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out,menu);
        getMenuInflater().inflate(R.menu.add_client,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.sign_out)
        {
            Intent intent = new Intent(List_client_medecin.this, Genre.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.add_client)
        {
            Intent intent = new Intent(List_client_medecin.this, Add_client.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

}

