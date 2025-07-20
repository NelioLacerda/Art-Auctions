package artAuction;

import artAuction.exceptions.BelowPriceException;
import artAuction.exceptions.NoBidsException;
import dataStructures.*;

public class WorkInAuctionClass implements WorkInAuction {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * Minimum price that the work can have.
     */
    private final int minPrice;

    /**
     * Bid with the maximum valor.
     */
    private Bid bidMax;

    /**
     * Original work.
     */
    private final Work work;

    /**
     * List containing all the bids of the work.
     */
    private final List<Bid> bids;

    private final List<BidInformation> bidsInformation;

    /**
     * Price for which it was sold
     */
    private int soldPrice;

    /**
     * Buyer.
     */
    private UserInformation buyer;

    /**
     * Constructor of WorkInAuction class.
     * @param work original work.
     * @param minPrice minimum price of the work.
     */
    public WorkInAuctionClass(Work work, int minPrice) {
        this.work = work;
        this.minPrice = minPrice;
        bidMax = null;
        bids = new DoubleList<>();
        bidsInformation = new DoubleList<>();
        soldPrice = 0;
        buyer = null;
    }

    @Override
    public WorkInformation getOriginalWork() {
        return work;
    }

    @Override
    public int getSoldPrice() {
        return soldPrice;
    }

    @Override
    public String getBuyerLogin() {
        return buyer.getLogin();
    }

    @Override
    public String getBuyerName() {
        return buyer.getName();
    }

    @Override
    public void addNewBid(User buyer, int amount) throws  BelowPriceException {
        if (minPrice > amount) throw new BelowPriceException();
        Bid bid = new BidClass(buyer, amount);
        bids.addLast(bid);
        bidsInformation.addLast(bid);
        bid.getBuyer().increaseNrBids();
        setNewMaxBid(bid);
    }

    @Override
    public void setHighestPrice() {
        work.setHighestPrice(bidMax.getAmount());
    }

    @Override
    public boolean closeBids() {
        boolean sold = false;
        if (hasBids()) {
            sold = true;
            soldPrice = bidMax.getAmount();
            buyer = bidMax.getBuyer();
            Iterator<Bid> it = bids.iterator();
            while (it.hasNext()) it.next().getBuyer().removeBid();
        }
        work.decreaseNrAuctions();
        return sold;
    }

    @Override
    public Iterator<BidInformation> listBids() throws NoBidsException {
        if (bids.isEmpty()) throw new NoBidsException();
        return bidsInformation.iterator();
    }

    // Private methods:

    /**
     * Verifies if the work has received any bid.
     * @return true if the work has bids, false otherwise.
     */
    private boolean hasBids() {
        return bidMax != null;
    }

    /**
     * Decides which is the highest bid.
     * @param bid new bid.
     */
    private void setNewMaxBid(Bid bid) {
        if (!hasBids()) bidMax = bid;
        else if (bid.getAmount() > bidMax.getAmount()) bidMax = bid;
    }
}

