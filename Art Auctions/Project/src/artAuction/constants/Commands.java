package artAuction.constants;

import artAuction.exceptions.NoCommandException;

/**
 * Commands of the program.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

public enum Commands {
    ADDUSER, ADDARTIST, REMOVEUSER, ADDWORK, INFOUSER, INFOARTIST, INFOWORK, CREATEAUCTION, ADDWORKAUCTION,
    BID, CLOSEAUCTION, LISTAUCTIONWORKS, LISTARTISTWORKS, LISTBIDSWORK, LISTWORKSBYVALUE, QUIT;

    public static Commands fromString(String option) throws NoCommandException {
        for (Commands command : values()) {
            if (command.name().equals(option)) {
                return command;
            }
        }
        throw new NoCommandException();
    }
}
