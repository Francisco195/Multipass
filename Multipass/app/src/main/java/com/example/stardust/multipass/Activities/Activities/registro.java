package com.example.stardust.multipass.Activities.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stardust.multipass.Activities.APIService.al;
import com.example.stardust.multipass.Activities.Models.alumno;
import com.example.stardust.multipass.Activities.RetroFit.API;
import com.example.stardust.multipass.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registro extends AppCompatActivity {
    EditText nombre,correo,nomusuario,boleta,pwd,rpwd,apep,apem;
    Button regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre=(EditText)findViewById(R.id.editText);
        apep=(EditText)findViewById(R.id.editText7);
        apem=(EditText)findViewById(R.id.editText9);
        correo=(EditText)findViewById(R.id.editText2);
        nomusuario=(EditText)findViewById(R.id.editText3);
        boleta=(EditText)findViewById(R.id.editText4);
        pwd=(EditText)findViewById(R.id.editText5);
        rpwd=(EditText)findViewById(R.id.editText6);
        regis=(Button) findViewById(R.id.button2);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nom=nombre.getText().toString();
                final String ap=apep.getText().toString();
                final String am=apem.getText().toString();
                final String email=correo.getText().toString();
                String bole2=boleta.getText().toString();
                final String usuario=nomusuario.getText().toString();
                String contra=pwd.getText().toString();
                String rcontra=rpwd.getText().toString();
                if (nom.isEmpty()||ap.isEmpty()||am.isEmpty()||email.isEmpty()||bole2.isEmpty()||usuario.isEmpty()||contra.isEmpty()
                        ||rcontra.isEmpty() || !contra.equals(rcontra)) {
                    Toast.makeText(getApplicationContext(),"Llena los campos correctamente",Toast.LENGTH_LONG).show();
                }
                else{
                    final int bol=Integer.parseInt(boleta.getText().toString());
                    verificaralumno(nom, ap, am, email, bol, usuario, contra);
                    Intent intent= new Intent(registro.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }



    private void verificaralumno(final String nombre, final String ap, final String am, final String correo, final int boleta
                                    ,final String usuario, final String pwd) {
        //LEER JSON
        al service= API.getApiJSON().create(al.class);
        Call<alumno> aldatos=service.obtenerdatoalumno(boleta);
        aldatos.enqueue(new Callback<alumno>() {
            @Override
            public void onResponse(Call<alumno> call, Response<alumno> response) {
                alumno respuesta= response.body();
                if (nombre.equals(respuesta.getNombre())&& ap.equals(respuesta.getApp()) && am.equals(respuesta.getApm()) && correo.equals(respuesta.getEmail())
                        && boleta==respuesta.getIdal() && respuesta.getPwd().equals("n")&& respuesta.getUsuario().equals("n")){
                    registrar(nombre,ap,am,correo,boleta,usuario,pwd,respuesta.getGenero(), "activo");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Datos incorrectos/Alumno no encontrado/Alumno ya registrado",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<alumno> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error ",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registrar(String nombre, String ap, String am, String correo,  int boleta
                        , String usuario,  String pwd, String genero, String estado){
        //Registrar alumno
        al service= API.getApiJSON().create(al.class);
        Call<alumno> datos= service.actualizar(boleta,new alumno(boleta,usuario,estado,nombre,ap,am,genero,correo,pwd));
        datos.enqueue(new Callback<alumno>() {
            @Override
            public void onResponse(Call<alumno> call, Response<alumno> response) {
                if(response.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Alumno registrado ",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Error: "+response.message(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<alumno> call, Throwable t) {

            }
        });

    }




}
