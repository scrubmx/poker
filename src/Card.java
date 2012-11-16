/**
 * @author scrub
 */
public class Card{
    private int rank, suit;

    private static String[] suits = { "s", "h", "c", "d" };
    private static String[] ranks  = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K" };
 
    Card(int rank, int suit){
        this.rank = rank;
        this.suit = suit;     
    }
    
    Card(String card){
        
        String cardRank = card.substring(0, 1);
        String cardSuit = card.substring(1, 2);
        //System.out.println(cardRank+cardSuit);

        for (int r=0; r<13; r++){
            if(cardRank.equals(ranks[r])){
                this.rank = r;
            }
        }
        
        for (int s=0; s<4; s++){
            if(cardSuit.equals(suits[s])){
                this.suit = s;
            }
        }
    }
    
    public static String rankAsString(int n){
        return ranks[n];
    }

    public @Override String toString(){
          return ranks[rank] + "" + suits[suit];
    }

    public int getRank(){
         return rank;
    }

    public int getSuit(){
        return suit;
    }
    
}