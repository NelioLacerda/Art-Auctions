package artAuction;

import artAuction.exceptions.*;
import dataStructures.Iterator;
import dataStructures.ValueIterator;

import java.io.Serializable;

/**
 * ArtAuction, the artAuction contains works, users and auctions
 * and can deal with all the functionalities of the project.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

public interface ArtAuction extends Serializable {

    /**
     * Register a new user (collector) in the system.
     * @param login user login.
     * @param name user name.
     * @param age user age.
     * @param email user email.
     * @throws UnderageException Non-adult user candidate.
     * @throws ExistentUserException Login already exists in the system.
     * @Pre: login != null && name != null && email != null
     */
    void addUser(String login, String name, int age, String email) throws UnderageException, ExistentUserException;

    /**
     * Register a new user (artist) in the system.
     * @param login artist login.
     * @param name artist name.
     * @param artisticName artistic name.
     * @param age artist age.
     * @param email artist email.
     * @throws UnderageException Non-adult user candidate.
     * @throws ExistentUserException Login already exists in the system.
     * @Pre: login != null && name != null && artisticName != null && email != null
     */
    void addArtist(String login, String name, String artisticName, int age, String email)
            throws UnderageException, ExistentUserException;

    /**
     * Removes a user (collector or artist) from the system.
     * @param login user login.
     * @throws NonexistentUserException Login doesn't exist in the system.
     * @throws UserInAuctionException User has submitted proposals.
     * @throws ArtistInAuctionException Artist has works in auction.
     * @Pre: login != null
     */
    void removeUser(String login) throws NonexistentUserException, UserInAuctionException, ArtistInAuctionException;

    /**
     * Register a new work of art in the system.
     * @param id work id.
     * @param loginAuthor author login.
     * @param year year.
     * @param name work name.
     * @throws ExistentWorkException Work identifier already exists in the system.
     * @throws NonexistentUserException Login doesn't exist in the system.
     * @throws NotAnArtistException Author login doesn't refer to an artist.
     * @Pre: id != null && loginAuthor != null && name != null
     */
    void addWork(String id, String loginAuthor, int year, String name)
            throws ExistentWorkException, NonexistentUserException, NotAnArtistException;

    /**
     * Provides generic data for a given user.
     * @param login user login.
     * @return interface that gives the information about the user.
     * @throws NonexistentUserException Login doesn't exist in the system.
     * @Pre: login != null
     */
    UserInformation infoUser(String login) throws NonexistentUserException;

    /**
     * Provides generic data for a given artist.
     * @param login artist login.
     * @return interface that gives the information about the artist.
     * @throws NonexistentUserException  Login doesn't exist in the system.
     * @throws NotAnArtistException Author login doesn't refer to an artist.
     * @Pre: login != null
     */
    ArtistInformation infoArtist(String login) throws NonexistentUserException, NotAnArtistException;

    /**
     * Provides generic data for a given work.
     * @param id work id.
     * @return interface that gives the information about the work.
     * @throws NonexistentWorkException Work identifier doesn't exist in the system.
     * @Pre: id != null
     */
    WorkInformation infoWork(String id) throws NonexistentWorkException;

    /**
     * Creates a new auction.
     * @param id action id.
     * @throws ExistentAuctionException Work identifier already exists in the system.
     * @Pre: id != null
     */
    void createAuction(String id) throws ExistentAuctionException;

    /**
     * Add a work of art to an auction.
     * @param idAuction auction id.
     * @param idWork work id.
     * @param minPrice minimum price.
     * @throws NonexistentAuctionException Auction identifier doesn't exist in the system.
     * @throws NonexistentWorkException Work identifier doesn't exist in the system.
     * @Pre: idAuction != null && idWork != null
     */
    void addWorkAuction(String idAuction, String idWork, int minPrice)
            throws NonexistentAuctionException, NonexistentWorkException;

    /**
     * Make a bid to purchase a work of art at an auction.
     * @param idAuction auction id.
     * @param idWork work id.
     * @param login buyer login.
     * @param amount purchase price.
     * @throws NonexistentUserException Login doesn't exist in the system.
     * @throws NonexistentAuctionException Auction identifier doesn't exist in the system.
     * @throws NonexistentWorkInAuctionException Work identifier doesn't exist in the auction.
     * @throws BelowPriceException Proposed value below the minimum value.
     * @Pre: idAuction != null && idWork != null && login != null
     */
    void bid(String idAuction, String idWork, String login, int amount) throws NonexistentUserException,
            NonexistentAuctionException, NonexistentWorkInAuctionException, BelowPriceException;

    /**
     * Closes the auction.
     * @param id auction id.
     * @return List of works sold and not sold in the auction.
     * @Pre: id != null
     */
    Iterator<WorkInAuctionInformation> closeAuction(String id);

    /**
     * Lists all works added to a given auction.
     * @param idAuction auction id.
     * @return List of works added to a given auction.
     * @throws NonexistentAuctionException Auction identifier doesn't exist in the system.
     * @throws EmptyAuctionException Auction has no works added.
     * @Pre: idAuction != null
     */
    Iterator<WorkInAuctionInformation> listAuctionWorks(String idAuction) throws NonexistentAuctionException, EmptyAuctionException;

    /**
     * Lists all works created by an artist.
     * @param login artist login
     * @return List of all works created by an artist.
     * @throws NonexistentUserException Auction identifier doesn't exist in the system.
     * @throws NotAnArtistException Author login doesn't refer to an artist.
     * @throws NoArtistWorksException Artist doesn't works.
     * @Pre: login != null
     */
    Iterator<WorkInformation> listArtistWorks(String login)
                throws NonexistentUserException, NotAnArtistException, NoArtistWorksException;

    /**
     * Lists the purchase proposals for a work in a given auction.
     * @param idAuction auction id.
     * @param idWork work id.
     * @return List of proposals to purchase a work in auction.
     * @throws NonexistentAuctionException Auction identifier doesn't exist in the system.
     * @throws NonexistentWorkInAuctionException Work identifier doesn't exist in the auction.
     * @throws NoBidsException Work has no bids in auction.
     * @Pre: idAuction != null && idWork != null
     */
    Iterator<BidInformation> listBidsWork(String idAuction, String idWork)
            throws NonexistentAuctionException, NonexistentWorkInAuctionException, NoBidsException;

    /**
     * Lists all works in the system in descending order of the highest value for which they have been sold.
     * @return Ranking of works.
     * @throws NoWorksSoldException There aren't works sold in auction.
     */
    Iterator<WorkInformation> listWorksByValue() throws NoWorksSoldException;
}
