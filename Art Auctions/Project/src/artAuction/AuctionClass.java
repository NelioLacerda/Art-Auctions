package artAuction;

import artAuction.exceptions.*;
import dataStructures.*;

public class AuctionClass implements Auction {

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * Dictionary of works in auction.
     */
    private final Dictionary<String, WorkInAuction> worksInAuction;

    /**
     * List of works in auction.
     */
    private final List<WorkInAuctionInformation> listWorks;

    /**
     * Auction id.
     */
    private final String id;

    /**
     * Constructor of auction class.
     * @param id auction id.
     */
    public AuctionClass(String id) {
        this.id = id;
        listWorks = new DoubleList<>();
        worksInAuction = new SearchableHashTable<>();
    }

    @Override
    public void addWork(Work work, int price) {
        WorkInAuction w =  new WorkInAuctionClass(work, price);
        if (worksInAuction.insert(work.getID(), w) != null){
            work.increaseNrAuctions();
            listWorks.addLast(w);
        }
    }

    @Override
    public void addBid(String idWork, int amount, User user) throws NonexistentWorkInAuctionException,
            BelowPriceException {
        WorkInAuction workInAuction = worksInAuction.find(idWork);
        if (workInAuction == null) throw new NonexistentWorkInAuctionException();
        workInAuction.addNewBid(user, amount);
    }

    @Override
    public Iterator<WorkInAuction> close(){
        return new ValueIterator<>(worksInAuction.iterator());
    }

    @Override
    public Iterator<BidInformation> listBidsWork(String idWork) throws NonexistentWorkInAuctionException, NoBidsException {
        WorkInAuction workInAuction = worksInAuction.find(idWork);
        if (workInAuction == null) throw new NonexistentWorkInAuctionException();
        return workInAuction.listBids();
    }

    @Override
    public Iterator<WorkInAuctionInformation> listWorks() throws EmptyAuctionException {
        if (listWorks.isEmpty()) throw new EmptyAuctionException();
        return listWorks.iterator();
    }
}