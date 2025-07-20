package artAuction;

public class UserClass implements User {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    // Instance variables:

    /**
     * User name.
     */
    protected final String name;

    /**
     * User login.
     */
    private final String login;

    /**
     * User email.
     */
    private final String email;

    /**
     * User age.
     */
    protected final int age;

    /**
     * Number of proposals to buy a work made by the user.
     */
    protected int nrBids;

    /**
     * Constructor of User class.
     * @param login user login.
     * @param name user name.
     * @param age user age.
     * @param email user email.
     */
    public UserClass(String login, String name, int age, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.age = age;
        nrBids = 0;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean isInAuction() {
        return nrBids > 0;
    }

    @Override
    public void increaseNrBids() {
        nrBids++;
    }

    @Override
    public void removeBid() {
        nrBids--;
    }
}
