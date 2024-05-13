package br.com.unicuritiba.pokemonroxo.personagens

import br.com.unicuritiba.pokemonroxo.enums.TIPO_POKEMON
import java.io.Serializable

class Pokemon : Serializable {
    var nome : String? = null
    var tipo : ArrayList<TIPO_POKEMON>? = null
    var velocidade :Int? = null
    var vida:Int = 0
    var danoRecebido:Int = 0
    var ataque: Int? = null
    var ataqueEspecial: Int? = null
    var defesa:Int? = null
    var imagemFrente : String? = null
    var imagemCosta : String? = null

    fun getVidaAtual() : Int {
        val vidaAtual = vida - danoRecebido
        return if(vidaAtual < 0) 0 else vidaAtual
    }
}