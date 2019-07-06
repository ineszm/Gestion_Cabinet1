package com.example.gestion_cabinet;

public class Rendez_vous extends Client {
    private String id_r;
    private String date_r;

    public Rendez_vous(String id, String nom, String prenom, String email, String adresse, String tel, String password, String type, String id_client, String titre, String description, String date_ajout, String id_r, String id_r1, String date_r) {
        super(id, nom, prenom, email, adresse, tel, password, type, id_client, titre, description, date_ajout, id_r);
        this.id_r = id_r1;
        this.date_r = date_r;
    }

    public Rendez_vous(String nom, String prenom, String email, String adresse, String tel, String password, String type, String titre, String description, String date_ajout, String id_r, String date_r) {
        super(nom, prenom, email, adresse, tel, password, type, titre, description, date_ajout, id_r);
        this.date_r = date_r;
    }

    public Rendez_vous()
    {super();
    }
    public Rendez_vous(String date_r)
    {
        this.date_r=date_r;
    }

    @Override
    public String getId_r() {
        return id_r;
    }

    @Override
    public void setId_r(String id_r) {
        this.id_r = id_r;
    }

    public String getDate_r() {
        return date_r;
    }

    public void setDate_r(String date_r) {
        this.date_r = date_r;
    }
}
