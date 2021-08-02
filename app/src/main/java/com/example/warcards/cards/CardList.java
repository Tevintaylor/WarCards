package com.example.warcards.cards;

import com.example.warcards.R;

public class CardList {
    public static Cards[] getCards() {
        return CARDS;
    }

    public static final Cards CARD_TWO = new Cards("card2",R.drawable.card2,2);
    public static final Cards CARD_THREE = new Cards("card3",R.drawable.card3,3);
    public static final Cards CARD_FOUR = new Cards("card4",R.drawable.card4,4);
    public static final Cards CARD_FIVE = new Cards("card5",R.drawable.card5,5);
    public static final Cards CARD_SIX = new Cards("card6",R.drawable.card6,6);
    public static final Cards CARD_SEVEN = new Cards("card7",R.drawable.card7,7);
    public static final Cards CARD_EIGHT = new Cards("card8",R.drawable.card8,8);
    public static final Cards CARD_NINE = new Cards("card9",R.drawable.card9,9);
    public static final Cards CARD_TEN = new Cards("card10",R.drawable.card10,10);
    public static final Cards CARD_ELEVEN = new Cards("card11",R.drawable.card11,11);
    public static final Cards CARD_TWELVE = new Cards("card12",R.drawable.card12,12);
    public static final Cards CARD_THIRTEEN = new Cards("card13",R.drawable.card13,13);
    public static final Cards CARD_FOURTEEN = new Cards("card14",R.drawable.card14,14);

    public static final Cards[] CARDS = {CARD_TWO, CARD_THREE, CARD_FOUR, CARD_FIVE,
            CARD_SIX, CARD_SEVEN, CARD_EIGHT, CARD_NINE, CARD_TEN, CARD_ELEVEN, CARD_TWELVE, CARD_THIRTEEN, CARD_FOURTEEN};
}

