package artAuction;

public class BidClass implements Bid {

       /**
        * Serial Version UID of the Class
        */
       static final long serialVersionUID = 0L;

       // Instance variables:

       /**
        * User that as made the bid (buyer).
        */
       private final User user;

       /**
        * The bid amount.
        */
       private final int amount;

       /**
        * Constructor of Bid class.
        * @param user user that as made the bid (buyer).
        * @param amount bid amount.
        */
       public BidClass(User user, int amount) {
              this.user = user;
              this.amount = amount;
       }

       @Override
       public int getAmount() {
              return amount;
       }

       @Override
       public User getBuyer() {
              return user;
       }

       @Override
       public String getBuyerLogin() {
              return user.getLogin();
       }

       @Override
       public String getBuyerName() {
              return user.getName();
       }
}
