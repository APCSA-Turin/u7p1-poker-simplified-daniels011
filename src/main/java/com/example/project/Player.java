package com.example.project;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand; // the player's two cards or his cards
    private ArrayList<Card> allCards; // all the cards including the player's hand
    String[] suits = Utility.getSuits(); // array that contains the suits of all the cards
    String[] ranks = Utility.getRanks(); // array that contains all the card ranks from 1- 13

    public Player() {
        hand = new ArrayList<>(); // starts as an empty hand
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void addCard(Card c) {
        hand.add(c); // adds a card to the player's hand
    }

    public String playHand(ArrayList<Card> communityCards) {
        allCards = new ArrayList<>(hand); // creates a list of all the cards including the player and the community
        allCards.addAll(communityCards);
        sortAllCards(); // sorts all the cards for checking for straight

        ArrayList<Integer> rankFrequency = findRankingFrequency();
        ArrayList<Integer> suitFrequency = findSuitFrequency();

        boolean straight = false;

        // checks for a straight using the rank frequency array
        for (int i = 0; i <= 8; i++) { //can iterate through only the first 9 ranks because we need 5 consecutive ranks to make a straight
            // checks if there are 5 consecutive ranks by comparing the rankFrequency values
            if (rankFrequency.get(i) > 0 &&
                rankFrequency.get(i + 1) > 0 &&
                rankFrequency.get(i + 2) > 0 &&
                rankFrequency.get(i + 3) > 0 &&
                rankFrequency.get(i + 4) > 0) {
                straight = true;
                break; // early stopping for efficiency
            }
        }

        // Check for Royal Flush by checking for straight, flush if there are 5 of same suit, and if it's ace high
        if (isPresent(suitFrequency, 5) && straight && allCards.get(allCards.size() - 1).getRank().equals("A")) {
            return "Royal Flush";
        }

        // checks for straight flush which is like a royal flush but not ace high
        if (isPresent(suitFrequency, 5) && straight) {
            return "Straight Flush";
        }

        // checks for Four of a Kind by checking if there are 4 of the same rank
        if (isPresent(rankFrequency, 4)) {
            return "Four of a Kind";
        }

        // checks for a full house by finding a 3 of a kind and a two pair of ranks
        if (isPresent(rankFrequency, 3) && isPresent(rankFrequency, 2)) {
            return "Full House";
        }

        // checks for flush by seeing if there are 5 cards with the same suit
        if (isPresent(suitFrequency, 5)) {
            return "Flush";
        }

        // returns that its a straight after the calculation from before
        if (straight) {
            return "Straight";
        }

        // checks if there are 3 of the same rank
        if (isPresent(rankFrequency, 3)) {
            return "Three of a Kind";
        }

        // checks if there are two of the same rank 
        int pairCount = 0;
        for (int count : rankFrequency) {
            if (count == 2) pairCount++;
        }
        if (pairCount >= 2) { //checks for two pairs
            return "Two Pair";
        }

        // Check for A Pair
        if (isPresent(rankFrequency, 2)) {
            return "A Pair";
        }

        Card highCard = allCards.get(allCards.size() - 1); //gets the highest card
        boolean highCardInHand = false; // variable to track if the highest card is in the player hand and not in the comunity cards
        for (Card card : hand) {
            if (card.getRank().equals(highCard.getRank())) { // checks if the highest card is in the player hand
                highCardInHand = true; 
            }
        }

        if (highCardInHand) {
            return "High Card";
        }

        return "Nothing";
    }

    // checks if a value is present in a list since we can't use .contains
    private boolean isPresent(ArrayList<Integer> list, int value) {
        for (int count : list) {
            if (count == value) {
                return true;
            }
        }
        return false;
    }

    public void sortAllCards() {
        // sorts the cards in ascending order of rank
        for (int i = 0; i < allCards.size() - 1; i++) {
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(i).getRank()) > Utility.getRankValue(allCards.get(j).getRank())) {
                    // swaps the cards if the current card has a higher rank
                    Card temp = allCards.get(i);
                    allCards.set(i, allCards.get(j));
                    allCards.set(j, temp);
                }
            }
        }
    }
    // finds the frequency of each rank in the player's cards
    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> frequencyList = new ArrayList<>();
        for (String num : ranks) { 
            int count = 0;
            for (Card card : allCards) { // checks if the rank matches for each card
                if (card.getRank().equals(num)) {
                    count++;
                }
            }

            frequencyList.add(count);
        }

        return frequencyList;
    }

    //finds the frequency of each suit in the player's cards
    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> frequencyList = new ArrayList<>();
        for (String suit : suits) {

            int count = 0;

            for (Card card : allCards) { // for each card checks if its suit matches
                if (card.getSuit().equals(suit)) {
                    count++; 

                }
            }
            frequencyList.add(count);
        }
        return frequencyList;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
