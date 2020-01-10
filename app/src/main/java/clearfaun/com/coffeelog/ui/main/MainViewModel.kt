package clearfaun.com.coffeelog.ui.main

import FeedResultsQuery
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import clearfaun.com.coffeelog.dataAccess.RickAndMortyAPI
import clearfaun.com.coffeelog.dataAccess.RickAndMortyCallback
import clearfaun.com.coffeelog.model.Character
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class MainViewModel : ViewModel(), RickAndMortyCallback {

    var data: (MutableList<FeedResultsQuery.Result>)? = null

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word


    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters


    init {

        makeRequest()
        _word.value = "silly"
    }


    fun makeRequest() {

        val api = RickAndMortyAPI()
        api.getCharacters(this)
    }

    override fun onFailure(e: ApolloException) {
        Log.d("", "")
        var bog = "ddd"
    }

    override fun onResponse(data: Response<FeedResultsQuery.Data>) {
        _word.postValue(data.data()?.characters()?.results()?.get(0)?.name())
        _characters.postValue(convertToCharacter(data.data()?.characters()?.results()))
    }

    fun convertToCharacter(characters: List<FeedResultsQuery.Result>?): MutableList<Character> {

        val myCharacters = mutableListOf<Character>()
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
}
