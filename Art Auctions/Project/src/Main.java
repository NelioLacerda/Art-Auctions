import artAuction.*;
import artAuction.constants.*;
import artAuction.exceptions.*;
import dataStructures.Iterator;
import java.io.*;
import java.util.Scanner;

/**
 * Main class of the project.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArtAuction art = load();
        chooseOption(in, art);
        in.close();
        save(art);
    }

    /**
     * Deals with the options that the user can choose.
     * @param in scanner.
     * @param art class of the system.
     */
    private static void chooseOption(Scanner in, ArtAuction art) {
        boolean hasFinish = false;
        do {
            try {
                String option = in.next().toUpperCase();
                Commands command = Commands.fromString(option);
                switch (command) {
                    case ADDUSER -> addUser(in, art);
                    case ADDARTIST -> addArtist(in, art);
                    case REMOVEUSER -> removeUser(in, art);
                    case ADDWORK -> addWork(in, art);
                    case INFOUSER -> infoUser(in, art);
                    case INFOARTIST -> infoArtist(in, art);
                    case INFOWORK -> infoWork(in, art);
                    case CREATEAUCTION -> createAuction(in, art);
                    case ADDWORKAUCTION -> addWorkAuction(in, art);
                    case BID -> makeBid(in, art);
                    case CLOSEAUCTION -> closeAuction(in, art);
                    case LISTAUCTIONWORKS -> listAuctionWorks(in, art);
                    case LISTARTISTWORKS -> listArtistWorks(in, art);
                    case LISTBIDSWORK -> listBidsWork(in, art);
                    case LISTWORKSBYVALUE -> listWorkByValue(art);
                    case QUIT -> hasFinish = leave();
                }
            } catch (NoCommandException ignored) {

            }
        } while (!hasFinish);
    }

    private static void addUser(Scanner in, ArtAuction art){
        try {
            String login = in.next().trim();
            String name = in.nextLine().trim();
            int age = in.nextInt();
            String email = in.nextLine().trim();
            art.addUser(login, name, age, email);
            System.out.println(Feedback.ADD_USER.getMessage());
        } catch (UnderageException e) {
            System.out.println(Feedback.UNDERAGE.getMessage());
        } catch (ExistentUserException e) {
            System.out.println(Feedback.EXISTENT_USER.getMessage());
        }
    }

    private static void addArtist(Scanner in, ArtAuction art){
        try {
            String login = in.next().trim();
            String name = in.nextLine().trim();
            String artistName = in.nextLine();
            int age = in.nextInt();
            String email = in.nextLine().trim();
            art.addArtist(login, name, artistName, age, email);
            System.out.println(Feedback.ADD_ARTIST.getMessage());
        } catch (UnderageException e) {
            System.out.println(Feedback.UNDERAGE.getMessage());
        } catch (ExistentUserException e) {
            System.out.println(Feedback.EXISTENT_USER.getMessage());
        }
    }

    private static void removeUser(Scanner in, ArtAuction art){
        try {
            String login = in.nextLine().trim();
            art.removeUser(login);
            System.out.println(Feedback.REMOVE_USER.getMessage());
        } catch (NonexistentUserException e) {
            System.out.println(Feedback.NONEXISTENT_USER.getMessage());
        } catch (UserInAuctionException e) {
            System.out.println(Feedback.USER_IN_AUCTION.getMessage());
        } catch (ArtistInAuctionException e) {
            System.out.println(Feedback.ARTIST_IN_AUCTION.getMessage());
        }
    }

    private static void addWork(Scanner in, ArtAuction art){
        try {
            String id = in.next().trim();
            String loginAuthor = in.next().trim();
            int year = in.nextInt();
            String name = in.nextLine().trim();
            art.addWork(id, loginAuthor, year, name);
            System.out.println(Feedback.ADD_WORK.getMessage());
        } catch (ExistentWorkException e){
            System.out.println(Feedback.EXISTENT_WORK.getMessage());
        } catch (NonexistentUserException e) {
            System.out.println(Feedback.NONEXISTENT_USER.getMessage());
        } catch (NotAnArtistException e) {
            System.out.println(Feedback.NOT_AN_ARTIST.getMessage());
        }
    }

    private static void infoUser(Scanner in, ArtAuction art){
        try {
            String login = in.nextLine().trim();
            UserInformation u = art.infoUser(login);
            System.out.printf(Feedback.INFO_USER.getMessage(), u.getLogin(), u.getName(),
                    u.getAge(), u.getEmail());
        } catch (NonexistentUserException e) {
            System.out.println(Feedback.NONEXISTENT_USER.getMessage());
        }
    }

    private static void infoArtist(Scanner in, ArtAuction art){
        try {
            String login = in.nextLine().trim();
            ArtistInformation a = art.infoArtist(login);
            System.out.printf(Feedback.INFO_ARTIST.getMessage(), a.getLogin(), a.getName(),
                    a.getArtisticName(), a.getAge(), a.getEmail());
        } catch (NonexistentUserException e) {
            System.out.println(Feedback.NONEXISTENT_USER.getMessage());
        } catch (NotAnArtistException e) {
            System.out.println(Feedback.NOT_AN_ARTIST.getMessage());
        }
    }

    private static void infoWork(Scanner in, ArtAuction art){
        try {
            String idWork = in.nextLine().trim();
            WorkInformation w = art.infoWork(idWork);
            System.out.printf(Feedback.INFO_WORK.getMessage(), w.getID(), w.getName(), w.getYear(),
                    w.getHighestSalePrice(), w.getArtistLogin(), w.getArtistName());
        } catch (NonexistentWorkException e){
            System.out.println(Feedback.NONEXISTENT_WORK.getMessage());
        }
    }

    private static void createAuction(Scanner in, ArtAuction art){
        try {
            String idAuction = in.nextLine().trim();
            art.createAuction(idAuction);
            System.out.println(Feedback.CREATE_AUCTION.getMessage());
        } catch (ExistentAuctionException e){
            System.out.println(Feedback.EXISTENT_AUCTION.getMessage());
        }
    }

    private static void addWorkAuction(Scanner in, ArtAuction art){
        try {
            String idAuction = in.next().trim();
            String idWork = in.next().trim();
            int minPrice = in.nextInt();
            in.nextLine();
            art.addWorkAuction(idAuction, idWork, minPrice);
            System.out.println(Feedback.ADD_WORK_AUCTION.getMessage());
        } catch (NonexistentAuctionException e){
            System.out.println(Feedback.NONEXISTENT_AUCTION.getMessage());
        }  catch (NonexistentWorkException e){
            System.out.println(Feedback.NONEXISTENT_WORK.getMessage());
        }
    }

    private static void makeBid(Scanner in, ArtAuction art){
        try {
            String idAuction = in.next().trim();
            String idWork = in.next().trim();
            String login = in.next().trim();
            int value = in.nextInt();
            in.nextLine();
            art.bid(idAuction, idWork, login, value);
            System.out.println(Feedback.BID.getMessage());
        } catch (NonexistentUserException e) {
            System.out.println(Feedback.NONEXISTENT_USER.getMessage());
        } catch (NonexistentAuctionException e) {
            System.out.println(Feedback.NONEXISTENT_AUCTION.getMessage());
        } catch (NonexistentWorkInAuctionException e) {
            System.out.println(Feedback.NONEXISTENT_WORK_AUCTION.getMessage());
        } catch (BelowPriceException e) {
            System.out.println(Feedback.BELOW_PRICE.getMessage());
        }
    }

    private static void closeAuction(Scanner in, ArtAuction art){
        try {
            String id = in.nextLine().trim();
            Iterator<WorkInAuctionInformation> it = art.closeAuction(id);
            System.out.println(Feedback.CLOSE_AUCTION.getMessage());
            while (it.hasNext()) {
                WorkInAuctionInformation work = it.next();
                WorkInformation w = work.getOriginalWork();
                if (work.getSoldPrice() > 0) {
                    System.out.printf(Feedback.WORK_SOLD.getMessage(), w.getID(), w.getName(),
                            work.getBuyerLogin(), work.getBuyerName(), work.getSoldPrice());
                } else System.out.printf(Feedback.WORK_NOT_SOLD.getMessage(), w.getID(), w.getName());
            }
            System.out.println();
        } catch (NonexistentAuctionException e){
            System.out.println(Feedback.NONEXISTENT_AUCTION.getMessage());
        } catch (EmptyAuctionException e) {
            System.out.println(Feedback.CLOSE_AUCTION.getMessage());
            System.out.println();
        }
    }

    private static void listAuctionWorks(Scanner in, ArtAuction art){
        try {
            String id = in.nextLine().trim();
            Iterator<WorkInAuctionInformation> it = art.listAuctionWorks(id);
            System.out.println();
            while (it.hasNext()) {
                WorkInAuctionInformation work = it.next();
                WorkInformation w = work.getOriginalWork();
                System.out.printf(Feedback.LIST_AUCTION_WORKS.getMessage(), w.getID(), w.getName(),
                        w.getYear(), w.getHighestSalePrice(), w.getArtistLogin(), w.getArtistName());
            }
            System.out.println();
        } catch (NonexistentAuctionException e) {
            System.out.println(Feedback.NONEXISTENT_AUCTION.getMessage());
        } catch (EmptyAuctionException e) {
            System.out.println(Feedback.EMPTY_AUCTION.getMessage());
        }
    }

    private static void listArtistWorks(Scanner in, ArtAuction art){
        try {
            String login = in.nextLine().trim();
            Iterator<WorkInformation> it = art.listArtistWorks(login);
            System.out.println();
            while (it.hasNext()) {
                WorkInformation w = it.next();
                System.out.printf(Feedback.LIST_ARTIST_WORKS.getMessage(), w.getID(), w.getName(),
                        w.getYear(), w.getHighestSalePrice());
            }
            System.out.println();
        } catch (NonexistentUserException e) {
            System.out.println(Feedback.NONEXISTENT_USER.getMessage());
        } catch (NotAnArtistException e) {
            System.out.println(Feedback.NOT_AN_ARTIST.getMessage());
        } catch (NoArtistWorksException e) {
            System.out.println(Feedback.NO_ARTIST_WORKS.getMessage());
        }
    }

    private static void listBidsWork(Scanner in, ArtAuction art) {
        try {
            String idAuction = in.next().trim();
            String idWork = in.nextLine().trim();
            Iterator<BidInformation> it = art.listBidsWork(idAuction, idWork);
            System.out.println();
            while (it.hasNext()) {
                BidInformation bid = it.next();
                System.out.printf(Feedback.LIST_BIDS_WORK.getMessage(), bid.getBuyerLogin(),
                        bid.getBuyerName(), bid.getAmount());
            }
            System.out.println();
        } catch (NonexistentAuctionException e) {
            System.out.println(Feedback.NONEXISTENT_AUCTION.getMessage());
        } catch (NonexistentWorkInAuctionException e) {
            System.out.println(Feedback.NONEXISTENT_WORK_AUCTION.getMessage());
        } catch (NoBidsException e) {
            System.out.println(Feedback.NO_BIDS.getMessage());
        }
    }

    private static void listWorkByValue(ArtAuction art){
        try {
            Iterator<WorkInformation> it = art.listWorksByValue();
            System.out.println();
            while (it.hasNext()) {
                WorkInformation work = it.next();
                System.out.printf(Feedback.LIST_WORKS_BY_VALUE.getMessage(), work.getID(), work.getName(),
                        work.getYear(), work.getHighestSalePrice(), work.getArtistLogin(), work.getArtistName());
            }
            System.out.println();
        } catch (NoWorksSoldException e){
            System.out.println(Feedback.NO_WORKS_SOLD.getMessage());
        }
    }

    private static boolean leave(){
        System.out.print(Feedback.QUIT.getMessage());
        return true;
    }

    private static ArtAuction load() {
        try {
            ObjectInputStream file = new ObjectInputStream ( new FileInputStream("out.txt") );
            ArtAuction data = (ArtAuction) file.readObject();
            file.close();
            return data;
        } catch (IOException | ClassNotFoundException e) {
            return new ArtAuctionClass();
        }
    }

    private static void save(ArtAuction art) {
        try {
            ObjectOutputStream file = new ObjectOutputStream ( new FileOutputStream("out.txt") );
            file.writeObject(art);
            file.flush();
            file.close();
        } catch (IOException e) {
            //Do Nothing
        }
    }
}
