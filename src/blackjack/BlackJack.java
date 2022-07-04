package blackjack;
import java.util.Scanner;

/*

// if nullPointerException happened
// Please run the code again until it runs well.
// I didn't find any solution for this exception but it sometimes runs well. 

*/

public class BlackJack {
    static Scanner sc = new Scanner(System.in);
    static Game game = new Game();
    static GUI gui = new GUI();
    static boolean flag = false;

    public static void main(String[] args) {
        game.cardsDeck();
        initializing();
        gui.runGUI( game.getGCards(), game.getPlayers(0).getCards(), game.getPlayers(1).getCards(), game.getPlayers(2).getCards(), game.getPlayers(3).getCards());
        playing();
        game.updateMax();
        if(game.isPushCase())
            System.out.println("Its's a PUSH case between Plyer " + game.getPushPlayer1().getName() + " and " + game.getPushPlayer2().getName());
        dealerPlay();
    }
    
    public static void initializing()
    {       
        for(int i = 0; i < 4; i++)
        {
            if(i < 3)
            {
                System.out.print("Player " + (i+1) + " name:  ");
                String name = sc.nextLine();
                game.setPlayers(name, i);
            }
            else if(i == 3)
            {
                game.setDealer();
            }
        }
    }
    
    public static void playing()
    {
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Player " + game.getPlayers(i).getName() + ", You have "+ game.getPlayers(i).getIndex() + " cards and Your score is " + game.getPlayers(i).getScore());
            System.out.println("Hit(press 1) or Stand(press 2)? \n");
            int hitOrStand = sc.nextInt();
            OUTER:
            while (hitOrStand == 1)
            {
                int j = game.Play(i);
                gui.updatePlayerHand(game.getPlayers(i).getLastCard() , i);

                switch (j) 
                {
                    case 0:
                        System.out.println(game.getPlayers(i).getName() + "'s score is:  " + game.getPlayers(i).getScore() + ", So he is BUSTED!"); 
                        break OUTER;
                    case 1:
                        System.out.println(game.getPlayers(i).getName() + "'s score is:  " + game.getPlayers(i).getScore() + ", So he has BLACKJACK!");
                        break OUTER;
                    case 2:
                        System.out.println("Player " + game.getPlayers(i).getName() + ", You have "+ game.getPlayers(i).getIndex() + " cards and Your score is " + game.getPlayers(i).getScore());
                        System.out.println("Hit(press 1) or Stand(press 2)? \n");
                        hitOrStand = sc.nextInt();
                }
            }
        }
    }
    
    public static void dealerPlay()
    {
        if(game.updateMax() != null)
        {
            while(true)
            {
                if(game.dealerScore() > game.getMax())
                {
                    flag = true;
                    break;
                }
                else if((game.dealerScore() == game.getMax()) && (game.getMax() == 21))
                {
                    flag = true;
                    break;
                }
                else
                {
                    game.Play(3);
                    gui.updateDealerHand(game.getPlayers(3).getLastCard(), game.getGCards());
                }
            }
            
            if((flag == true) && (game.dealerScore() <= 21) && (game.dealerScore() != game.getMax())){
                if(game.dealerScore() == 21)
                    System.out.println("The Dealer has BLACKJACK! \n The Dealer WINS!");
                else
                    System.out.println("The Dealer WINS!");
            }
            else if((flag == true) && (game.dealerScore() == game.getMax()))
            {
                System.out.println("The Dealer also has BLACKJACK! \n So PUSH!");
            }
            else
                System.out.println("The Dealer is BUSTED! \nThe player " + game.updateMax().getName() + " WINS!");
        }
        else
        {
            System.out.println("The Dealer WINS! \n");

        }
    }
}
