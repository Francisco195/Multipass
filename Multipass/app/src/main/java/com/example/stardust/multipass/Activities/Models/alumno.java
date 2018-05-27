package com.example.stardust.multipass.Activities.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class alumno {
    public int idal;
    @SerializedName("usuario")
    @Expose
    public String usuario;
    @SerializedName("estadoactual")
    @Expose
    public String estadoactual;
    public String nombre;
    public String app;
    public String apm;
    public String genero;
    public String email;
    @SerializedName("pwd")
    @Expose
    public String pwd;

    public alumno(int idal, String usuario, String estadoactual, String nombre, String app, String apm, String genero, String email, String pwd) {
        this.idal = idal;
        this.usuario = usuario;
        this.estadoactual = estadoactual;
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.genero = genero;
        this.email = email;
        this.pwd = pwd;
    }

    public int getIdal() {
        return idal;
    }

    public void setIdal(int idal) {
        this.idal = idal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstadoactual() {
        return estadoactual;
    }

    public void setEstadoactual(String estadoactual) {
        this.estadoactual = estadoactual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
