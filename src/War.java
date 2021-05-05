import java.util.*;

public class War
{
    public static boolean playerOneWin = false;
    public static boolean playerTwoWin = false;
    static boolean noOneWin = false;

    //Keep track of how many rounds each player wins
    public static int playerOneBattles = 0;
    public static int playerTwoBattles = 0;

    public static void main(String[] args)
    {
        boolean game = true;
        Scanner scan = new Scanner(System.in);
        String input = "";

        //Creating an array list for the deck of cards
        ArrayList<Card> deck = new ArrayList<Card>();

        //Initializing the suits
        for (int x = 0; x < 4; x++)
        {
            //Initializing the ranks
            for(int y = 2; y < 15; y++)
            {
                //Adding a new card to the deck
                deck.add(new Card(x,y));
                //System.out.println(deck.toString());
            }
        }

        //Shuffling the deck
        Collections.shuffle(deck, new Random());

        //Creating two decks for each player
        LinkedList<Card> playerOneDeck = new LinkedList<Card>();
        LinkedList<Card> playerTwoDeck = new LinkedList<Card>();

        //Splitting the deck in half between the two players
        playerOneDeck.addAll(deck.subList(0, 25));
        playerTwoDeck.addAll(deck.subList(26, deck.size()));

        while(game)
        {
            System.out.println("\n");
            System.out.println("================================================");
            System.out.println("Press enter to play the next deck or x to quit:");
            input = scan.nextLine();

            System.out.println("Player One's Deck: " + playerOneDeck.toString());
            System.out.println("Player Two's Deck: " + playerTwoDeck.toString());
            System.out.println("================================================");
            System.out.println("\n");

            if(!input.equalsIgnoreCase("x")) {
                //each round, each player plays 1 card from the top of their deck
                Card playerOne = playerOneDeck.pop();
                Card playerTwo = playerTwoDeck.pop();

                System.out.println("Player One's card is: " + playerOne.toString());
                System.out.println("Player Two's card is: " + playerTwo.toString());

                //Comparing ranks, if Player One wins:
                if (playerOne.returnCard() > playerTwo.returnCard()) {
                    playerOneDeck.addLast(playerOne);
                    playerOneDeck.addLast(playerTwo);
                    System.out.println("----Player 1 wins this round----\n");
                    playerOneBattles++;
                    System.out.println("Total battles won by Player One: " + playerOneBattles);
                    System.out.println("Total battles won by Player Two: " + playerTwoBattles);
                }
                //If Player Two wins:
                else if (playerOne.returnCard() < playerTwo.returnCard()) {
                    playerTwoDeck.addLast(playerOne);
                    playerTwoDeck.addLast(playerTwo);
                    System.out.println("----Player 2 wins this round----\n");
                    playerTwoBattles++;
                    System.out.println("Total battles won by Player One: " + playerOneBattles);
                    System.out.println("Total battles won by Player Two: " + playerTwoBattles);
                }
                //WAR!!! aka a tie
                else {
                    System.out.println("The cards are equal");
                    war(playerOneDeck, playerTwoDeck, true, playerOne, playerTwo);
                }

                //Ending the game if either player loses all their cards
                if (noOneWin == true) {
                    System.out.println("----It's a tie, both players are out of cards!----");
                    System.out.println("Total battles won by Player One: " + playerOneBattles);
                    System.out.println("Total battles won by Player Two: " + playerTwoBattles);
                    game = false;
                } else if (playerOneDeck.size() == 0 || playerTwoWin == true) {
                    game = false;
                    System.out.println("----Game Over: Player Two wins, Player One is out of cards!----");
                    System.out.println("Total battles won by Player One: " + playerOneBattles);
                    System.out.println("Total battles won by Player Two: " + playerTwoBattles);
                } else if (playerTwoDeck.size() == 0 || playerOneWin == true) {
                    game = false;
                    System.out.println("----Game Over: Player One wins, Player Two is out of cards!----");
                    System.out.println("Total battles won by Player One: " + playerOneBattles);
                    System.out.println("Total battles won by Player Two: " + playerTwoBattles);
                }
              //  System.out.println("================================================");
            }
            else
            {
                System.out.println("Total battles won by Player One: " + playerOneBattles);
                System.out.println("Total battles won by Player Two: " + playerTwoBattles);
                System.out.println("Exiting game...");
             //   System.out.println("================================================");
                game = false;
            }
        }
    }

    public static void war(LinkedList<Card> deck1, LinkedList<Card> deck2, boolean game,
                           Card playerOneCard, Card playerTwoCard)
    {
        ArrayList<Card> playerOneWar = new ArrayList<Card>();
        ArrayList<Card> playerTwoWar = new ArrayList<Card>();

        //add the previously popped cards at the beginning of the round
        playerOneWar.add(playerOneCard);
        playerTwoWar.add(playerTwoCard);

        while (game == true)
        {
            System.out.println("---------------------");
            System.out.println("****WAR****");

            //If either player doesn't have enough cards war can't happen
            if (deck1.size() < 3 || deck2.size() < 3)
            {
                game = false;
                if (deck1.size() < 3 && deck2.size() < 3)
                {
                    noOneWin = true;
                }
                else if (deck1.size() < 2)
                {
                    playerTwoWin = true;
                }
                else
                {
                    playerOneWin = true;
                }
            }
            else
            {
                for (int x = 0; x < 3; x++)
                {
                    playerOneWar.add(deck1.pop());
                    playerTwoWar.add(deck2.pop());

                    System.out.println("Player One's " + (x + 1) + " War Card is: " + playerOneWar.get(playerOneWar.size()-1).toString());
                    System.out.println("Player Two's " + (x + 1) + " War Card is: " + playerTwoWar.get(playerTwoWar.size()-1).toString());
                    System.out.println();
                }
                if(playerOneWar.get(playerOneWar.size()-1).returnCard() > playerTwoWar.get(playerOneWar.size()-1).returnCard())
                {
                    deck1.addAll(playerOneWar);
                    deck1.addAll(playerTwoWar);
                    System.out.println("Player One wins this War round!");
                    System.out.println("---------------------");
                    playerOneBattles++;
                    System.out.println("Total battles won by Player One: " + playerOneBattles);
                    System.out.println("Total battles won by Player Two: " + playerTwoBattles);
                    game = false;
                }

                //If it's a tie again, repeat the process
                else if(playerOneWar.get(playerOneWar.size()-1).returnCard() == playerTwoWar.get(playerOneWar.size()-1).returnCard())
                {
                    System.out.println("The cards are equal again! WAR will happen again!");
                    game = true;
                }

                //If player two wins
                else
                {
                    deck2.addAll(playerOneWar);
                    deck2.addAll(playerTwoWar);
                    System.out.println("Player Two wins this War round!");
                    System.out.println("---------------------");
                    playerTwoBattles++;
                    System.out.println("Total battles won by Player One: " + playerOneBattles);
                    System.out.println("Total battles won by Player Two: " + playerTwoBattles);
                    game = false;
                }
            }
        }
    }
}