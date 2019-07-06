package com.example.gestion_cabinet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Login extends AppCompatActivity {
    Button button;
    EditText edit1, edit2;
    TextView textView;
Intent intent1;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.login);
        edit1 = findViewById(R.id.mail);
        edit2 = findViewById(R.id.mdp);
        textView = findViewById(R.id.sigin);
        sharedPreferences=getBaseContext().getSharedPreferences("prefs",MODE_PRIVATE);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        intent1=getIntent();
        String type=intent1.getStringExtra("ch");
        if(Isloged()){
        if (type.equals("client"))
        {
            Intent intent=new Intent(Login.this,Fiche_rendez_vous.class);
            intent.putExtra("email",edit1.getText().toString());
            startActivity(intent);
        }
        if(type.equals("médecin"))
        {
            Intent intent1=new Intent(Login.this,List_client_medecin.class);

            startActivity(intent1);
        }
        if(type.equals("secretaire"))
        {
            Intent intent2=new Intent(Login.this,Menu_sec.class);

            startActivity(intent2);
        }}
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.length() == 0 && edit2.length() == 0) {
                    Toast.makeText(Login.this, "erreur remplir les champs", Toast.LENGTH_SHORT).show();
                } else {
                    String mail = edit1.getText().toString();
                    String mdp = edit2.getText().toString();
                    User user = new User(mail, mdp);

                    log(user);
                }
            }


        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Sign_in.class);
                startActivity(intent);
            }
        });
    }

    private void log(User user) {
        String url = "http://192.168.1.7/cabinet/Login.php?email=" + user.getEmail() + "&password=" + user.getPassword();
        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Toast.makeText(Login.this, "ERROR " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {  boolean isregisted=result.get("reponse").getAsBoolean();
                            if(isregisted){
                            intent1=getIntent();
                            String type=intent1.getStringExtra("ch");
                            String id=result.get("id").getAsString();
                            if (type.equals("client"))
                            {  sharedPreferences.edit()
                                    .putBoolean("loged",true)
                                    .putString("id",id)
                                    .apply();
                                Intent intent=new Intent(Login.this,Fiche_rendez_vous.class);
                                intent.putExtra("email",edit1.getText().toString());
                                startActivity(intent);
                            }
                            if(type.equals("médecin"))
                            {  sharedPreferences.edit()
                                    .putBoolean("loged",true)
                                    .putString("id",id)
                                    .apply();
                                Intent intent=new Intent(Login.this,List_client_medecin.class);
                                //intent.putExtra("email",edit1.getText().toString());
                                startActivity(intent);
                            }
                            if(type.equals("secretaire"))
                            {   sharedPreferences.edit()
                                    .putBoolean("loged",true)
                                    .putString("id",id)
                                    .apply();
                                Intent intent=new Intent(Login.this,Menu_sec.class);
                                //intent.putExtra("email",edit1.getText().toString());
                                startActivity(intent);
                            }
                        }
                            else
                            {
                                Toast.makeText(Login.this, "Erreur", Toast.LENGTH_SHORT).show();
                            }
                    }
                }
    });
    }
    public boolean Isloged()
    {
        if(sharedPreferences.contains("loged"))
        {
            boolean test=sharedPreferences.getBoolean("loged",false);
            if(test)
            {
                return true;//3andou el session w ma7loula


            }
            else
            {
                return  false;//ma3andouch session
            }
        }
        else
        {
            sharedPreferences.edit().putBoolean("loged",false)
                    .apply();
            return false;
        }
    }
}

