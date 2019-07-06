package com.example.gestion_cabinet;

public class Client extends  User {
    private String id_client;
    private String titre;
    private String description;
    private String date_ajout;
    private String id_r;

    public Client(String id, String nom, String prenom, String email, String adresse, String tel, String password, String type, String id_client, String titre, String description, String date_ajout, String id_r) {
        super(id, nom, prenom, email, adresse, tel, password, type);
        this.id_client = id_client;
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
        this.id_r = id_r;
    }

    public Client(String nom, String prenom, String email, String adresse, String tel, String password, String type, String titre, String description, String date_ajout, String id_r) {
        super(nom, prenom, email, adresse, tel, password, type);
        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
        this.id_r = id_r;
    }
    public Client()
    {
        super();
    }
    public Client(String titre,String description,String date_ajout)
    {

        this.titre = titre;
        this.description = description;
        this.date_ajout = date_ajout;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getId_r() {
        return id_r;
    }

    public void setId_r(String id_r) {
        this.id_r = id_r;
    }
}
