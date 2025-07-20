package artAuction;

/**
 * Interface providing information about a work in an auction.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

public interface WorkInAuctionInformation {

    /**
     * Returns the original work.
     * @return Original work.
     */
    WorkInformation getOriginalWork();

    /**
     * Gets the sold price of the work in the auction.
     * @return The sold price of the work.
     */
    int getSoldPrice();

    /**
     * Returns the buyer login.
     * @return Buyer login.
     */
    String getBuyerLogin();

    /**
     * Returns the buyer name.
     * @return Buyer name.
     */
    String getBuyerName();
}
