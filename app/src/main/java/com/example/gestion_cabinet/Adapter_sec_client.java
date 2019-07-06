package com.example.gestion_cabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_sec_client  extends ArrayAdapter<Client> {
    Context ctx;
    public Adapter_sec_client( Context context,   List<Client> objects) {
        super(context, R.layout.model_sec_listeclient, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_sec_listeclient , parent, false);
        TextView nom=convertView.findViewById(R.id.nom);
        TextView prenom=convertView.findViewById(R.id.pre);
        TextView time=convertView.findViewById(R.id.date_ajouter);
        Client client = getItem(position);
        nom.setText(client.getNom());
        prenom.setText(client.getPrenom());
        time.setText(client.getDate_ajout());






        return convertView;
    }
}
