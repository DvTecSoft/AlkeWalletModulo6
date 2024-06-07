package vidal.daniel.alkewallet.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase que indica la ruta de la API
 */
class RetrofitInstancia
{
    companion object
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}