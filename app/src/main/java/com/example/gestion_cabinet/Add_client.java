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

public class Add_client extends AppCompatActivity {
    EditText edit3, edit4, edit5;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        edit3 = findViewById(R.id.titre1);
        edit4 = findViewById(R.id.description);
        edit5 = findViewById(R.id.date_ajout1);
        button = findViewById(R.id.valider1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titre = edit3.getText().toString();
                String description = edit4.getText().toString();
                String date_ajout = edit5.getText().toString();
                Client client = new Client(titre, description, date_ajout);
                AddClient(client);
            }
        });
    }

    private void AddClient(Client client) {
        String url = "http://192.168.1.7/cabinet/AddClient.php?titre=" + client.getTitre() + "&description=" + client.getDescription() + "&date_ajout=" + client.getDate_ajout();
        Ion.with(Add_client.this)
                .load(url)//url eli testineh fel url eli fih el name wel phone
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e!=null)
                        {
                            Toast.makeText(Add_client.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            boolean isInserted= result.get("reponse").getAsBoolean();
                            if(isInserted)
                            {
                                Toast.makeText(Add_client.this, "success...!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Add_client.this,List_client_medecin.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Add_client.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }





                    }

                });
    }
}

