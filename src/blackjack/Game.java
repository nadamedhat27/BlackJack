package blackjack;
import java.util.Random;

public class Game
{
    private final Player[] players = new Player[4];
    private final static Card[] cards = new Card[52];
    private int max = 0;   
    private Player pushPlayer1; 
    private Player pushPlayer2; 
    private boolean pushCase = false;
    private static int counter = 0;
    private int randomSelection;
    
    public void cardsDeck()
    {
        for(int i = 0;i < 4;i++)
        {
            for(int j = 0;j < 13;j++)
            {
                if(j >= 10)
                    cards[counter] = new Card(i,j,10);
                else
                    cards[counter] = new Card(i,j,j+1);
                
                counter++;
            }
        }
    }
    
    public Card randomDraw()
    {
        Card card;
        while(true)
        {
            Random rand = new Random();
            randomSelection = rand.nextInt(52);
            card = new Card(cards[randomSelection]);
            if(cards[randomSelection] != null) 
            {
                cards[randomSelection] = null;
                return card;
            }           
        }
    }
    
    public void setPlayers(String name,int i){
        Card firstCard;
        Card secondCard;
        if(i < 3)
        {
            players[i] = new Player(name);
            firstCard = new Card(this.randomDraw());
            players[i].hit(firstCard);
            secondCard = new Card(this.randomDraw());
            players[i].hit(secondCard);
        }
        
    }
    
    public void setDealer()
    {   
        Card firstCard;
        Card secondCard;
        players[3] = new Player("D");
        firstCard = new Card(this.randomDraw());
        players[3].hit(firstCard);
        secondCard = new Card(this.randomDraw());
        players[3].hit(secondCard);
    }
    
    public Player updateMax()
    {
        Player maxPlayer = null;
        
        if(players[0].getScore() > players[1].getScore() && players[0].getScore() > players[2].getScore())
        {
            if(players[0].getLost())
            {
                if(players[1].getScore() > players[2].getScore())
                {
                    maxPlayer = players[1];
                    max = players[1].getScore();
                    return maxPlayer;
                }
                else
                {
                    maxPlayer = players[2];
                    max = players[2].getScore();
                    return maxPlayer;
                }
            }
            else
            {
                maxPlayer = players[0];
                max = players[0].getScore();
                return maxPlayer;
            }
        }
        else if(players[1].getScore() > players[2].getScore() && players[1].getScore() > players[0].getScore())
        {
            if(players[1].getLost())
            {
                if(players[2].getScore() > players[0].getScore())
                {
                    maxPlayer = players[2];
                    max = players[2].getScore();
                    return maxPlayer;
                }
                else
                {
                    maxPlayer = players[0];
                    max = players[0].getScore();
                    return maxPlayer;
                }
            }
            else
            {
                maxPlayer = players[1];
                max = players[1].getScore();
                return maxPlayer;
            }
        }  
        else if(players[2].getScore() > players[0].getScore() && players[2].getScore() > players[1].getScore())
        {
            if(players[2].getLost())
            {
                if(players[0].getScore() > players[1].getScore())
                {
                    maxPlayer = players[0];
                    max = players[0].getScore();
                    return maxPlayer;
                }
                else
                {
                    maxPlayer = players[1];
                    max = players[1].getScore();
                    return maxPlayer;
                }
            }
            else
            {
                maxPlayer = players[2];
                max = players[2].getScore();
                return maxPlayer;
            }
        }  
        else if(players[0].getScore() == players[1].getScore())
        {
            pushPlayer1 = players[0];
            pushPlayer2 = players[1];
            pushCase = true;
            if(!players[2].getLost())
            {
                maxPlayer = players[2];
                max = players[2].getScore();
                return maxPlayer;
            }
            else
            {
                max = 0;
                return maxPlayer;
            }
        }
        else if(players[1].getScore() == players[2].getScore())
        {
            pushPlayer1 = players[1];
            pushPlayer2 = players[2];
            pushCase = true;
            if(!players[0].getLost())
            {
                maxPlayer = players[0];
                max = players[0].getScore();
                return maxPlayer;
            }
            else
            {
                max = 0;
                return maxPlayer;
            }
        }
        else if(players[0].getScore() == players[2].getScore())
        {
            pushPlayer1 = players[0];
            pushPlayer2 = players[2];
            pushCase = true;
           if(!players[1].getLost())
            {
                maxPlayer = players[1];
                max = players[1].getScore();
                return maxPlayer;
            }
            else
            {
                max = 0;
                return maxPlayer;
            }
        }
        else
        {
            max = 0;
            return maxPlayer;
        }
    }

    public int getMax() {
        return max;
    }

    public int Play(int indexOfPlayer){
        if(indexOfPlayer < 3){
            players[indexOfPlayer].hit(this.randomDraw());
            players[indexOfPlayer].is_Black_Jack_or_Lost();
            if(players[indexOfPlayer].getLost())
                return 0;
            else if(players[indexOfPlayer].getIsBlackJack())
                return 1;
            else
                return 2;
        }
        else
        {
            players[indexOfPlayer].hit(this.randomDraw());
        }
        return -1;
    }
    
    public int dealerScore()
    {
        return players[3].getScore();
    }

    public Player getPushPlayer1() {
        return pushPlayer1;
    }

    public Player getPushPlayer2() {
        return pushPlayer2;
    }

    public boolean isPushCase() {
        return pushCase;
    }

    public Card[] getGCards() {
        return cards;
    }

    public Player getPlayers(int i) {
        return players[i];
    }
   
}