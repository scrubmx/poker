/**
 * @author scrub
 */
import java.util.ArrayList;
import java.util.Random;

public class Deck{
    
    private ArrayList<Card> deck;
    
    Deck(Deck original){
        this.deck = new ArrayList<Card>();
        
        for ( int i=0 ; i<original.getTotalCards(); i++){
            Card card_temp = original.getCard(i);
            Card deepCopy = new Card(card_temp.getRank(), card_temp.getSuit());
            this.deck.add(deepCopy);
        }
    }

    Deck(){
         
        this.deck = new ArrayList<Card>();

        for (int suit=0; suit<4; suit++){
            for (int rank=0; rank<13; rank++){
                this.deck.add(new Card(rank,suit));
            }
        }
    }
    

    public @Override String toString(){
          String strDeck = "";
          for(int i=0; i<deck.size(); i++){
              strDeck += deck.get(i).toString(); 
          }
          return strDeck;
    }
        
    public int getTotalCards(){
        return deck.size();
    }
    
    /** Toma 100 pares de cartas y los cambia de lugar para revolver el deck. **/
    public void shuffle(){
        
        int index_1, index_2;
        Random generator = new Random();
        Card temp;
        
        for (int i=0; i<100; i++){
            index_1 = generator.nextInt(this.deck.size());
            index_2 = generator.nextInt(this.deck.size());

            temp = deck.get(index_2);
            deck.set(index_2, deck.get(index_1));
            deck.set(index_1, temp);
        }    
    }
    
    public Card dealCard(){
        return (Card)deck.remove(0);
    }
    
    public Card dealRandomCard(){
        Random generator = new Random();
        int index = generator.nextInt(this.deck.size());
        Card randomCard = deck.get(index);
        deck.remove(index);
        return randomCard;
    }
    
    public void removeCard(Card card){
        for(int i=0; i<deck.size(); i++){
            if(card.getRank()== deck.get(i).getRank() && card.getSuit() == deck.get(i).getSuit()){
                deck.remove(i);
            }
        }
    }
    
    public void removeCards(String[] c){
        Card temp;
        for(int i=0; i<c.length; i++){
            temp = new Card(c[i]);
            for(int r=0; r<deck.size(); r++){
                if(temp.getRank()== deck.get(r).getRank() && temp.getSuit() == deck.get(r).getSuit()){
                    deck.remove(r);
                }
            }
        }
    }
    
    public Card getCard(int index){
        return (Card)deck.get(index);
    }
    public void addCard(Card c){
        this.deck.add(c);
    }
    
    public boolean containsCard(Card card){
        return deck.contains(card);
    }
    
    public Card[] toArray(){
        return (Card[])deck.toArray();  
    }
    
    public void clearDeck(){
        this.deck.clear();
    }
    
}
