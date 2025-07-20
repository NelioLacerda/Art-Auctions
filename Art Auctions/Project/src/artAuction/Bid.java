package artAuction;

import java.io.Serializable;

/**
 * Bid contains the information about a bid, like the amount and the buyer.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */
public interface Bid extends Serializable, BidInformation{

    /**
     * Returns the buyer (User).
     * @return buyer.
     */
    User getBuyer();

}
