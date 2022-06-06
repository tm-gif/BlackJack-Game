/**
   This class represents a single card.  
   It has 4 suits and 13 values.

   @author S. Anderson
   @version 1 written Aug 2011, modified Feb 2014.
   @version 2 written Sep 2019, switched to enum types for suits.
*/

public class Card {

    /** suits */
    enum Suit {SPADES, HEARTS, CLUBS, DIAMONDS};

    /** number of different suits */
    public static final int NSUITS = Suit.values().length; 

    public static final int MINVAL = 1;
    public static final int MAXVAL = 13; 
    /** number of different values */
    public static final int NVALUES = MAXVAL - MINVAL + 1;
    public static final int ACE = MINVAL;

    // instance variables
    private Suit suit ; // the suit of the card
    private int value ; // the value of the card (MINVAL to MAXVAL, inclusive)
    
    /**
     * Creates a default instance of a Card, an Ace of Spaces.
     */
    public Card()
    {
	this(Suit.SPADES, ACE);
    }

    /**
       Creates a card with specified suit and value.
       @param thesuit suit of the card (use the constants provided!!)
       @param thevalue raw value of the card (1 to 13 for ace to king)
       @throws exception if values are invalid.
    */

    public Card(Suit thesuit, int thevalue) throws RuntimeException {
	if (thevalue < MINVAL || thevalue > MAXVAL) {
	    throw new IllegalArgumentException("Bad card value: " +
				       thesuit + " " +
				       thevalue);
	}
	this.suit = thesuit;
	this.value = thevalue;
    }


    /**
       Get the suit.
       @return the suit 
   */
    public Suit suit() { return suit; }    
    /**
       Get the value.
       @return the value
    */
    public int value() { return value; }

    /**
     * returns the suit attribute of the card as a string
     * @return the suit attribute of the card as a string
     */
    public String getSuitString()
    {
	return suit.toString();
    }

    /**
     * returns the value attribute of the card as a string
     * @return the value attribute of the card as a string
     */
    public String getValueString()
    {
	final String[] names = 
	    {"ace","two","three","four","five","six","seven",
	     "eight","nine","ten","jack","queen","king"};
	return names[value - MINVAL];
    }

    public boolean equals(Card c) {
	return this.suit == c.suit && this.value == c.value;
    }

    public String toString() {
	return "(" + getValueString() + ", " + getSuitString() + ")";
    }

    // to print debugging statements
    private static void p(String s) {
	System.out.println(s);
    }
    /**
       Test Card class.
    */
    public static void main(String[] args ) {

	Card c = new Card(Suit.CLUBS,1);
	p("OK. Card should be ace of clubs: " + c);

	c = new Card(Suit.SPADES,13);
	p("OK. Card should be king of clubs: " + c);	

	try {
	    c = new Card(Suit.SPADES,14);
	    p("FAIL. Fails to correctly raise exception for invalid card value.");
	} catch (IllegalArgumentException ex) {
	    p("OK. Correctly raises exception for invalid card value.");
	    p("\tMessage: " + ex.getMessage());
	}

    }

}
