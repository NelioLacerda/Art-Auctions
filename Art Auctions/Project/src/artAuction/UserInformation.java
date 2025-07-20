package artAuction;

import java.io.Serializable;

/**
 * UserInformation contains all the information about a User, like the login, name, age and email.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

public interface UserInformation extends Serializable {

    /**
     * Returns the user login.
     * @return user login.
     */
    String getLogin();

    /**
     * Returns the user's name.
     * @return User's name.
     */
    String getName();

    /**
     * Returns the user age.
     * @return User age.
     */
    int getAge();

    /**
     * Returns the user email.
     * @return user email.
     */
    String getEmail();

    /**
     * Verifies if the user has bids submitted in auction.
     * @return true if the user has bids submitted, false otherwise.
     */
    boolean isInAuction();
}
