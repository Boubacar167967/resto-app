package com.b1707b.cours.resto_app.favorite;

public class Menu {
    private int id_menu;
    private String dateMenu;
    private Plats diner;
    private Plats diner1;
    private Plats repas;
    private Plats repas1;

    public Menu(int id_menu, String dateMenu, Plats diner, Plats diner1, Plats repas, Plats repas1) {
        this.id_menu = id_menu;
        this.dateMenu = dateMenu;
        this.diner = diner;
        this.diner1 = diner1;
        this.repas = repas;
        this.repas1 = repas1;
    }

    public Menu() {
    }

    public String getDateMenu() {
        return dateMenu;
    }

    public void setDateMenu(String dateMenu) {
        this.dateMenu = dateMenu;
    }

    public Plats getDiner() {
        return diner;
    }

    public void setDiner(Plats diner) {
        this.diner = diner;
    }

    public Plats getDiner1() {
        return diner1;
    }

    public void setDiner1(Plats diner1) {
        this.diner1 = diner1;
    }

    public Plats getRepas() {
        return repas;
    }

    public void setRepas(Plats repas) {
        this.repas = repas;
    }

    public Plats getRepas1() {
        return repas1;
    }

    public void setRepas1(Plats repas1) {
        this.repas1 = repas1;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }
}
