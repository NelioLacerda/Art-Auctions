package artAuction;

public class WorkClass implements Work {

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    // Instance variables:

    /**
     * String containing the work ID and the work name, respectively.
     */
    private final String id, name;

    /**
     * Artist that create the work.
     */
    private final Artist creator;

    /**
     * Integer containing the work year.
     */
    private final int year;

    /**
     * Integer containing the lastPrice and highestSalePrice, respectively.
     */
    private int highestSalePrice;

    /**
     * Constructor of Work class.
     * @param id work ID.
     * @param name work name.
     * @param year year when the work was made.
     * @param creator creator of the work.
     */
    public WorkClass(String id, String name, int year, Artist creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.year = year;
        highestSalePrice = 0;
    }

    //Getters

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getArtistName() {
        return creator.getName();
    }

    @Override
    public String getArtistLogin() {
        return creator.getLogin();
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getHighestSalePrice() {
        return highestSalePrice;
    }

    //Other methods

    @Override
    public void increaseNrAuctions() {
        creator.increaseNrWorksInAuction();
    }

    @Override
    public void decreaseNrAuctions() {
        creator.decreaseNrWorksInAuction();
    }

    @Override
    public void setHighestPrice(int finalPrice) {
        if (highestSalePrice < finalPrice) highestSalePrice = finalPrice;
    }

    @Override
    public int compareTo(WorkInformation o) {
        return name.compareTo(o.getName());
    }
}
