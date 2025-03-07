package com.example.project;
import java.util.ArrayList;



public class Game{
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards) {
        //generates and stores the hand rankings of each hand
        int p1Ranking = Utility.getHandRanking(p1Hand);
        int p2Ranking = Utility.getHandRanking(p2Hand);
        
        //compares the rankings to see which one is stronger 

        if (p1Ranking > p2Ranking) {
            return "Player 1 wins!";
        } 
        if (p1Ranking < p2Ranking) {
            return "Player 2 wins!";
        }
    
        // If hands have equal ranking, compare the highest card
        // both cards are compared in their own hands to see which one is higher
        int p1HighCard;
        int p2HighCard;
        if (Utility.getRankValue(p1.getHand().get(0).getRank()) > Utility.getRankValue(p1.getHand().get(1).getRank())) {
            p1HighCard = Utility.getRankValue(p1.getHand().get(0).getRank());
        } else {
            p1HighCard = Utility.getRankValue(p1.getHand().get(1).getRank());
        }

        if (Utility.getRankValue(p2.getHand().get(0).getRank()) > Utility.getRankValue(p2.getHand().get(1).getRank())) {
            p2HighCard = Utility.getRankValue(p2.getHand().get(0).getRank());
        } else {
            p2HighCard = Utility.getRankValue(p2.getHand().get(1).getRank());
        }
        // compares the high cards
        if (p1HighCard > p2HighCard) {
            return "Player 1 wins!";
        } 
        if (p1HighCard < p2HighCard) {
            return "Player 2 wins!";
        } 
        // if both players don't have a high card or have the same high card and the same rank then it's a tie
        return "Tie!";
    }
    
    

    public static void play(){ //simulate card playing
    
    }
        
        

}