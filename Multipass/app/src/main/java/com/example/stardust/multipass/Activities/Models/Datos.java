package com.example.stardust.multipass.Activities.Models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Stardust on 24/03/2018.
 */

public class Datos {
    public String r;
    public String pwd;
    public String usuario;

    public Datos(String pwd, String usuario, String r) {
        this.pwd = pwd;
        this.usuario = usuario;
        this.r=r;

    }
    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
