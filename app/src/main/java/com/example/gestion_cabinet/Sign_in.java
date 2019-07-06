package com.example.gestion_cabinet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Sign_in extends AppCompatActivity {
    EditText edit1, edit2, edit3, edit4, edit5, edit6, edit7;
    Button button;
    Spinner spinner;
   String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        String[] arraySpinner = new String[]{
                "médecin", "secrétaire", "client"
        };
        button = findViewById(R.id.sign);
        edit1 = findViewById(R.id.nom);
        edit2 = findViewById(R.id.prenom);
        edit3 = findViewById(R.id.email);
        edit4 = findViewById(R.id.adresse);
        edit5 = findViewById(R.id.tel);
        edit6 = findViewById(R.id.password);
        edit7 = findViewById(R.id.cpassword);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snom = edit1.getText().toString();
                String sprenom = edit2.getText().toString();
                String semail = edit3.getText().toString();
                String sadresse = edit4.getText().toString();
                String stel = edit5.getText().toString();
                String spass = edit6.getText().toString();
                String scpass = edit7.getText().toString();

                if (snom.length() != 0 && sprenom.length() != 0 && semail.length() != 0 && sadresse.length() != 0 && stel.length() != 0) {
                    if (spass.equalsIgnoreCase(scpass)) {

                         type = spinner.getSelectedItem().toString();
                        User user = new User(snom, sprenom, semail, sadresse, stel, spass, type);
                        UserRegister(user);

                    } else {
                        Toast.makeText(Sign_in.this, "MOT DE PASSE INCORRECTE", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(Sign_in.this, "REMPLIR LES CHAMPS", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void UserRegister(User user) {//consommation de web service de AddUser
        String url = "http://192.168.1.7/cabinet/AddUser.php?nom=" + user.getNom() + "&prenom=" + user.getPrenom() + "&email=" + user.getEmail() + "&adresse=" + user.getAdresse() + "&tel=" + user.getTel() + "&=password=" + user.getPassword() + "&type=" + user.getType();
        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e!=null)
                        {
                            Toast.makeText(Sign_in.this, "ERROR "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            boolean isregisted=result.get("reponse").getAsBoolean();
                            if(isregisted) {
                                Toast.makeText(Sign_in.this, "succees.....!", Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(Sign_in.this,Login.class);
                                intent.putExtra("ch",type);
                                startActivity(intent);
                            }
                            else
                            { Toast.makeText(Sign_in.this, "Erreur", Toast.LENGTH_SHORT).show();

                            }

                        }

                    }
                });

    }
}
