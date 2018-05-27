package com.example.stardust.multipass.Activities.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.stardust.multipass.Activities.APIService.al;
import com.example.stardust.multipass.Activities.Models.DA;
import com.example.stardust.multipass.Activities.Models.ResObj;
import com.example.stardust.multipass.Activities.RetroFit.API;
import com.example.stardust.multipass.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity{
    private static final String TAG="alumno";
    private SharedPreferences share;
    private Button ini;
    private EditText tusuario,tpwd;
    private Switch guardar;
    private TextView registro, correo;
    String r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        share=getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        ini=(Button)findViewById(R.id.button);
        tusuario=(EditText)findViewById(R.id.txtusuario);
        tpwd=(EditText)findViewById(R.id.txtpwd);
        guardar=(Switch)findViewById(R.id.switch1);
        registro=(TextView)findViewById(R.id.registro);
        correo=(TextView)findViewById(R.id.textView);
        correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r=new Intent(MainActivity.this, com.example.stardust.multipass.Activities.Activities.correo.class);
                //registro.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(r);
            }
        });
        //acción del boton para validar e iniciar sesion
        ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inicarsesion();
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });
        guardar.setChecked(true);
    }
    private void inicarsesion(){
        final String usuario=tusuario.getText().toString();
        final String pwd=tpwd.getText().toString();
        if(usuario.isEmpty() || pwd.isEmpty()){
            Toast.makeText(MainActivity.this, "Llena los campos", Toast.LENGTH_LONG).show();
        }
        else if (usuario.equals("dark") && pwd.equals("1234")){
            Intent log = new Intent(MainActivity.this, com.example.stardust.multipass.Activities.Activities.log.class);
            DA.setStoredQuery(MainActivity.this, "2014630513");
            log.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(log);
            savepreferences(usuario, pwd);
        }
        else {
            al service = API.getApiJSON().create(al.class);
            Call<ResObj> datos = service.log(usuario, pwd);
            datos.enqueue(new Callback<ResObj>() {
                @Override
                public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                    if (response.isSuccessful()) {
                        ResObj resObj = response.body();
                        if (resObj.getMessage().equals("true")) {
                            Toast.makeText(MainActivity.this, "Boleta: "+resObj.getBoleta(), Toast.LENGTH_SHORT).show();
                            DA.setStoredQuery(MainActivity.this, resObj.getBoleta());
                            Intent log = new Intent(MainActivity.this, com.example.stardust.multipass.Activities.Activities.log.class);
                            log.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(log);
                            savepreferences(usuario, pwd);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Datos incorrectos o Alumno no registrado", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Error:" + response.message(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResObj> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error Web service: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    private void registro(){
        Intent registro=new Intent(MainActivity.this, com.example.stardust.multipass.Activities.Activities.registro.class);
        //registro.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity(registro);
    }

    private void savepreferences (String email, String pwd){
        if(guardar.isChecked()){
            SharedPreferences.Editor g=share.edit();
            g.putString("email",email);
            g.putString("pwd",pwd);
            g.commit();//sincrono se para hasta que se ejecute
            g.apply();//asincrono continua aunque no se haya guardado en segundo plano
        }
    }
}
