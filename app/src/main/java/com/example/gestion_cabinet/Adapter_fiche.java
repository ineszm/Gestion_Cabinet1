package com.example.gestion_cabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_fiche extends ArrayAdapter<Rendez_vous> {
    Context ctx;
    public Adapter_fiche( Context context,  List<Rendez_vous> objects) {
        super(context, R.layout.model_client, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_client, parent, false);
        TextView titre = convertView.findViewById(R.id.titre);
        TextView descri = convertView.findViewById(R.id.desc);
        TextView time = convertView.findViewById(R.id.date_rend);
        Rendez_vous rendez_vous=getItem(position);
        titre.setText(rendez_vous.getTitre());
        descri.setText(rendez_vous.getDescription());
        time.setText(rendez_vous.getDate_ajout());
        return convertView;


    }
}
