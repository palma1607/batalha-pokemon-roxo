package br.com.unicuritiba.pokemonroxo

import android.app.AlertDialog
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.unicuritiba.pokemonroxo.helpers.HelperBatalhaPokemon
import br.com.unicuritiba.pokemonroxo.helpers.HelperBatalhaPokemon.ID_JOGADOR
import br.com.unicuritiba.pokemonroxo.helpers.HelperBatalhaPokemon.ID_OPONENTE
import br.com.unicuritiba.pokemonroxo.personagens.Pokemon
import br.com.unicuritiba.pokemonroxo.personagens.Treinador
import com.squareup.picasso.Picasso

class BatalhaActivity : AppCompatActivity() {

    private var treinadorOponente : Treinador? = null
    private var treinadorJogador : Treinador? = null

    private  var pokemonJogador : Pokemon? = null
    private  var pokemonOponente : Pokemon? = null

    private var buttonFugir : Button? = null
    private var buttonAtacar : Button? = null
    private var buttonDesviar : Button? = null

    private var textViewPokemonOponenteVida : TextView? = null
    private var textViewPokemonJogadorVida : TextView? = null
    private var imagePokemonJogador : ImageView? = null
    private var imagePokemonOponente : ImageView? = null

    private var idJogadorAtual :Int? = 0

    private var desvioAtaque : Boolean = false

    private val handlerJogadaOponente = Handler(Looper.getMainLooper()){
        if(!desvioAtaque) {
            jogadorAtacar()
        }else{
            trocarTurno()
        }
        return@Handler true
    }

    private val handlerTurno = Handler(Looper.getMainLooper()){

        idJogadorAtual = it.arg1
        desvioAtaque = false
        atualizarBotoes()

        if(idJogadorAtual != ID_JOGADOR){
            handlerJogadaOponente.sendEmptyMessageDelayed(0, 1500)
        }

        return@Handler true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batalha)

        treinadorJogador = intent?.getSerializableExtra("jogador") as Treinador
        treinadorOponente = intent?.getSerializableExtra("oponente") as Treinador


        val textViewNomeJogador = findViewById<TextView>(R.id.textViewJogador)
        val textViewNomeOponente = findViewById<TextView>(R.id.textViewOponente)

        imagePokemonJogador = findViewById(R.id.imageViewPokemonJogador)
        imagePokemonOponente = findViewById(R.id.imageViewPokemonOponente)

        textViewNomeJogador.text = treinadorJogador?.nome
        textViewNomeOponente.text = treinadorOponente?.nome

        pokemonOponente = treinadorOponente?.pokemons?.firstOrNull()
        pokemonJogador = treinadorJogador?.pokemons?.firstOrNull()

        Picasso.get().load(pokemonJogador?.imagemCosta).into(imagePokemonJogador)
        Picasso.get().load(pokemonOponente?.imagemFrente).into(imagePokemonOponente)

        buttonFugir = findViewById(R.id.buttonFugir)
        buttonAtacar = findViewById(R.id.buttonAtaque)
        buttonDesviar = findViewById(R.id.buttonDesviar)

        textViewPokemonOponenteVida = findViewById(R.id.textViewPokemonOponenteVida)
        textViewPokemonJogadorVida = findViewById(R.id.textViewPokemonJogadorVida)

        buttonAtacar?.setOnClickListener {
            jogadorAtacar()
        }

        buttonFugir?.setOnClickListener {
            jogadorFugir()
        }

        buttonDesviar?.setOnClickListener {
            jogadorDesvio()
            buttonDesviar?.isEnabled = false

        }

        atualizarPokemonVida()
    }

    override fun onResume() {
        super.onResume()

        val velocidadePokemonJogador = pokemonJogador?.velocidade ?: 0
        val velocidadePokemonOponente = pokemonOponente?.velocidade ?: 0

        val message = Message()
        message.arg1 = HelperBatalhaPokemon.verificaQuemIniciaBatalha(
            velocidadePokemonJogador,
            velocidadePokemonOponente
        )

        handlerTurno.sendMessage(
            message
        )
    }

    private fun jogadorDesvio(){
        val velocidadePokemonJogador = pokemonJogador?.velocidade ?: 0
        val velocidadePokemonOponente = pokemonOponente?.velocidade ?: 0

        desvioAtaque = HelperBatalhaPokemon.calculaChanceDesvio(
            velocidadePokemonJogador,
            velocidadePokemonOponente
        )

        if(desvioAtaque){
            setPokemonJogadorAnimacao(R.anim.desvio_jogador_animacao)
        }
    }

    private fun atualizarBotoes(){
        val isJogadorVez = idJogadorAtual == ID_JOGADOR
        buttonAtacar?.isEnabled = isJogadorVez
        buttonFugir?.isEnabled = isJogadorVez
        buttonDesviar?.isEnabled = !isJogadorVez
    }

    private fun jogadorFugir(){
        finish()
    }

    private fun jogadorAtacar(){

        val isJogadorVez = idJogadorAtual == ID_JOGADOR

        setSomAtaque()

        if(isJogadorVez){
            setPokemonJogadorAnimacao(R.anim.ataque_jogador_animacao)
        }else{
            setPokemonOponenteAnimacao(R.anim.ataque_oponente_animacao)
        }

        val tiposPokemonAtacante = if(isJogadorVez){
            pokemonJogador?.tipo
        } else {
            pokemonOponente?.tipo
        }

        val tiposPokemonDefensor = if(!isJogadorVez){
            pokemonJogador?.tipo
        } else {
            pokemonOponente?.tipo
        }

        val fatorDeAtaque = HelperBatalhaPokemon.calculaFatorDeAtaque(
            tiposPokemonAtacante, tiposPokemonDefensor
        )

        val ataqueValor = if(isJogadorVez){
            pokemonJogador?.ataque
        }else{
            pokemonOponente?.ataque
        }

        val defesaValor = if(!isJogadorVez){
            pokemonJogador?.defesa
        }else{
            pokemonOponente?.defesa
        }

        val danoAtaque = HelperBatalhaPokemon.calcularDanoAtaque(
            ataqueValor ?: 0,
            defesaValor ?: 0,
            fatorDeAtaque
        )

        if (isJogadorVez) {
            pokemonOponente?.danoRecebido = pokemonOponente?.danoRecebido?.plus(danoAtaque) ?:0
        } else {
            pokemonJogador?.danoRecebido = pokemonJogador?.danoRecebido?.plus(danoAtaque) ?:0
        }

        atualizarPokemonVida()
        if(!verificaTermino()) {
            trocarTurno()
        }
    }

    private fun trocarTurno(){
        val message = Message()
        message.arg1 = if(idJogadorAtual == ID_JOGADOR) {
            ID_OPONENTE
        } else {
            ID_JOGADOR
        }
        handlerTurno.sendMessage(message)
    }

    private fun atualizarPokemonVida(){
        textViewPokemonJogadorVida?.text =
            pokemonJogador?.getVidaAtual()?.toString() + "/" + pokemonJogador?.vida?.toString()

        textViewPokemonOponenteVida?.text =
            pokemonOponente?.getVidaAtual()?.toString() + "/" + pokemonOponente?.vida?.toString()
    }

    private fun verificaTermino() : Boolean {
        var termino = false
        pokemonJogador?.also { pokemon ->
            if(pokemon.getVidaAtual() <= 0){
                exibirDialogFinalBatalha(ID_OPONENTE)
                termino = true
            }
        }

        pokemonOponente?.also {pokemon ->
            if(pokemon.getVidaAtual() <= 0){
                exibirDialogFinalBatalha(ID_JOGADOR)
                termino = true
            }
        }
        return termino
    }

    private fun exibirDialogFinalBatalha(idJogadorVencedor:Int ){

        val dialog = AlertDialog.Builder(this)

        dialog.setTitle("Resultado Batalha Pokemon")

        if(idJogadorVencedor == ID_JOGADOR){
            dialog.setMessage("Parabéns, você venceu !!! ")
            setPokemonOponenteAnimacao(R.anim.perdeu_luta_animacao)
        }else{
            setPokemonJogadorAnimacao(R.anim.perdeu_luta_animacao)
            dialog.setMessage("Você perdeu esta batalha," +
                    " mas está no caminho de ser um grande mestre Pokemon!!")
        }

        dialog.setPositiveButton("OK"){ dialog, _ ->
            dialog.dismiss()
            finish()
        }

        dialog.create().show()
    }

    private fun setPokemonOponenteAnimacao(animacao: Int){
        imagePokemonOponente?.startAnimation(
            AnimationUtils.loadAnimation(this,animacao)
        )
    }

    private fun setPokemonJogadorAnimacao(animacao: Int){
        imagePokemonJogador?.startAnimation(
            AnimationUtils.loadAnimation(this,animacao)
        )
    }

    private fun setSomAtaque(){
        MediaPlayer.create(this,R.raw.karate_chop).start()
    }
}