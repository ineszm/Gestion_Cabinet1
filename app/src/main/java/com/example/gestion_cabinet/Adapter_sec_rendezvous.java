package com.example.gestion_cabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_sec_rendezvous extends ArrayAdapter<Rendez_vous> {
    Context ctx;
    public Adapter_sec_rendezvous( Context context,  List<Rendez_vous> objects) {
        super(context, R.layout.model_sec_rendezvous, objects);
        this.ctx=context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.model_sec_rendezvous, parent, false);
        TextView nom=convertView.findViewById(R.id.name_prenom);
        TextView prenom=convertView.findViewById(R.id.msg);
        TextView date_ren =convertView.findViewById(R.id.date_re);
        Rendez_vous rendez_vous=getItem(position);
        nom.setText(rendez_vous.getNom());
        prenom.setText(rendez_vous.getPrenom());
        date_ren.setText(rendez_vous.getDate_r());






        return convertView;
    }
}
