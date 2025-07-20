package artAuction;

import java.io.Serializable;

/**
 * Work contains all the information about a User, like the login, name, age and email.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */
interface Work extends Serializable, WorkInformation {

    /**
     * Increments the number of works in auction that the artist has.
     */
    void increaseNrAuctions();

    /**
     * Decreases the number of works in auction that the artist has.
     */
    void decreaseNrAuctions();

    /**
     * Sets the highest price achieved by this work in auction.
     * @param finalPrice The final price achieved in auction.
     */
    void setHighestPrice(int finalPrice);
}
