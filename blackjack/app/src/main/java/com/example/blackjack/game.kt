package com.example.blackjack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import kotlin.collections.ArrayList

class game : AppCompatActivity() {

    private var gameOver = false
    private var win = false
    var dealersHand = ArrayList<Int>()
    var dealerTotal = 0
    var playersHand = ArrayList<Int>()
    var playerTotal = 0
    var playerCardNum = 0
    var dealerCardNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        restart_btn.visibility = View.INVISIBLE


        val cards =  mutableMapOf(
                R.drawable.clubs_ace to 11, R.drawable.clubs_jack to 10, R.drawable.clubs_queen to 10, R.drawable.clubs_king to 10, R.drawable.clubs2 to 2,
                R.drawable.clubs3 to 3, R.drawable.clubs4 to 4, R.drawable.clubs5 to 5, R.drawable.clubs6 to 6, R.drawable.clubs7 to 7, R.drawable.clubs8 to 8,
                R.drawable.clubs9 to 9, R.drawable.clubs10 to 10, R.drawable.diamonds_ace to 11, R.drawable.diamonds_jack to 10, R.drawable.diamonds_king to 10,
                R.drawable.diamonds_queen to 10, R.drawable.diamonds2 to 2, R.drawable.diamonds3 to 3, R.drawable.diamonds4 to 4, R.drawable.diamonds5 to 5,
                R.drawable.diamonds6 to 6, R.drawable.diamonds7 to 7, R.drawable.diamonds8 to 8, R.drawable.diamonds9 to 9, R.drawable.diamonds10 to 10,
                R.drawable.hearts_ace to 11, R.drawable.hearts_jack to 10, R.drawable.hearts_queen to 10, R.drawable.hearts_king to 10, R.drawable.hearts2 to 2,
                R.drawable.hearts3 to 3, R.drawable.hearts4 to 4, R.drawable.hearts5 to 5, R.drawable.hearts6 to 6, R.drawable.hearts7 to 7, R.drawable.hearts8 to 8,
                R.drawable.hearts9 to 9, R.drawable.hearts10 to 10, R.drawable.spades_ace to 11, R.drawable.spades_jack to 10, R.drawable.spades_queen to 10,
                R.drawable.spades_king to 10, R.drawable.spades2 to 2, R.drawable.spades3 to 3, R.drawable.spades4 to 4, R.drawable.spades5 to 5, R.drawable.spades6 to 6,
                R.drawable.spades7 to 7, R.drawable.spades8 to 8, R.drawable.spades9 to 9, R.drawable.spades10 to 10
        )

        val playerCards = arrayOf(player_1, player_2, player_3, player_4, player_5)
        val dealerCards = arrayOf(dealer_1, dealer_2, dealer_3, dealer_4, dealer_5)

        for(i in 0 until 2){
            playerCardNum ++
            dealerCardNum ++
            addDealer(cards, dealerCards[i])
            addPlayer(cards, playerCards[i])
        }

        checkGameOver()
        if(gameOver){
            gameOver()
        }

        hit_btn.setOnClickListener{
            if(!gameOver){
                if(playerCardNum < 5 && dealerCardNum < 5){
                    addDealer(cards, dealerCards[dealerCardNum])
                    addPlayer(cards, playerCards[playerCardNum])
                    playerCardNum ++
                    dealerCardNum ++
                }
                checkGameOver()
                if(gameOver){
                    gameOver()
                }
            }
        }

        pass_btn.setOnClickListener {
            if(!gameOver){
                if(dealerCardNum < 5){
                    addDealer(cards, dealerCards[dealerCardNum])
                    dealerCardNum ++
                }
                checkGameOver()
                if(gameOver){
                    gameOver()
                }

            }
        }

        restart_btn.setOnClickListener{
            if(gameOver){
                gameOver = false
                restart()
            }
        }




    }

    private fun addDealer(cards: MutableMap<Int, Int>, card: ImageView) {
        var randomCard =cards.entries.elementAt(Random().nextInt(cards.size))
        dealerTotal += randomCard.value
        dealersHand.add(randomCard.key)
        if(card != dealer_1){
            card.setImageResource(randomCard.key)
        }
        cards.remove(randomCard.key)
    }

    private fun addPlayer(cards: MutableMap<Int, Int>, card: ImageView) {
        var randomCard =cards.entries.elementAt(Random().nextInt(cards.size))
        playerTotal += randomCard.value
        playersHand.add(randomCard.key)
        cards.remove(randomCard.key)
        card.setImageResource(randomCard.key)
    }

    private fun checkGameOver(){
        if(dealerTotal == 21){
            gameOver = true
            win = false
        }else if(dealerTotal > 21 && playerTotal < 21){
            gameOver = true
            win = true
        }else if(playerTotal == 21){
            gameOver = true
            win = true
        }else if(playerTotal > 21){
            gameOver = true
            win = false
        }else{
            win = false
        }
    }

    private fun restart() {
        finish()
        startActivity(intent);
    }

    private fun gameOver(){
        hit_btn.visibility = View.INVISIBLE
        pass_btn.visibility = View.INVISIBLE
        restart_btn.visibility = View.VISIBLE
        dealer_1.setImageResource(dealersHand[0])
        if(win){
            end_msg.text = "You win!"

        }else{
            end_msg.text = "You lose!"
        }
    }

}
