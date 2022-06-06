/*
  Test the Deck class.
*/
import java.util.ArrayList;

public class TestDeck {
    static int numerrors, numok;

    public static void p(String msg) {
	System.out.println(msg);
    }

    public static void pErr(String msg) {
	numerrors++;
	System.out.println("\tERROR: " + msg);
    }


    public static void pOK(String msg) {
	numok++;
	System.out.println("\tOK:   " + msg);
    }

    /** check card dealing for empty condition */
    public static void test1() {
	p("TEST1: dealing tests");
        Deck d1 = new Deck();

        for (int i = 0; i < 52; i++) {
            d1.dealCard();
        }
        if (d1.empty()) 
            pOK("deck is empty.");
        else {
            pErr("Deck should be empty.");
	}
            
        try {
            d1.dealCard();  //raise Exception
            pErr("ERROR exception was not raised.");
        } catch (Exception e) {
            pOK("Exception raised: " + e);
        }

    }

    /*
      Test dealing cards
    */
    public static void test2() {
	p("TEST2: dealing tests");
        Deck d1 = new Deck();   
        d1.reset();

        for (int i = 0; i < 51; i++) {
            d1.dealCard();
        }
        if (d1.empty())
            pErr("Deck shold not be empty.");
        else
            pOK("Deck is not empty.");
        d1.shuffle();
        d1.dealCard();  //not a problem
        if (d1.empty()) 
            pOK("Deck is empty.");
        else
            pErr("Deck should be empty.");     

    }

   /*
      Test reset after dealing cards.
    */
    public static void test3() {
	p("TEST 3: reset after dealing cards.");
        Deck d1 = new Deck();   
        d1.reset();

	try {
	    for (int i = 0; i < 20; i++) {
		d1.dealCard();
	    }
	    d1.reset();
	    for (int i = 0; i < 51; i++) {
		d1.dealCard();
	    }
	    
	    if (!d1.empty()) {
		d1.dealCard();
		if (d1.empty()) pOK("Deck is empty.");
		else pErr("Deck should be empty.");
	    }
		else
		    pErr("Deck should not be empty.");
	}
        catch (Exception e) {
	    pErr("Unexpected exception " + e);
	}

    }


    
   /*
      Test inDeck dealing cards.
    */
    public static void test4() {
	p("TEST 4: in deck and out of deck");
        Deck d1 = new Deck();   
        d1.reset();
	d1.shuffle();

	try {
	    for (int i = 0; i < 52; i++) {
		Card c1 = d1.dealCard();
		if (d1.inDeck(c1)) {
		    pErr("Card should not be in the deck.");
		    return;
		}
	    }
	    pOK("Card is not in the deck.");

	}
        catch (Exception e) {
	    pErr("Unexpected exception " + e);
	}

    }

    
   /*
      Test very basic shuffle.
    */
    public static void test5() {
	p("TEST 5: test shuffle loses no cards.");
	ArrayList<Card> lst = new ArrayList<Card>();

	try {
        Deck d1 = new Deck();   
	d1.shuffle();
	while (!d1.empty()) {
	    lst.add(d1.dealCard());
	}
	d1.reset();
	d1.shuffle();
	for (Card c : lst) {
	    if (!d1.inDeck(c)) {
		pErr("Card is not in deck.");
		return;
	    }
	}
	pOK("All cards in deck.");
	} catch (Exception e) {
	    pErr("Unexpected exception " + e);
	}
    }	

	
    
    /** Run a few tests on Deck type. */
    public static void main(String[] args) {
	numerrors = 0;
    TestDeck.test1();
    TestDeck.test2();
	TestDeck.test3();
	TestDeck.test4();
	TestDeck.test5();
	p("Num OK " + numok + "\nNum errors = " + numerrors);
    }



}
