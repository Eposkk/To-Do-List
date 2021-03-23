package cardgame;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;

class DeckOfCardsTest {

    @Test
    void dealHand() {
        DeckOfCards deckOfCards = new DeckOfCards();
        ArrayList<PlayingCard> deck = deckOfCards.dealHand(10);
        HashSet<PlayingCard> deckSet=new HashSet<>(deck);
        assert(deck.size()==10);
        assert(deckSet.size()==deck.size());
    }
}