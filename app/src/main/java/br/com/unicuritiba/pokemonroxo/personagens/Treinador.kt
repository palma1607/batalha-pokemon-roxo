package br.com.unicuritiba.pokemonroxo.personagens

import java.io.Serializable

class Treinador : Serializable {
    var nome : String? = null
    var genero : String? = null
    var altura : Float? = null
    var peso : Float? = null
    var cidadeOrigem: String? = null
    var pokemons : ArrayList<Pokemon>? = null
}