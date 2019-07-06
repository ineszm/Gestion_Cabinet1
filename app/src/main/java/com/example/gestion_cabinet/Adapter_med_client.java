package com.example.gestion_cabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_med_client extends ArrayAdapter<Client> {
    Context ctx;
    public Adapter_med_client( Context context,  List<Client> objects) {
        super(context, R.layout.model_med_listeclient, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_med_listeclient, parent, false);
        TextView nom=convertView.findViewById(R.id.name);
        TextView description =convertView.findViewById(R.id.descri);
        TextView temps=convertView.findViewById(R.id.date_ajout);
        TextView titre=convertView.findViewById(R.id.tit);
       Client client=getItem(position);

        nom.setText(client.getNom());
        nom.setText(client.getPrenom());
        titre.setText(client.getTitre());
        description.setText(client.getDescription());

        temps.setText(client.getDate_ajout());
        return convertView;
    }
}
