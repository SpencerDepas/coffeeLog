package clearfaun.com.coffeelog.adapter

import android.os.Build
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import clearfaun.com.coffeelog.model.Character
import com.bumptech.glide.Glide


@BindingAdapter("characters")
fun bindCharactersRV(
    recyclerView: RecyclerView?,
    characters: ArrayList<Character>?
) {
    if (characters != null) {
        recyclerView?.adapter = CharacterAdapter(characters)
    }
}

@BindingAdapter("layout_vertical")
fun bindLayoutManager(@NonNull recyclerView: RecyclerView, vertical: Boolean) {
    val orientation = if (vertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL
    recyclerView.layoutManager = GridLayoutManager(
        recyclerView.context,
        2,
        orientation,
        false
    )
}

@BindingAdapter("android:src")
fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView);
}

@BindingAdapter("setClipToOutline")
fun loadImage(imageView: ImageView, clipToOutline: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        imageView.setClipToOutline(clipToOutline)
    }
}

