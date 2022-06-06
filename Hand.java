/*
  Represent one hand of cards in BlackJack.
  @author Tsitsi Mambo
*/

public class Hand {
    private Card[] cards;
    private int count;
    public static final int DEFAULT_SIZE = 10;
    public Hand() {
	this.cards = new Card[DEFAULT_SIZE];
	this.count = 0;

  }

    /**
       Add card to the hand.
       @param c card to add
    */
    public void add(Card c) {
	if (isFull()) {
	    resize(2 * count);
	}
	cards[count] = c;
	count++;
     
    }

    /** True iff hand is full. */
    public boolean isFull() {
	return count == cards.length;
    }

    private void resize(int newsize) {
	if (newsize <= count) return;
	Card[] newhand = new Card[newsize];
	for (int i = 0; i < count; i++) {
	    newhand[i] = this.cards[i];
	}
	this.cards = newhand;
    }
    /**
       Determine the best way to evaluate this hand, yielding the greatest
       value less that 21.

       @return maximum value of this hand that is <= 21, assuming aces
       are 1 or 10.
    */
    public int handValue() {
    int ace = 0;
    int sum = 0; //sum begins at 0
   // System.out.println(count);
    for (int i = 0; i < count; i++){ //iterating through count   
          if (cards[i].value()==1){ //if the cards value is 1
        ace++; //increment the ace, because there is an ace
    }else if(cards[i].value()>10){ //otherwise if its greater than 10
      sum+=10;//add 10 to the sum of the hand (accounts for face cards)
    }else sum+=cards[i].value(); // and at the end you are adding the value of the cards to the sum
    }

    for (int i = 0; i<ace; i++){ //when iterating through all the aces
      if(sum > 10){ //if the sum is greater than 10 since two aces bust
        sum+=1;//increment the sum this means the ace is valued at one and not 11
      }else {
        sum+=11; //add 11 to the sum an so the ace counts as 11
      }
    }
      
      return sum ;
    }
}








