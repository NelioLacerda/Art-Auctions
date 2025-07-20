package artAuction;

import artAuction.exceptions.*;
import dataStructures.*;

public class ArtAuctionClass implements ArtAuction {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    // Constants:

    /**
     * Constant indicates the legal age to login.
     */
    private static final int LEGAL_AGE = 18;

    // Instance variables:

    /**
     * Dictionary containing all the users of the system.
     */
    private final Dictionary<String, User> users;

    /**
     * Dictionary containing all the auctions of the system.
     */
    private final Dictionary<String, Auction> auctions;

    /**
     * Dictionary containing all the works of the system.
     */
    private final Dictionary<String, Work> works;

    /**
     * Constructor of ArtAuction class.
     */
    public ArtAuctionClass() {
        works = new SearchableHashTable<>();
        users = new SearchableHashTable<>();
        auctions = new SearchableHashTable<>();
    }

    @Override
    public void addUser(String login, String name, int age, String email) throws UnderageException,
            ExistentUserException {
        if (age < LEGAL_AGE) throw new UnderageException();
        if (users.insert(login, new UserClass(login, name, age, email))==null) throw new ExistentUserException();
    }

    @Override
    public void addArtist(String login, String name, String artisticName, int age, String email) throws
            UnderageException, ExistentUserException {
        if (age < LEGAL_AGE) throw new UnderageException();
        if (users.insert(login, new ArtistClass(login, name, artisticName, age, email)) == null) throw new ExistentUserException();
    }

    @Override
    public void removeUser(String login) throws NonexistentUserException, UserInAuctionException,
            ArtistInAuctionException {
        User user = users.find(login);
        if (user == null) throw new NonexistentUserException();
        if (user.isInAuction()) throw new UserInAuctionException();
        if (user instanceof Artist && ((Artist)user).hasWorks()) removeWorks((Artist) user);
        users.remove(login);
    }

    @Override
    public void addWork(String id, String login, int year, String name) throws ExistentWorkException,
            NonexistentUserException, NotAnArtistException {
        Work work = works.find(id);
        if (work != null) throw new ExistentWorkException();
        User artist = users.find(login);
        if (artist == null) throw new NonexistentUserException();
        if (!(artist instanceof Artist)) throw new NotAnArtistException();
        work = new WorkClass(id, name, year, (Artist) artist);
        works.insert(id, work);
        ((Artist) artist).addNewWork(name, work);
    }

    @Override
    public UserInformation infoUser(String login) throws NonexistentUserException {
        User user = users.find(login);
        if (user == null) throw new NonexistentUserException();
        return user;
    }

    @Override
    public ArtistInformation infoArtist(String login) throws NonexistentUserException, NotAnArtistException {
        User user = users.find(login);
        if (user == null) throw new NonexistentUserException();
        if (!(user instanceof Artist)) throw new NotAnArtistException();
        return (ArtistInformation) user;
    }

    @Override
    public WorkInformation infoWork(String id) throws NonexistentWorkException {
        Work work = works.find(id);
        if (work == null) throw new NonexistentWorkException();
        return work;
    }

    @Override
    public void createAuction(String id) throws ExistentAuctionException {
        if (auctions.insert(id,new AuctionClass(id)) == null) throw new ExistentAuctionException();
    }

    @Override
    public void addWorkAuction(String idAuction, String idWork, int minPrice) throws NonexistentAuctionException,
            NonexistentWorkException {
        Auction auction = auctions.find(idAuction);
        if (auction == null) throw new NonexistentAuctionException();
        Work work = works.find(idWork);
        if (work == null) throw new NonexistentWorkException();
        auction.addWork(work, minPrice);
    }

    @Override
    public void bid(String idAuction, String idWork, String login, int amount) throws NonexistentUserException,
            NonexistentAuctionException, NonexistentWorkInAuctionException, BelowPriceException {
        User user = users.find(login);
        if (user == null) throw new NonexistentUserException();
        Auction auction = auctions.find(idAuction);
        if (auction == null) throw new NonexistentAuctionException();
        auction.addBid(idWork, amount, user);
    }

    @Override
    public Iterator<WorkInAuctionInformation> closeAuction(String id) throws NonexistentAuctionException,
            EmptyAuctionException {
        Auction auction = auctions.find(id);
        if (auction == null) throw new NonexistentAuctionException();
        Iterator<WorkInAuction> it = auction.close();
        while (it.hasNext()){
            WorkInAuction work = it.next();
            if (work.closeBids())
                work.setHighestPrice();
        }
        auctions.remove(id);
        return auction.listWorks();
    }

    @Override
    public Iterator<WorkInAuctionInformation> listAuctionWorks(String idAuction) throws NonexistentAuctionException,
            EmptyAuctionException {
        Auction auction = auctions.find(idAuction);
        if (auction == null) throw new NonexistentAuctionException();
        return auction.listWorks();
    }

    @Override
    public Iterator<WorkInformation> listArtistWorks(String login) throws NonexistentUserException,
            NotAnArtistException, NoArtistWorksException {
        User user = users.find(login);
        if (user == null) throw new NonexistentUserException();
        if (!(user instanceof Artist)) throw new NotAnArtistException();
        return ((Artist) user).listOwnWork();
    }

    @Override
    public Iterator<BidInformation> listBidsWork(String idAuction, String idWork) throws NonexistentAuctionException,
            NonexistentWorkInAuctionException, NoBidsException {
        Auction auction = auctions.find(idAuction);
        if (auction == null) throw new NonexistentAuctionException();
        return auction.listBidsWork(idWork);
    }


    @Override
    public Iterator<WorkInformation> listWorksByValue() throws NoWorksSoldException {
        TreeSet<WorkInformation> soldWorks = new AVLTreeSet<>(new WorkCompare());
        Iterator<Work> it = new ValueIterator<>(works.iterator());
        while (it.hasNext()) {
            WorkInformation work = it.next();
            if (work.getHighestSalePrice() != 0)
                soldWorks.add(work);
        }
        if (soldWorks.isEmpty()) throw new NoWorksSoldException();
        return soldWorks.iterator();
    }

    // Private methods:

    /**
     * Removes works of the given user(Artist).
     * @param artist artist.
     * @throws ArtistInAuctionException Artist is in an auction.
     */
    private void removeWorks(Artist artist) throws ArtistInAuctionException {
        if (artist.hasWorkInAuction()) throw new ArtistInAuctionException();
        Iterator<WorkInformation> it = artist.listOwnWork();
        while (it.hasNext()) {
            WorkInformation w = it.next();
            works.remove(w.getID());
        }
    }
}
