package artAuction;

import java.io.Serializable;

public interface BidInformation extends Serializable {

    /**
     * Returns the buyer login.
     * @return buyer login.
     */
    String getBuyerLogin();

    /**
     * Returns the buyer name.
     * @return buyer name.
     */
    String getBuyerName();

    /**
     * Returns the valor of the bid.
     * @return Valor of the bid.
     */
    int getAmount();
}
