package clearfaun.com.coffeelog.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import clearfaun.com.coffeelog.databinding.CharacterItemBinding
import clearfaun.com.coffeelog.model.Character

class CharacterAdapter(var characters: ArrayList<Character>) :
    RecyclerView.Adapter<CharacterAdapter.UserViewHolder>() {

    init{
        Log.d("","")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CharacterItemBinding.inflate(inflater)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(characters.get(position))
    }

    fun updateUsers(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    class UserViewHolder(val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.item = character
            binding.executePendingBindings()
        }
    }
}