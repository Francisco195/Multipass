package com.example.stardust.multipass.Activities.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stardust.multipass.Activities.APIService.al;
import com.example.stardust.multipass.Activities.Models.DA;
import com.example.stardust.multipass.Activities.Models.alumno;
import com.example.stardust.multipass.Activities.RetroFit.API;
import com.example.stardust.multipass.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Sistema extends Fragment {
    private Button pwd;
    View v;
    private EditText pwd1,pwd2;
    public Sistema() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_sistema, container, false);
        v =inflater.inflate(R.layout.change_pwd, container, false);
        pwd=(Button)view.findViewById(R.id.contra);
        pwd1=(EditText)v.findViewById(R.id.editText8);
        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setView(v);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String pwd=pwd1.getText().toString();
                        if (pwd.isEmpty()){
                            Toast.makeText(getContext(), "Ingresa nueva contraseña" , Toast.LENGTH_LONG).show();
                        }else {
                            String boleta= DA.getStoredQuery(getActivity());
                            verificar(Integer.parseInt(String.valueOf(boleta)),pwd);
                            onDestroyView();
                        }
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onDestroyView();
                    }
                });
                builder.show();
            }
        });
        return view;
    }

    private void verificar(int boleta, final String pwd){
        al service= API.getApiJSON().create(al.class);
        Call<alumno> aldatos=service.obtenerdatoalumno(boleta);
        aldatos.enqueue(new Callback<alumno>() {
            @Override
            public void onResponse(Call<alumno> call, Response<alumno> response) {
                alumno respuesta= response.body();
                CambioContraseña(respuesta.getNombre(),respuesta.getApp(),respuesta.getApm(),
                                respuesta.getEmail(),respuesta.getIdal(),respuesta.getUsuario(),
                                pwd,respuesta.getGenero(),respuesta.getEstadoactual());
            }

            @Override
            public void onFailure(Call<alumno> call, Throwable t) {
                Toast.makeText(getContext(),"Datos incorrectos/Alumno no encontrado",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void CambioContraseña(String nombre, String ap, String am, String correo,  int boleta
                                    , String usuario,  String pwd, String genero, String estado){
        //Registrar alumno
        al service= API.getApiJSON().create(al.class);
        Call<alumno> datos= service.actualizar(boleta,new alumno(boleta,usuario,estado,nombre,ap,am,genero,correo,pwd));
        datos.enqueue(new Callback<alumno>() {
            @Override
            public void onResponse(Call<alumno> call, Response<alumno> response) {
                if(response.isSuccessful())
                    Toast.makeText(getContext(),"Contraseña actualizada ",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getContext(),"Error: "+response.message(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<alumno> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
        }
    }

}
