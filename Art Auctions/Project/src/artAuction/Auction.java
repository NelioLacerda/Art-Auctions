package artAuction;

import artAuction.exceptions.BelowPriceException;
import artAuction.exceptions.NoBidsException;
import artAuction.exceptions.NonexistentWorkInAuctionException;
import dataStructures.Iterator;
import dataStructures.List;

import java.io.Serializable;

/**
 * Auction contains all the methods about an auction, like remove and add works, as well as bids.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

interface Auction extends Serializable {

    /**
     * Adds a new work to the auction.
     * @param work work.
     * @param price work price.
     */
    void addWork(Work work, int price);

    /**
     * Adds a new bid to a work in the auction.
     * @param idWork work ID.
     * @param amount valor of the bid.
     * @param user buyer.
     * @throws NonexistentWorkInAuctionException Work identifier doesn't exist in the auction.
     * @throws BelowPriceException Proposed value below the minimum value.
     */
    void addBid(String idWork, int amount, User user) throws NonexistentWorkInAuctionException,
            BelowPriceException;

    /**
     * Returns a list with all the works that were in auction.
     * @return final list of works in auction.
     */
    Iterator<WorkInAuction> close();

    /**
     * Lists all the bids made to a specific work.
     * @param idWork work ID.
     * @return List of all work bids.
     * @throws NonexistentWorkInAuctionException Work identifier doesn't exist in the auction.
     * @throws NoBidsException Work has no bids in auction.
     */
    Iterator<BidInformation> listBidsWork(String idWork) throws NonexistentWorkInAuctionException, NoBidsException;

    /**
     * Lists all works in the auction.
     * @return List of all works in the auction.
     */
    Iterator<WorkInAuctionInformation> listWorks();
}
