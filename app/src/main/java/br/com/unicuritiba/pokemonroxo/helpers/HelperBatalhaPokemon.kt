package br.com.unicuritiba.pokemonroxo.helpers

import br.com.unicuritiba.pokemonroxo.enums.TIPO_POKEMON

object HelperBatalhaPokemon {

    const val ID_JOGADOR = 1
    const val ID_OPONENTE = 2

    fun verificaQuemIniciaBatalha(velocidadePokemonJogador:Int,
                                  velocidadePokemonOponente:Int): Int {
        return if(velocidadePokemonJogador >= velocidadePokemonOponente){
            ID_JOGADOR
        }else{
            ID_OPONENTE
        }
    }

    fun calcularDanoAtaque(ataque:Int, defesa:Int, fatorDeAtaque:Float) : Int {
        val danoAtaque = (ataque.toFloat()/defesa.toFloat() + 1) * 5 * fatorDeAtaque
        return danoAtaque.toInt()
    }

    fun calculaFatorDeAtaque(tiposDoAtacante: ArrayList<TIPO_POKEMON>?,
                             tiposDoDefensor: ArrayList<TIPO_POKEMON>? ) : Float {
        //TODO realizar o calculo baseado na relação do elementos
        // 2, 1 e 0,5
        return 1.0f
    }


    fun calculaChanceDesvio(velocidadePokemonJogador:Int,
                            velocidadePokemonOponente:Int) : Boolean {

        // TODO emplementar este método
        return true
    }

}