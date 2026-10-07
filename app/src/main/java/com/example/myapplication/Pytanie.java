package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Pytanie {
    @SerializedName("tresc")

    private String tresc;
    private String odp_a;
    private String odp_b;
    private String odp_c;
    private int poprawna;

    public String getTresc() {
        return tresc;
    }

    public String getOdp_a() {
        return odp_a;
    }

    public String getOdp_b() {
        return odp_b;
    }

    public String getOdp_c() {
        return odp_c;
    }

    public int getPoprawna() {
        return poprawna;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public void setOdp_a(String odp_a) {
        this.odp_a = odp_a;
    }

    public void setOdp_b(String odp_b) {
        this.odp_b = odp_b;
    }

    public void setOdp_c(String odp_c) {
        this.odp_c = odp_c;
    }

    public void setPoprawna(int poprawna) {
        this.poprawna = poprawna;
    }
}
