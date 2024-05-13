package br.com.unicuritiba.pokemonroxo.factories

import br.com.unicuritiba.pokemonroxo.enums.TIPO_POKEMON
import br.com.unicuritiba.pokemonroxo.personagens.Pokemon
import java.util.Random

object PokemonFactory {

    fun getListaPokemon() : ArrayList<Pokemon>{

        val pokemonList = arrayListOf<Pokemon>()

        val pokemonPikachu = Pokemon()
        pokemonPikachu.nome = "Pikachu"
        pokemonPikachu.ataque = 55
        pokemonPikachu.defesa = 40
        pokemonPikachu.ataqueEspecial = 50
        pokemonPikachu.velocidade = 90
        pokemonPikachu.vida = 35
        pokemonPikachu.tipo = arrayListOf(
            TIPO_POKEMON.ELETRICO
        )
        pokemonPikachu.imagemFrente = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"
        pokemonPikachu.imagemCosta = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png"
        pokemonList.add(pokemonPikachu)


        val pokemonBulbasaur = Pokemon()
        pokemonBulbasaur.nome = "Bulbasaur"
        pokemonBulbasaur.ataque = 49
        pokemonBulbasaur.defesa = 49
        pokemonBulbasaur.ataqueEspecial = 65
        pokemonBulbasaur.velocidade = 45
        pokemonBulbasaur.vida = 45
        pokemonBulbasaur.tipo = arrayListOf(
            TIPO_POKEMON.GRAMA
        )
        pokemonBulbasaur.imagemFrente = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        pokemonBulbasaur.imagemCosta = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"
        pokemonList.add(pokemonBulbasaur)

        val pokemonCharmander = Pokemon()
        pokemonCharmander.nome = "Charmander"
        pokemonCharmander.ataque = 52
        pokemonCharmander.defesa = 43
        pokemonCharmander.ataqueEspecial = 60
        pokemonCharmander.velocidade = 65
        pokemonCharmander.vida = 39
        pokemonCharmander.tipo = arrayListOf(
            TIPO_POKEMON.FOGO
        )
        pokemonCharmander.imagemFrente = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
        pokemonCharmander.imagemCosta = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/4.png"
        pokemonList.add(pokemonCharmander)

        val pokemonSquirtle = Pokemon()
        pokemonSquirtle.nome = "Squirtle"
        pokemonSquirtle.ataque = 48
        pokemonSquirtle.defesa = 65
        pokemonSquirtle.ataqueEspecial = 50
        pokemonSquirtle.velocidade = 43
        pokemonSquirtle.vida = 44
        pokemonSquirtle.tipo = arrayListOf(
            TIPO_POKEMON.AGUA
        )
        pokemonSquirtle.imagemFrente = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"
        pokemonSquirtle.imagemCosta = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/7.png"
        pokemonList.add(pokemonSquirtle)

        val pokemonButterfree = Pokemon()
        pokemonButterfree.nome = "Butterfree"
        pokemonButterfree.ataque = 45
        pokemonButterfree.defesa = 50
        pokemonButterfree.ataqueEspecial = 90
        pokemonButterfree.velocidade = 70
        pokemonButterfree.vida = 60
        pokemonButterfree.tipo = arrayListOf(
            TIPO_POKEMON.VOADOR,
            TIPO_POKEMON.VENENOSO
        )
        pokemonButterfree.imagemFrente = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png"
        pokemonButterfree.imagemCosta = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/12.png"
        pokemonList.add(pokemonButterfree)


        val pokemonOnix = Pokemon()
        pokemonOnix.nome = "Onix"
        pokemonOnix.ataque = 45
        pokemonOnix.defesa = 160
        pokemonOnix.ataqueEspecial = 30
        pokemonOnix.velocidade = 70
        pokemonOnix.vida = 35
        pokemonOnix.tipo = arrayListOf(
            TIPO_POKEMON.TERRA,
            TIPO_POKEMON.PEDRA
        )
        pokemonOnix.imagemFrente = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/95.png"
        pokemonOnix.imagemCosta = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/95.png"
        pokemonList.add(pokemonOnix)

        return pokemonList
    }

    fun getPokemonAleatorio() : Pokemon{
        val posicao = Random().nextInt(getListaPokemon().size)
        return getListaPokemon()[posicao]
    }
}