package blackjack;
public class Player {
    private final String Name;
    private int score = 0;
    private int index = 0;
    private final Card[] cards = new Card[11];
    private boolean isBlackJack;
    private boolean lost;

    public Card[] getCards() {
        return cards;
    }

    public Player(String Name) {
        this.Name = Name;
    }
    public String getName()
    {
        return Name;
    }

    public int getScore()
    {
        return score;
    }

    public boolean getIsBlackJack() {
        return isBlackJack;
    }

    public boolean getLost()
    {
        return lost;
    }
    
    public void is_Black_Jack_or_Lost()
    {
        if(score == 21)
            isBlackJack = true;
        else if(score > 21)
            lost = true;
    }
      
    public void hit(Card card)
    {
        cards[index] = card;
        score = score + cards[index].getValue();
        index++;
    }

    public int getIndex() {
        return index;
    }
    
    public Card getLastCard(){
        return cards[index-1];
    }
}
