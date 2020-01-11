package clearfaun.com.coffeelog.dataAccess

import FeedResultsQuery
import android.util.Log
import clearfaun.com.coffeelog.model.Character
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.OkHttpClient
import java.lang.Exception
import java.util.concurrent.TimeUnit

class RickAndMortyAPI {

    val BASE_URL = "https://rickandmortyapi.com/graphql/"

    lateinit var callback: RickAndMortyCallback
    val uiScope = CoroutineScope(Dispatchers.Main)
    val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun getCharacters(_callback: RickAndMortyCallback) {

        Log.d("", "")
        callback = _callback






       // loadData()

        loadDataTwo()


    }


    fun loadDataTwo(){

        GlobalScope.launch{Dispatchers.Main

            try{

                val httpClient = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

                val apolloClient = ApolloClient.builder()
                    .serverUrl(BASE_URL)
                    .okHttpClient(httpClient)
                    .build()


                val deferred =
                    apolloClient.query(FeedResultsQuery.builder().build()).toDeferred()

                val response = deferred.await()
                val repositories = response.data()?.characters()?.results() ?: emptyList()

                callback.onResponse(convertToCharacter(repositories))



            } catch (e: ApolloException) {
                // you will end up here if .await() throws, most likely due to a transport or parsing error
              Log.d("","")

                val dataa = arrayListOf(
                    Character("Raising Arizona", "", "", "", ""),
                    Character("Raisijjkkjjkng Arizona", "", "", "", ""),
                    Character("Raisinlkhlkjljzona", "", "", "", ""),
                    Character("Raisihkjhhhhha", "", "", "", ""),
                    Character("Raisyyyyyyyy777na", "", "", "", ""),
                    Character("Rai987987987zona", "", "", "", "")
                )



                callback.onResponse(dataa)



            } catch (e: NullPointerException) {
                // you will end up here if repositories!! throws above. This will happen if your server sends a response
                // with missing fields or errors
                Log.d("","")

            } finally {
                // in all cases, hide the progress bar
                Log.d("","")
            }
        }
    }



//        apolloClient.query(FeedResultsQuery.builder().build())
//            .enqueue(object : ApolloCall.Callback<FeedResultsQuery.Data>() {
//                override fun onFailure(e: ApolloException) {
//                    Log.d("", "error")
//
//                    callback.onFailure(e)
//
//
//                }
//
//                override fun onResponse(response: Response<FeedResultsQuery.Data>) {
//                    if (response.data() != null) {
//                        //data = response.data()?.characters()?.results()
//                        callback.onResponse(response)
//                    }
//                }
//            })
}

fun convertToCharacter(characters: List<FeedResultsQuery.Result>?): ArrayList<Character> {

    val myCharacters = ArrayList<Character>()
    characters?.forEach { character ->
        myCharacters.add(character.toCharacter())
    }

    return myCharacters
}

fun FeedResultsQuery.Result.toCharacter() = Character(
    name = this.name(),
    image = this.image(),
    species = this.species(),
    gender = this.gender(),
    type = this.type()
)


interface RickAndMortyCallback {
    fun onResponse(data: ArrayList<Character>)
}