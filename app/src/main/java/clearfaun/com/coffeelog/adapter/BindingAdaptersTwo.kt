package clearfaun.com.coffeelog.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import clearfaun.com.coffeelog.model.Character


@BindingAdapter("characters")
fun bindCharactersRV(
    recyclerView: RecyclerView?,
    characters: ArrayList<Character>?
) {
    if (characters != null) {
        recyclerView?.adapter = CharacterAdapter(characters)

        recyclerView?.layoutManager = GridLayoutManager(
            recyclerView?.context,
            1,
            RecyclerView.VERTICAL,
            false
        )
    }
}

//@BindingAdapter("layout_vertical")
//fun bindLayoutManager(@NonNull recyclerView: RecyclerView, vertical: Boolean) {
//    val orientation = if (vertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL
//    recyclerView.layoutManager = NpaGridLayoutManager(
//        recyclerView.context,
//        1,
//        orientation,
//        false
//    )
//}
