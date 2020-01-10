package clearfaun.com.coffeelog.model

class Character(val name: String?,
                val image: String?,
                val species: String?,
                val gender: String?,
                val type: String?) {



//    public fun convertToCharacter(characters : List<FeedResultsQuery.Result>?) : MutableList<Character>{
//
//        val myCharacters = mutableListOf<Character>()
//        characters?.forEach{ character ->
//            myCharacters.add(character.toCharacter())
//        }
//
//        return myCharacters
//    }
//
//
//    fun FeedResultsQuery.Result.toCharacter() = Character(
//        name = this.name(),
//        image = this.image(),
//        species = this.species(),
//        gender = this.gender(),
//        type = this.type()
//    )
}