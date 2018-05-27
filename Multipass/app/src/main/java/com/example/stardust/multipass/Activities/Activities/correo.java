package com.example.stardust.multipass.Activities.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stardust.multipass.Activities.APIService.al;
import com.example.stardust.multipass.Activities.Models.Email;
import com.example.stardust.multipass.Activities.RetroFit.API;
import com.example.stardust.multipass.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class correo extends AppCompatActivity {
    private EditText correo;
    private Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);
        correo=(EditText)findViewById(R.id.correo);
        enviar=(Button)findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarcorreo();
            }
        });

    }
    public void enviarcorreo(){
        String email=correo.getText().toString();
        if(email.isEmpty() ){
            Toast.makeText(correo.this, "Llena los campos ", Toast.LENGTH_LONG).show();
        }
        else if(isValidEmail(email)){
            al service = API.getApiJSON().create(al.class);
            Call<Email> datos = service.email(email);
            datos.enqueue(new Callback<Email>() {
                @Override
                public void onResponse(Call<Email> call, Response<Email> response) {
                    Toast.makeText(correo.this, "Correo enviado", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Email> call, Throwable t) {

                }
            });
        }
        else if(isValidEmail(email)==false){
            Toast.makeText(correo.this, "Correo invalido", Toast.LENGTH_LONG).show();
        }
    }
    public final static boolean isValidEmail(CharSequence email) {
        if (email== null) return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
