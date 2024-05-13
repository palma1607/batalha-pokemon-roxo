package br.com.unicuritiba.pokemonroxo.helpers

import android.graphics.Color
import br.com.unicuritiba.pokemonroxo.enums.TIPO_POKEMON

object HelperPokemon {

    fun getCorPeloTipo(tipoPokemon: TIPO_POKEMON?) : Int{
       return when(tipoPokemon){
           TIPO_POKEMON.AGUA -> Color.parseColor("#01386A")
           TIPO_POKEMON.VOADOR -> Color.parseColor("#8CBED6")
           TIPO_POKEMON.FOGO -> Color.parseColor("#FF8E57")
           TIPO_POKEMON.TERRA -> Color.parseColor("#E2725B")
           TIPO_POKEMON.PEDRA -> Color.DKGRAY
           TIPO_POKEMON.GRAMA -> Color.parseColor("#7CFC00")
           TIPO_POKEMON.ELETRICO -> Color.parseColor("#FFFF00")
           TIPO_POKEMON.VENENOSO -> Color.parseColor("#993399")
           else -> Color.LTGRAY
        }
    }
}