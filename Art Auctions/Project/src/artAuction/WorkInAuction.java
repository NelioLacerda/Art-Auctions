package artAuction;

import artAuction.exceptions.BelowPriceException;
import artAuction.exceptions.NoBidsException;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * WorkInAuction contains all the methods about the work, like addBids.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

interface WorkInAuction extends Serializable, WorkInAuctionInformation {

    /**
     * Adds a nwe bid to the work.
     * @param buyer buyer.
     * @param amount Valor of the bid.
     * @throws BelowPriceException Proposed value below the minimum value.
     */
    void addNewBid(User buyer, int amount) throws BelowPriceException;

    /**
     * Sets the highest price achieved by this work.
     */
    void setHighestPrice();

    /**
     * If the work was sold, it will choose the winning bid and eliminate all other bids made.
     */
    boolean closeBids();

    /**
     * List all the bids made.
     * @return List of bids.
     * @throws NoBidsException No bids made.
     */
    Iterator<BidInformation> listBids() throws NoBidsException;
}
