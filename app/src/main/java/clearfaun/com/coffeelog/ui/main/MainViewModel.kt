package clearfaun.com.coffeelog.ui.main

import FeedResultsQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import clearfaun.com.coffeelog.dataAccess.RickAndMortyAPI
import clearfaun.com.coffeelog.dataAccess.RickAndMortyCallback
import clearfaun.com.coffeelog.model.Character

class MainViewModel() : ViewModel(), RickAndMortyCallback {

    var callback: DataCallback? = null

    var data: (MutableList<FeedResultsQuery.Result>)? = null

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word


    private val _characters = MutableLiveData<ArrayList<Character>>()
    val characters: LiveData<ArrayList<Character>>
        get() = _characters


    init {
        getCharacterData()
        //_word.value = "silly"
    }

    private fun getCharacterData() {

        val api = RickAndMortyAPI()
        api.getCharacters(this)
    }


    override fun onResponse(data: ArrayList<Character>) {
        _word.postValue(data.get(0).name)
        _characters.postValue(data)

        callback?.onResponse(data)
    }

}

interface DataCallback {
    fun onResponse(data: ArrayList<Character>?)
}
