package cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

    private final char[] suit={'S','H','D','C'};
    private ArrayList<PlayingCard> cards;

    public DeckOfCards (){
        cards=new ArrayList<PlayingCard>();
        for(int i=0;i<4;i++){
            for (int j=0;j<13;j++){
                cards.add(new PlayingCard(suit[i],j+1));
            }
        }
    }

    public ArrayList<PlayingCard> getCards() {
        return cards;
    }

    public ArrayList<PlayingCard> dealHand(int n){
        ArrayList<PlayingCard> hand = new ArrayList<>();
        Random rand = new Random();
        int r;
        while(hand.size()<n){
            r= rand.nextInt(51);
            if(!hand.contains(cards.get(r))){
                  hand.add(cards.get(r));
            }
        }
        return hand;
    }
}
