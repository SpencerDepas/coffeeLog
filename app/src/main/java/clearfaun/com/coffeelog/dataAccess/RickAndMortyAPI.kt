package clearfaun.com.coffeelog.dataAccess

import FeedResultsQuery
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class RickAndMortyAPI {

    val BASE_URL = "https://rickandmortyapi.com/graphql/"


    fun getCharacters(calback: RickAndMortyCallback) {



        val httpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val apolloClient = ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(httpClient)
            .build()

        apolloClient.query(FeedResultsQuery.builder().build())
            .enqueue(object : ApolloCall.Callback<FeedResultsQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    Log.d("", "error")

                    calback.onFailure(e)


                }

                override fun onResponse(response: Response<FeedResultsQuery.Data>) {
                    if (response.data() != null) {
                        //data = response.data()?.characters()?.results()
                        calback.onResponse(response)
                    }
                }
            })
    }
}

interface RickAndMortyCallback {
    fun onFailure(e : ApolloException)
    fun onResponse(data : Response<FeedResultsQuery.Data>)
}