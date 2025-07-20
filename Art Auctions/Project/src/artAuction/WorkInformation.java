package artAuction;

import java.io.Serializable;

/**
 * WorkInformation contains all the work information, like ID, name, year, etc...
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

public interface WorkInformation extends Serializable, Comparable<WorkInformation> {

    /**
     * Returns the work ID.
     * @return work ID.
     */
    String getID();

    /**
     * Returns the work's name.
     * @return Work's name
     */
    String getName();

    /**
     * Returns the artist name.
     * @return Artist name.
     */
    String getArtistName();

    /**
     * Returns the artist login.
     * @return Artist login.
     */
    String getArtistLogin();

    /**
     * Returns the year when the work was made.
     * @return Year when the work was made.
     */
    int getYear();

    /**
     * Returns the highest price of the work for which it was sold.
     * @return Highest price of the work.
     */
    int getHighestSalePrice();
}
