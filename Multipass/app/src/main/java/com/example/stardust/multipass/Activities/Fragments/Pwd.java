package com.example.stardust.multipass.Activities.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stardust.multipass.Activities.APIService.al;
import com.example.stardust.multipass.Activities.Activities.MainActivity;
import com.example.stardust.multipass.Activities.Activities.correo;
import com.example.stardust.multipass.Activities.Models.Check;
import com.example.stardust.multipass.Activities.Models.DA;
import com.example.stardust.multipass.Activities.Models.alumno;
import com.example.stardust.multipass.Activities.RetroFit.API;
import com.example.stardust.multipass.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Pwd extends Fragment {
    private Button dal,da2;
    private TextView d2,d3,d4;
    View v;
    public Pwd() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        //layout del dialog
        v = LayoutInflater.from(getActivity()).inflate(R.layout.style_datos, container,false);
        d2=(TextView)v.findViewById(R.id.textView5);
        d3=(TextView)v.findViewById(R.id.textView6);
        d4=(TextView)v.findViewById(R.id.textView7);
        //layout del fragmento
        View view =inflater.inflate(R.layout.fragment_pwd, container, false);
        final String d1=DA.getStoredQuery(getActivity());
        da2=(Button)view.findViewById(R.id.button4) ;
        dal=(Button) view.findViewById(R.id.button3);
        final int dato= Integer.parseInt(String.valueOf(d1));//boleta
        final String[] nombre = new String[1];
        final String[] correo = new String[1];
        dal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                al service= API.getApiJSON().create(al.class);
                Call<alumno> aldatos=service.obtenerdatoalumno(dato);
                aldatos.enqueue(new Callback<alumno>() {
                    @Override
                    public void onResponse(Call<alumno> call, Response<alumno> response) {
                        alumno respuesta= response.body();
                        nombre[0] =respuesta.getNombre();
                        correo[0] =respuesta.getEmail();
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setView(v);
                        d2.setText(nombre[0]);
                        d3.setText(d1);
                        d4.setText(correo[0]);
                        //builder.setMessage("Nombre: "+nombre[0]+"\nBoleta: "+d1+"\nCorreo: "+correo[0]);
                        builder.setTitle("Datos alumno");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                onDestroyView();
                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.show();
                    }
                    @Override
                    public void onFailure(Call<alumno> call, Throwable t) {
                        Toast.makeText(getActivity(),"Error ",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        da2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] result = new String[1];
                result[0]="";
                al service= API.getApiJSON().create(al.class);
                Call <List<Check>> aldatos=service.check("1","2",d1);
                aldatos.enqueue(new Callback<List<Check>>() {
                    @Override
                    public void onResponse(Call<List<Check>> call, Response<List<Check>> response) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("Datos sistema");
                        final List<Check> respuesta=response.body();
                        if(response.body()==null){
                            Toast.makeText(getContext(), "No hay registro" , Toast.LENGTH_LONG).show();
                        }else{
                            for (Check cl:respuesta){
                                result[0] =result[0]+cl.getPa_registro()+"\n";
                            }
                            builder.setMessage(result[0]);
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    onDestroyView();
                                }
                            });
                            AlertDialog dialog=builder.create();
                            dialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Check>> call, Throwable t) {

                    }
                });

            }
        });

        return view;
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
