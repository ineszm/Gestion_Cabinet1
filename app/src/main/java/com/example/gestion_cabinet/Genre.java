package com.example.gestion_cabinet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Genre extends AppCompatActivity {
Button button;
Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        button=findViewById(R.id.ok);
        String[] arraySpinner = new String[] {
                "médecin", "secrétaire", "client"
        };
        spinner=findViewById(R.id.spinn);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //selection de valeur de spinner
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItem().toString()=="client")
                {
                    Intent intent=new Intent(Genre.this,Login.class);
                    intent.putExtra("ch","client");
                    startActivity(intent);
                }
                else if(spinner.getSelectedItem().toString()=="médecin")
                {
                    Intent intent=new Intent(Genre.this,Login.class);
                    intent.putExtra("ch","médecin");
                    startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(Genre.this,Login.class);
                    intent.putExtra("ch","secretaire");
                    startActivity(intent);
                }
            }
        });


    }
}
