/**
 * Represents a standard deck of 52 Card objects.
 * @author S. Anderson
 * @version Feb 2014
   revised Sep 2019

   Worked in Collaboration with Thalia
   
 */

import java.util.* ;

public class Deck{

  private Stack<Card> deck; //create a stack deck and discard of class Card
  private Stack<Card> discard;
  private static Random rand; // to generate random nums.
  Stack <Card> pile1; //creates two empty stacks of cards
  Stack <Card> pile2;
  int deckSize;
    /**
       Create a new 52-card deck that is shuffled.
    */
  
  public Deck () {
    rand = new Random(); //initializes rand
    deck =new Stack<Card>(); //initializes deck and discard
    discard =new Stack<Card>();
    // XX don't need these as instance vars!  you have parameters to do this.
    pile1= new Stack<Card>();
    pile2= new Stack<Card>();
    
    for (Card.Suit s : Card.Suit.values()) { //loops through the different suits
      for (int v = Card.MINVAL; v <= Card.MAXVAL; v++) { //loops through the different card values
        deck.push(new Card(s,v)); //pushes new cards onto deck
      
      }
    }
    deckSize = deck.size();
  reset(); //resets deck
  }

    /**
      Return deck to a new fully shuffled state with 52 cards.
    */
	public void reset() {
    for (int i = 0;i < discard.size();i++ ){ //not sure if necessary, it is necessary
    	while (discard.empty()==false){
      deck.push(discard.pop()); //pushing the popped discard card onto deck
    }
 	 }
   shuffle(); 

  }

    /**
       @return true iff the deck is empty.
    */
  public boolean empty() {

    return deck.empty(); //makes it empty

  }

    /**
       Deals one card from top of the deck.  Card is removed.
       Will raise exception if called on an empty deck!!

       @return next card from top of deck
       @throws RuntimeException if the deck is empty
    */     
  public Card dealCard() throws NoSuchElementException {
    if (empty())
      throw new NoSuchElementException("Too many cards dealt.");
    discard.push(deck.pop()); //pushes the popped deck card onto discard stack
 
    return discard.peek(); //looks at the top of the discard pile

  }

  private void split(Stack<Card> allcards,Stack<Card> pile1,Stack<Card> pile2){
     int half = allcards.size()/2; //creates an int half which is half the size in deck

     for(int i = 0; i < half; i++){
        pile1.push(allcards.pop()); //push half the deck into pile1
     }
     // XX no randomness is problem for simulation (-2)
     for(int i = 0; i < half; i++){
        pile2.push(allcards.pop()); //push the other half of the deck into pile2
     }

   }

  private void riffle(Stack<Card> allcards,Stack<Card> pile1,Stack<Card> pile2){

    
  while(pile1.empty() == false && pile2.empty() == false){ //checks to see if pile1 AND pile2 are empty "Look before you Leap"

      allcards.push(pile1.pop()); //pile1 is pushed onto allcards
  
      allcards.push(pile2.pop()); //pile2 is pushed onto allcards
      
        
    }
  }

  private void shuffle(Stack<Card> allcards){
    int nshuffle = rand.nextInt(7); //create int nshuffle 

    for(int i = 0; i<=nshuffle; i++){ //loops through 
      this.split(allcards,pile1,pile2); //splits the allcards first
      this.riffle(allcards,pile1,pile2);//then riffles them
    }

  }
  public void shuffle(){
    shuffle(this.deck); //calls the private shuffle method
  }

    /**
       @return string equivalent of entire deck.
    */
  public String toString() {
    return deck.toString(); //return toString
  }


    /**
       @param c card to look for.
       @return true iff card is in the deck.
    */
    public boolean inDeck(Card c) {
        Iterator <Card> iterator= deck.iterator();
        while(iterator.hasNext()){
        Card x = iterator.next();	
          for(int i=0;i<=deck.size();i++){ //increment i as long as its less than the size of the deck
            if(c.value() == x.value() && c.suit()==x.suit()){ //check if the suit and value of card c is equal to card x
              return true; //return true
              
            }
          }
        }
     return false; //if iteratoration does not have next element return false
    }

} // end of class Deck
