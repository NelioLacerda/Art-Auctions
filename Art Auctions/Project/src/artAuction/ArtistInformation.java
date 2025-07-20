package artAuction;

/**
 * ArtistInformation gives all information about the artist, like the name.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

public interface ArtistInformation extends UserInformation {

    /**
     * Returns the artistic name of the artist.
     * @return the artistic name of the artist.
     */
    String getArtisticName();

    /**
     * Verifies if the artist has any work in auction.
     * @return true if the artist has work in auction, false otherwise.
     */
    boolean hasWorkInAuction();
}
