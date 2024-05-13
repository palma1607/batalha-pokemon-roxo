package br.com.unicuritiba.pokemonroxo.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.unicuritiba.pokemonroxo.R
import br.com.unicuritiba.pokemonroxo.helpers.HelperPokemon
import br.com.unicuritiba.pokemonroxo.helpers.HelperPokemon.getCorPeloTipo
import br.com.unicuritiba.pokemonroxo.personagens.Pokemon
import com.squareup.picasso.Picasso

class PokemonAdapter(
    private val listaPokemon: ArrayList<Pokemon>,
    var posicaoSelecionada : Int = 0
) : RecyclerView.Adapter<PokemonAdapter.PokemonItemViewHolder>() {

    override fun getItemCount(): Int {
        return listaPokemon.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pokemon,
            parent,
            false
            )
        return PokemonItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageViewPokemon)
        val pokemon = listaPokemon[position]

        if(posicaoSelecionada == position){
            imageView.setBackgroundColor(
                HelperPokemon.getCorPeloTipo(pokemon.tipo?.firstOrNull())
            )
        }else{
            imageView.setBackgroundColor(Color.WHITE)
        }

        Picasso.get().load(pokemon.imagemFrente).into(imageView)

        holder.itemView.setOnClickListener{
            posicaoSelecionada = position
            notifyDataSetChanged()
        }
    }

    fun getPokemonSelecionado() : Pokemon{
        return listaPokemon[posicaoSelecionada]
    }

    inner class PokemonItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

}