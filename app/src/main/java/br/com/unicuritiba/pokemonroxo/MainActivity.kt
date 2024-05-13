package br.com.unicuritiba.pokemonroxo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.unicuritiba.pokemonroxo.adapters.PokemonAdapter
import br.com.unicuritiba.pokemonroxo.factories.PokemonFactory
import br.com.unicuritiba.pokemonroxo.personagens.Treinador

class MainActivity : AppCompatActivity() {

    private var adapter : PokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonIniciarBatalha = findViewById<Button>(R.id.buttonIniciarBatalha)
        val recyclerViewPokemon = findViewById<RecyclerView>(R.id.recyclerViewPokemon)

        recyclerViewPokemon.layoutManager = GridLayoutManager(this,4)
        adapter = PokemonAdapter(PokemonFactory.getListaPokemon())
        recyclerViewPokemon.adapter = adapter

        buttonIniciarBatalha.setOnClickListener {
            chamarTelaBatalha()
        }
    }

    private fun chamarTelaBatalha(){

        val treinadorOponente = Treinador()
        treinadorOponente.nome = "Brock"
        treinadorOponente.altura = 1.9f
        treinadorOponente.peso = 80f
        treinadorOponente.genero = "masculino"
        treinadorOponente.cidadeOrigem = "Veridian"
        treinadorOponente.pokemons = arrayListOf(PokemonFactory.getPokemonAleatorio())

        val treinadorJogador = Treinador()
        treinadorJogador.nome = "Ash"
        treinadorJogador.altura = 1.65f
        treinadorJogador.peso = 60f
        treinadorJogador.genero = "masculino"
        treinadorJogador.cidadeOrigem = "Pallet"

        adapter?.let {
            treinadorJogador.pokemons = arrayListOf(it.getPokemonSelecionado())
        }

        val batalhaActivity = Intent(this,BatalhaActivity::class.java)
        batalhaActivity.putExtra("jogador", treinadorJogador)
        batalhaActivity.putExtra("oponente", treinadorOponente)

        startActivity(batalhaActivity)

    }
}