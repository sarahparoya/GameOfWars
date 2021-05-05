public class Card
{
    private int rank;
    private int suit;

    public Card(int suit, int rank)
    {
        this.suit = suit;
        this.rank = rank;
    }

    public int returnCard()
    {
        return rank;
    }

    @Override
    public String toString()
    {
        //Combining ranks and suits together
        StringBuilder actualCard = new StringBuilder();

        //Assigning Jack, Queen, King, and Ace their numerical values
        switch(rank)
        {
            case 11:
                actualCard.append("Jack");
                break;
            case 12:
                actualCard.append("Queen");
                break;
            case 13:
                actualCard.append("King");
                break;
            case 14:
                actualCard.append("Ace");
                break;
            default:
                actualCard.append(rank);
                break;
        }
        actualCard.append(" of ");

        switch(suit) {
            case 0:
                actualCard.append("♠Spades♠");
                break;
            case 1:
                actualCard.append("♥Hearts♥");
                break;
            case 2:
                actualCard.append("♣Clubs♣");
                break;
            case 3:
                actualCard.append("♦Diamonds♦");
                break;
            default:
                break;
        }
        return actualCard.toString();
    }
}