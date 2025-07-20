package artAuction;

import java.io.Serializable;

/**
 * User contains all the methods of the user, like adding a bid and close the bids.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

interface User extends Serializable, UserInformation {

    /**
     * Adds a proposal to buy a work that the user has made.
     */
    void increaseNrBids();

    /**
     * Removes a proposal to buy a work that the user has made.
     */
    void removeBid();
}
