/**
 * @author scrub
 */
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Random;

public class Hand extends Deck{
    
    private ArrayList<Card> hand;
    //private ArrayList<Card> board;
    public int hand_rank;
    
    
    Hand(){
        this.hand = new ArrayList<Card>();
        this.hand_rank = 0;
    }
    
    Hand(Hand original){
        this.hand = new ArrayList<Card>();
        for (int i=0 ; i<original.getTotalCards(); i++){
            Card card_temp = original.getCard(i);
            Card deepCopy = new Card(card_temp.getRank(), card_temp.getSuit());
            this.hand.add(deepCopy);
        }
    }

    Hand(String[] cards){
        this.hand = new ArrayList<Card>();
        Card temp;
        for(int i=0; i<cards.length; i++){
            temp = new Card(cards[i]);
            this.hand.add(temp);
            //System.out.println(temp.toString());
        }
    }
    
    @Override
    public void removeCard(Card card){
        for(int i=0; i<hand.size(); i++){
            if(card.getRank()== hand.get(i).getRank() && card.getSuit() == hand.get(i).getSuit()){
                hand.remove(i);
            }
        }
    }
      
    @Override
    public Card getCard(int index){
        return hand.get(index);
    }
    
    @Override
    public void addCard(Card c){
        this.hand.add(c);
    }
    
    public String handToString(){
          String strHand = "[ ";
          for(int i=0; i<this.hand.size(); i++){
              strHand += this.hand.get(i).toString() + " ";
          }
          strHand += "]";
          return strHand;
    }
    
    public void setHandRank(int hr){
        this.hand_rank = hr;
    }
    
    public int getHandRank(){
        return  this.hand_rank;
    }

    /*public void fillTheHand(){
        while(hand.size() < 5){
            hand.add(dealRandomCard());
        }
    }
    
    public void fillTheHand(int x){
        while(hand.size() < x){
            hand.add(dealRandomCard());
        }
    }*/
   
    @Override
    public int getTotalCards(){
        return hand.size();
    }
    
    @Override
    public Card[] toArray(){
        return (Card[])hand.toArray();  
    }
    
    public void removeIndex(int n){
        hand.remove(n);
    }
    
    /*BOARD*/
   /* public int boardSize(){
        return board.size();
    }
    
    public void addToBoard(Card c){
        board.add(c);
    }
    
    public void removeFromBoard(Card c){
        board.remove(c);
    }
    
    /****************************************
     * EVALUATE THE HAND
     */   
    public boolean isPair(){
        int card_rank;
        for(int i=0; i<4; i++){
            int index = i+1;
            card_rank = hand.get(i).getRank();
            for(int n=index; n<5; n++){
                if(card_rank == hand.get(n).getRank()){
                    return true;
                }
            }           
        }
        return false;
    }
    
    public int whichKind(){
        int card_rank;
        int count_kind = 0;
        for(int i=0; i<4; i++){
            int index = i+1;
            card_rank = hand.get(i).getRank();
            for(int n=index; n<5; n++){
                if(card_rank == hand.get(n).getRank()){
                    count_kind++;
                }
            }           
        }
        return count_kind;
    }
    
    public boolean countKind(int kind_Number){
        int card_rank;
        int count_kind = 0;
        for(int i=0; i<4; i++){
            int index = i+1;
            card_rank = hand.get(i).getRank();
            for(int n=index; n<5; n++){
                if(card_rank == hand.get(n).getRank()){
                    count_kind++;
                }
            }           
        }
        if (count_kind == kind_Number){ 
            return true; 
        }else{
            return false;
        }
    }
    
    public boolean isOnePair(){ 
        return countKind(1); 
    }
    public boolean isTwoPair(){ 
        return countKind(2); 
    }
    public boolean isThreeOfAKind(){ 
        return countKind(3); 
    }
    public boolean isFullHouse(){ 
        return countKind(4); 
    }
    public boolean isFourOfAKind(){ 
        return countKind(6); 
    }


    public boolean isColor(){
        int card_suit  = hand.get(0).getSuit();
        
        for(int i=1; i<5; i++){
            if(card_suit != hand.get(i).getSuit()){
                return false;
            }
        }           
        return true;
    }
    
    public boolean isStraight(){
        int[] rank = new int[5];
        for(int i=0; i<5; i++){
            rank[i] = hand.get(i).getRank();
        }
        Arrays.sort(rank);
        
        if(rank[0] == 0 && rank[1] == 10){
            return true;
        }else{
            for(int i=0; i<4; i++){
                if(rank[i] != rank[i+1]-1){
                    return false;
                }
            }
            return true;
        } 
    }
    
    public boolean mataAces(){
        
        int count_aces = 0;
        
        for(int i=0; i<5; i++){
            if(hand.get(i).getRank() == 0){
               count_aces++;
            }
        }
        if(count_aces > 1){
            return true;
        }else{
            return false;  
        }   
    }
    
    
    public int onePairTieBreak(Hand opponent){

        int hand_one[] = new int[5];
        int hand_two[] = new int[5];  
        int hand_one_values[] = new int[4];
        int hand_two_values[] = new int[4];

        for(int i=0; i<5; i++){
            
            if(hand.get(i).getRank() == 0){
                hand_one[i] = 14;
            }else{
                hand_one[i] = hand.get(i).getRank();
            }
            if(opponent.getCard(i).getRank() == 0){
                hand_two[i] = 14;
            }else{
                int r = (int)opponent.getCard(i).getRank();
                hand_two[i] = r;
            }
        }
        
        Arrays.sort(hand_one);
        Arrays.sort(hand_two);
        
        if(hand_one[0] == hand_one[1]){
            hand_one_values[0] = hand_one[0];
            hand_one_values[1] = hand_one[4];
            hand_one_values[2] = hand_one[3];
            hand_one_values[3] = hand_one[2];    
        }else if(hand_one[1] == hand_one[2]){
            hand_one_values[0] = hand_one[1];
            hand_one_values[1] = hand_one[4];
            hand_one_values[2] = hand_one[3];
            hand_one_values[3] = hand_one[0]; 
        }else if(hand_one[2] == hand_one[3]){
            hand_one_values[0] = hand_one[2];
            hand_one_values[1] = hand_one[4];
            hand_one_values[2] = hand_one[1];
            hand_one_values[3] = hand_one[0]; 
        }else{
            hand_one_values[0] = hand_one[3];
            hand_one_values[1] = hand_one[2];
            hand_one_values[2] = hand_one[1];
            hand_one_values[3] = hand_one[0];  
        }
        
        if(hand_two[0] == hand_two[1]){
            hand_two_values[0] = hand_two[0];
            hand_two_values[1] = hand_two[4];
            hand_two_values[2] = hand_two[3];
            hand_two_values[3] = hand_two[2];    
        }else if(hand_two[1] == hand_two[2]){
            hand_two_values[0] = hand_two[1];
            hand_two_values[1] = hand_two[4];
            hand_two_values[2] = hand_two[3];
            hand_two_values[3] = hand_two[0]; 
        }else if(hand_two[2] == hand_two[3]){
            hand_two_values[0] = hand_two[2];
            hand_two_values[1] = hand_two[4];
            hand_two_values[2] = hand_two[1];
            hand_two_values[3] = hand_two[0]; 
        }else{
            hand_two_values[0] = hand_two[3];
            hand_two_values[1] = hand_two[2];
            hand_two_values[2] = hand_two[1];
            hand_two_values[3] = hand_two[0];  
        }

  
        if(hand_one_values[0] > hand_two_values[0]){
            return 1;
        }else if(hand_one_values[0] < hand_two_values[0]){
            return 2;
        }else{ 
            if(hand_one_values[1] > hand_two_values[1]){
                return 1;
            }else if(hand_one_values[1] < hand_two_values[1]){
                return 2;
            }else{
                if(hand_one_values[2] > hand_two_values[2]){
                    return 1;
                }else if(hand_one_values[2] < hand_two_values[2]){
                    return 2;
                }else{
                    if(hand_one_values[3] > hand_two_values[3]){
                        return 1;
                    }else if(hand_one_values[3] < hand_two_values[3]){
                        return 2;
                    }else{
                        return 0;
                    }
                }
            } 
        }
    }
    
     
    
    public int twoPairTieBreak(Hand opponent){

        int hand_one[] = new int[5];
        int hand_two[] = new int[5];  
        int hand_one_values[] = new int[3];
        int hand_two_values[] = new int[3];
   
        for(int i=0; i<5; i++){
            
            if(hand.get(i).getRank() == 0){
                hand_one[i] = 14;
            }else{
                hand_one[i] = hand.get(i).getRank();
            }
            if(opponent.getCard(i).getRank() == 0){
                hand_two[i] = 14;
            }else{
                hand_two[i] = opponent.getCard(i).getRank();
            }
        }
        
        Arrays.sort(hand_one);
        Arrays.sort(hand_two);

        if(hand_one[0] == hand_one[1]){
            hand_one_values[0] = hand_one[1];
            if(hand_one[2] == hand_one[3]){
                hand_one_values[1] = hand_one[3];
                hand_one_values[2] = hand_one[4];
            }else{
                hand_one_values[1] = hand_one[4];
                hand_one_values[2] = hand_one[2];  
            }                   
        }else{
            hand_one_values[0] = hand_one[1];
            hand_one_values[1] = hand_one[3];
            hand_one_values[2] = hand_one[0];
        }
        
        if(hand_two[0] == hand_two[1]){
            hand_two_values[0] = hand_two[1];
            if(hand_two[2] == hand_two[3]){
                hand_two_values[1] = hand_two[3];
                hand_two_values[2] = hand_two[4];
            }else{
                hand_two_values[1] = hand_two[4];
                hand_two_values[2] = hand_two[2];  
            }                   
        }else{
            hand_two_values[0] = hand_two[1];
            hand_two_values[1] = hand_two[3];
            hand_two_values[2] = hand_two[0];
        }
  
        if(hand_one_values[1] > hand_two_values[1]){
            return 1;
        }else if(hand_one_values[1] < hand_two_values[1]){
            return 2;
        }else{ 
            if(hand_one_values[0] > hand_two_values[0]){
                return 1;
            }else if(hand_one_values[0] < hand_two_values[0]){
                return 2;
            }else{
                if(hand_one_values[2] > hand_two_values[2]){
                    return 1;
                }else if(hand_one_values[2] < hand_two_values[2]){
                    return 2;
                }else{
                    return 0;
                }
            } 
        }
    }

    
    public int terciaTieBreak(Hand opponent){
        
        int hand_one[] = new int[5];
        int hand_two[] = new int[5];  
        int hand_one_values[] = new int[3];
        int hand_two_values[] = new int[3];
   
        for(int i=0; i<5; i++){
            if(hand.get(i).getRank() == 0){
                hand_one[i] = 14;
            }else{
                hand_one[i] = hand.get(i).getRank();
            }
            if(opponent.getCard(i).getRank() == 0){
                hand_two[i] = 14;
            }else{
                hand_two[i] = opponent.getCard(i).getRank();
            }
        }
        
        Arrays.sort(hand_one);
        Arrays.sort(hand_two);

        if(hand_one[0] == hand_one[1]){
            hand_one_values[0] = hand_one[2];
            hand_one_values[1] = hand_one[3];
            hand_one_values[2] = hand_one[4]; 
        }else if(hand_one[1] == hand_one[2]){
            hand_one_values[0] = hand_one[2];
            hand_one_values[1] = hand_one[0];
            hand_one_values[2] = hand_one[4];
        }else{
            hand_one_values[0] = hand_one[2];
            hand_one_values[1] = hand_one[0];
            hand_one_values[2] = hand_one[1];
        }
        
        if(hand_two[0] == hand_two[1]){
            hand_two_values[0] = hand_two[2];
            hand_two_values[1] = hand_two[3];
            hand_two_values[2] = hand_two[4]; 
        }else if(hand_two[1] == hand_two[2]){
            hand_two_values[0] = hand_two[2];
            hand_two_values[1] = hand_two[0];
            hand_two_values[2] = hand_two[4];
        }else{
            hand_two_values[0] = hand_two[2];
            hand_two_values[1] = hand_two[0];
            hand_two_values[2] = hand_two[1];
        }
  
        if(hand_one_values[0] > hand_two_values[0]){
            return 1;
        }else if(hand_one_values[0] < hand_two_values[0]){
            return 2;
        }else{ 
            if(hand_one_values[2] > hand_two_values[2]){
                return 1;
            }else if(hand_one_values[2] < hand_two_values[2]){
                return 2;
            }else{
                if(hand_one_values[1] > hand_two_values[1]){
                    return 1;
                }else if(hand_one_values[1] < hand_two_values[1]){
                    return 2;
                }else{
                    return 0;
                }
            } 
        }
    }
    
    public int fullTieBreak(Hand opponent){
        
        int hand_one[] = new int[5];
        int hand_two[] = new int[5];  
        int hand_one_values[] = new int[2];
        int hand_two_values[] = new int[2];
   
        for(int i=0; i<5; i++){
            if(hand.get(i).getRank() == 0){
                hand_one[i] = 14;
            }else{
                hand_one[i] = hand.get(i).getRank();
            }
            if(opponent.getCard(i).getRank() == 0){
                hand_two[i] = 14;
            }else{
                hand_two[i] = opponent.getCard(i).getRank();
            }
        }
        
        Arrays.sort(hand_one);
        Arrays.sort(hand_two);

        if(hand_one[1] == hand_one[2]){
            hand_one_values[0] = hand_one[0];
            hand_one_values[1] = hand_one[4];
        }else{
            hand_one_values[0] = hand_one[4];
            hand_one_values[1] = hand_one[0];  
        }
        if(hand_two[1] == hand_two[2]){
            hand_two_values[0] = hand_two[0];
            hand_two_values[1] = hand_two[4];
        }else{
            hand_two_values[0] = hand_two[4];
            hand_two_values[1] = hand_two[0];  
        }

        if(hand_one_values[0] > hand_two_values[0]){
            return 1;
        }else if(hand_one_values[0] < hand_two_values[0]){
            return 2;
        }else{ 
            if(hand_one_values[1] > hand_two_values[1]){
                return 1;
            }else if(hand_one_values[1] < hand_two_values[1]){
                return 2;
            }else{
                return 0;
            } 
        }        
    }
    public int pokerTieBreak(Hand opponent){
        
        int hand_one[] = new int[5];
        int hand_two[] = new int[5];  
        int hand_one_values[] = new int[2];
        int hand_two_values[] = new int[2];
   
        for(int i=0; i<5; i++){
            if(hand.get(i).getRank() == 0){
                hand_one[i] = 14;
            }else{
                hand_one[i] = hand.get(i).getRank();
            }
            if(opponent.getCard(i).getRank() == 0){
                hand_two[i] = 14;
            }else{
                hand_two[i] = opponent.getCard(i).getRank();
            }
        }
        
        Arrays.sort(hand_one);
        Arrays.sort(hand_two);

        if(hand_one[0] == hand_one[1]){
            hand_one_values[0] = hand_one[0];
            hand_one_values[1] = hand_one[4];
        }else{
            hand_one_values[0] = hand_one[4];
            hand_one_values[1] = hand_one[0];  
        }
        if(hand_two[0] == hand_two[1]){
            hand_two_values[0] = hand_two[0];
            hand_two_values[1] = hand_two[4];
        }else{
            hand_two_values[0] = hand_two[4];
            hand_two_values[1] = hand_two[0];  
        }

        if(hand_one_values[0] > hand_two_values[0]){
            return 1;
        }else if(hand_one_values[0] < hand_two_values[0]){
            return 2;
        }else{ 
            if(hand_one_values[1] > hand_two_values[1]){
                return 1;
            }else if(hand_one_values[1] < hand_two_values[1]){
                return 2;
            }else{
                return 0;
            } 
        }    
    }
    
    public int straightTieBreak(Hand opponent){
        
        int hand_one[] = new int[5];
        int hand_two[] = new int[5];
        int hand_one_value = 0;
        int hand_two_value = 0;
        
        for(int i=0; i<5; i++){
            hand_one[i] = hand.get(i).getRank();
            hand_two[i] = opponent.getCard(i).getRank();
        }
        
        Arrays.sort(hand_one);
        Arrays.sort(hand_two);
        
        if(hand_one[0] == 0 && hand_one[1] == 10){
            hand_one_value = 14;
        }else{
            hand_one_value = hand_one[4];
        } 
        if(hand_two[0] == 0 && hand_two[1] == 10){
            hand_two_value = 14;
        }else{
            hand_two_value = hand_two[4];
        }
        
        if(hand_one_value > hand_two_value){
            return 1;
        }else if(hand_one_value < hand_two_value){
            return 2;
        }else{ 
            return 0;
        }
    }
    public int colorTieBreak(Hand opponent){
        int hand_one[] = new int[5];
        int hand_two[] = new int[5];
        //int hand_one_values[] = new int[5];
        //int hand_two_values[] = new int[5];
        
        for(int i=0; i<5; i++){
            if(hand.get(i).getRank() == 0){
                hand_one[i] = 14;
            }else{
                hand_one[i] = hand.get(i).getRank();
            }
            if(opponent.getCard(i).getRank() == 0){
                hand_two[i] = 14;
            }else{
                hand_two[i] = opponent.getCard(i).getRank();
            }
        }
        
        Arrays.sort(hand_one);
        Arrays.sort(hand_two);
        
        for(int i=0; i<5; i++){
            if(hand_one[i] > hand_two[i]){
                return 1;
            }else if(hand_one[i] < hand_two[i]){
                return 2;
            }
        }
        return 0;
    }
   
    public void deleteHand(){
        this.hand.clear();
    }
}