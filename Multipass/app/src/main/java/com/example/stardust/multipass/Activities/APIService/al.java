package com.example.stardust.multipass.Activities.APIService;


import com.example.stardust.multipass.Activities.Models.Check;
import com.example.stardust.multipass.Activities.Models.ResObj;
import com.example.stardust.multipass.Activities.Models.Email;
import com.example.stardust.multipass.Activities.Models.alumno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Stardust on 14/03/2018.
 */

public interface al {
    @GET("entidades.al/{id}")
    Call<alumno> obtenerdatoalumno(@Path("id") int id);

    @POST("entidades.al/log")
    @FormUrlEncoded
    Call<ResObj> log(@Field("us") String usuario, @Field("pwd") String pwd);

    @POST("entidades.alpa/check")
    @FormUrlEncoded
    Call <List<Check>> check(@Field("pa") String pa, @Field("pa1") String pa1, @Field("al") String al);

    @POST("entidades.al/email")
    @FormUrlEncoded
    Call<Email> email(@Field("correo") String correo);

    //@FormUrlEncoded
    @Headers("Content-Type: application/json")
    @PUT("entidades.al/{id}")
    Call<alumno> actualizar(@Path("id") int id,
                           @Body alumno alumno);
}
