package artAuction;

import dataStructures.Iterator;

/**
 * Artist contains all the methods of the artist, allowing to add new works, remove works, and list its
 * own works.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */
interface Artist extends User, ArtistInformation {

    /**
     * Checks if this artist has works.
     * @return true if there are works associated with this artist, false otherwise.
     */
    boolean hasWorks();

    /**
     * Adds a new work to the artist.
     * @param work work.
     * @param name work name.
     */
    void addNewWork(String name, WorkInformation work);

    /**
     * Increments the number of works that the artist has in auctions.
     */
    void increaseNrWorksInAuction();

    /**
     * Decreases the number of works that the author has in auctions.
     */
    void decreaseNrWorksInAuction();

    /**
     * Lists the artist works.
     * @return list of the artist works.
     */
    Iterator<WorkInformation> listOwnWork();
}
