package clearfaun.com.coffeelog.ui.main

import FeedResultsQuery
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import clearfaun.com.coffeelog.dataAccess.RickAndMortyAPI
import clearfaun.com.coffeelog.dataAccess.RickAndMortyCallback
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class MainViewModel : ViewModel(), RickAndMortyCallback {

    val BASE_URL = "https://rickandmortyapi.com/graphql/"
    var data: (MutableList<FeedResultsQuery.Result>)? = null


    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    init {
        Log.d("", "") //
        makeRequest()
        _word.value = "silly"


        _word.value = "COCK"

    }


    fun makeRequest() {

        val api = RickAndMortyAPI()
        api.getCharacters(this)

//        val httpClient = OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build()
//
//        val apolloClient = ApolloClient.builder()
//            .serverUrl(BASE_URL)
//            .okHttpClient(httpClient)
//            .build()
//
//
//        apolloClient.query(FeedResultsQuery.builder().build())
//            .enqueue(object : ApolloCall.Callback<FeedResultsQuery.Data>() {
//                override fun onFailure(e: ApolloException) {
//                    Log.d("", "error")
//                }
//
//                override fun onResponse(response: Response<FeedResultsQuery.Data>) {
//                    if (response.data() != null) {
//                        data = response.data()?.characters()?.results()
//                        _word.postValue(response.data()?.characters()?.results()?.get(0)?.name())
//                    }
//                }
//            })
    }

    override fun onFailure(e: ApolloException) {
        Log.d("", "")
        var bog = "ddd"
    }

    override fun onResponse(data: Response<FeedResultsQuery.Data>) {
        Log.d("", "")
        var bog = "ddd"
    }


}
