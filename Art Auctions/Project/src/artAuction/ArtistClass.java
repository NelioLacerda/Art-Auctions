package artAuction;

import artAuction.exceptions.NoArtistWorksException;
import dataStructures.*;

public class ArtistClass extends UserClass implements Artist {

    // Instance variables:

    /**
     * Constant that saves the artist name.
     */
    private final String artisticName;

    /**
     * List containing the artists work.
     */
    private final OrderedDictionary<String, WorkInformation> ownWork;

    /**
     * Number of works in auction.
     */
    private int nWorksInAuction;

    /**
     * Constructor of Artist class.
     * @param login artist login.
     * @param name artist name.
     * @param artisticName artistic name.
     * @param age artist age.
     * @param email artist email.
     */
    public ArtistClass(String login, String name, String artisticName, int age, String email) {
        super(login, name, age, email);
        this.artisticName = artisticName;
        ownWork = new AVLTree<>();
    }

    @Override
    public String getArtisticName() {
        return artisticName;
    }

    @Override
    public boolean hasWorks() {
        return !ownWork.isEmpty();
    }

    @Override
    public void addNewWork(String name, WorkInformation work) {
        ownWork.insert(name, work);
    }

    @Override
    public void increaseNrWorksInAuction() {
        nWorksInAuction++;
    }

    @Override
    public void decreaseNrWorksInAuction() {
        nWorksInAuction--;
    }

    @Override
    public boolean hasWorkInAuction() {
        return nWorksInAuction > 0;
    }

    @Override
    public Iterator<WorkInformation> listOwnWork() throws NoArtistWorksException {
        if (ownWork.isEmpty()) throw new NoArtistWorksException();
        return new ValueIterator<>(ownWork.iterator());
    }
}
