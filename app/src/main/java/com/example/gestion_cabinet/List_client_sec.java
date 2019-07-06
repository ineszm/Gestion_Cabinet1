package com.example.gestion_cabinet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class List_client_sec extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client_sec);
        listView = findViewById(R.id.list1);
        GetClient();
    }

    private void GetClient() {
        String url = "http://192.168.1.7/cabinet/Liste_clientsec.php";
        Ion.with(this)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            Toast.makeText(List_client_sec.this, "ERROR " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Gson gson=new Gson();
                            Client[] contactArray=gson.fromJson(result.toString(),Client[].class);
                            ArrayList<Client> client=new ArrayList<>();
                            for(Client row:contactArray)
                            {
                                client.add(row);
                            }
                            Adapter_sec_client adapter_sec_client=new Adapter_sec_client(List_client_sec.this,client);
                          listView.setAdapter(adapter_sec_client);

                        }
                    }
                });
    }
}

