/**
   Implements a simulation of many games of 21.  Objective is to find 
   best holding point for a player, assuming that the dealer always 
   holds at 17.

   
Hold  Dealer  Player  Player
Value Won Won Winnings
-----------------------------------------
10  5608  4392  -2432.0
11  5594  4406  -2376.0
12  5595  4405  -2380.0
13  5598  4402  -2392.0
14  5595  4405  -2380.0
15  5599  4401  -2396.0
16  5595  4405  -2380.0
17  5593  4407  -2372.0
18  5607  4393  -2428.0
19  5593  4407  -2372.0
20  5595  4405  -2380.0
21  5600  4400  -2400.0


   @author 
*/

public class Blackjack {
    public static final int BUSTVAL = 21;
    public static final int PLAYER = 1;
    public static final int DEALER = 2;
    public static final int TIE = 0;
    public static int PLAYER_HOLD = 10; 
    public static final int DEALER_HOLD = 17;



    public static void main(String[] args) {
	final int MAX_GAMES = 10000;  // number of games to play
	Deck deck = new Deck();

	double winnings = 0; // player's winnings
	int pwin = 0; // number of time player wins
	int dwin = 0; // number of times dealer wins
	double bet = 2.0; // size of player's bet //changed it from 0.0 to 2

	System.out.println("Hold\tDealer\tPlayer\tPlayer");
	System.out.println("Value\tWon\tWon\tWinnings");
	System.out.println("-----------------------------------------");
	// Loop over different player hold values.
	for (int phold = PLAYER_HOLD; phold <= BUSTVAL; phold++) {
	    pwin = dwin = 0;
	    winnings = 0;

	    // play many games
	    for (int i = 0; i < MAX_GAMES; i++) {

		double value = playGame(deck,bet,phold);
		winnings += value; // accumulate winnings of player
		if (value > 0) {
		    pwin++;
		} else if (value < 0) {
		    dwin++;
		}

	    } // many games
	    
	    System.out.println(phold + "\t" + dwin + "\t" +
			       pwin + "\t" + winnings);
	}
    }

    /**
       Play one game of blackjack with the given deck.
       The bet is taken from the player prior to play.
       @param deck the deck of cards
       @param bet the bet to place
       @param card value player holds at
       @return the winnings/loss of the player.
    */
    public static double playGame(Deck deck, double bet,int playerhold) {
        Hand player = new Hand();
        Hand dealer = new Hand();

	// Deal 2 cards to each
        for (int i = 0; i < 2; i++) {
            player.add( getCard(deck) );
            dealer.add( getCard(deck) );
        }

	// TODO rest of game!
       
        while(player.handValue() < PLAYER_HOLD){ //if the player hand value is less than the player hold value
          player.add(getCard(deck) ); //player adds cards
          if(player.handValue()>PLAYER_HOLD)//if while adding the handvalue gets to be bigger than the hold, then break
            break;
        }
        while(dealer.handValue()< DEALER_HOLD){ //if the dealer hand is less than the hold value
          dealer.add( getCard(deck) ); //add cards to the dealer
          if(dealer.handValue()>DEALER_HOLD) //if while adding the handvalue gets to be bigger than the hold, then break
            break;
	}
        if (player.handValue() <= BUSTVAL && player.handValue() > dealer.handValue()){// if player hand is not bust and greater than dealer hand value
          return bet; //player wins
        }else if (player.handValue() > BUSTVAL){ 
          return -bet; //player loses
        }

        if (player.handValue() < dealer.handValue() && dealer.handValue() <= BUSTVAL){//if player.handValue>dealer.handValue && player.handValue<21)
          return -bet;//dealer wins
        }else if (dealer.handValue() > BUSTVAL){ //if dealer.handVlaue > 21, 
          return bet;//dealer busts, player wins

        }

        if (player.handValue() == BUSTVAL){//if player.handVlaue==21
          return bet + 5;//player wins bet + 5(dollars)
        }

        if (dealer.handValue() == BUSTVAL){//else if dealer .handVlaue == 21 
          return -bet;//dealer wins     
        }

       
	      return bet; //returning bet
        }


    /**
       Get next card in the deck.  Get new shuffled deck if necessary.

       @param deck the deck of cards
       @returns the next card.
    */
    public static Card getCard(Deck deck) {
	if (deck.empty())  {
	    deck.reset();
	}
	return deck.dealCard();
    }

} // end of BlackJack class


