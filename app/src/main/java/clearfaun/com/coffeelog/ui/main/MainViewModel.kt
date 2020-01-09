package clearfaun.com.coffeelog.ui.main

import FeedResultsQuery
import android.util.Log
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var BASE_URL = "https://rickandmortyapi.com/graphql/"


    init {
        Log.d("","")
        makeRequest()
    }


    fun makeRequest() {

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
                    // error
                    Log.d("","")
                }

                override fun onResponse(response: Response<FeedResultsQuery.Data>) {
                    if (response.data() != null) {

                        listOf(response.data())[0]

                      //  Log.d("","Name : " + response?.data()?.characters()?.results()[0]?.name())
                        Log.d("","Name : " + response.data()?.characters()?.results()?.get(0)?.name())


                    }
                }
            })

    }

}
