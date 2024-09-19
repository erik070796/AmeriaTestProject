package erik.chatinyan.ameriatest.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

fun provideRetrofit(): Retrofit {
    val okHttpClient = OkHttpClient().newBuilder().build()
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

fun provideApi(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

